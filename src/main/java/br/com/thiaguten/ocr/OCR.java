package br.com.thiaguten.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A Java example of OCR using Tess4j wrapper for Tesseract OCR API using
 * JNA Interface Mapping.
 * <p>
 * tesseract 3.04.01
 * leptonica-1.73
 * ghostscript-9.10
 *
 * @author Thiago Gutenberg Carvalho da Costa
 */
public class OCR {

    private final ExecutorService executorService;

    private final List<Path> paths;
    private final int threadWorkerBreath;

    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        OCRArgs ocrArgs = new OCRArgs(args);
        OCR ocr = new OCR(ocrArgs);
        ocr.run();
        ocr.shutdown();
    }

    public OCR(OCRArgs ocrArgs) {
        this.paths = ocrArgs.getPaths();
        this.threadWorkerBreath = ocrArgs.getThreadBreath();
        this.executorService = Executors.newFixedThreadPool(ocrArgs.getThreads());

        Runtime.getRuntime().addShutdownHook(new Thread("jvm shutdown ocr executor service") {
            @Override
            public void run() {
                shutdown();
            }
        });
    }

    public void run() {
        run(TimeUnit.SECONDS);
    }

    public void run(final TimeUnit timeUnit) {
        for (Path image : paths) {
            executorService.execute(new OCRTask(image));

            // Meow...
            // To avoid JVM fatal error in the ghostscript native library
            // in multi-thread environment.
            // # Problematic frame:
            // # C [libgs.so.9.10+0x196315] file_close_file+0x85
            try {
                timeUnit.sleep(threadWorkerBreath);
            } catch (InterruptedException e) {
                // nop
            }
        }
    }

    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    /**
     * OCR Task
     *
     * @author Thiago Gutenberg Carvalho da Costa
     */
    private static class OCRTask implements Runnable {

        private final Path imageFile;

        public OCRTask(Path imageFile) {
//            System.setProperty("jna.debug_load", "true"); // print its library search steps to the console
//            System.setProperty("jna.debug_load.jna", "true"); // trace the search for JNA's own native support
            System.setProperty("jna.library.path", "/usr/local/lib");

            this.imageFile = imageFile;
        }

        @Override
        public void run() {
            ITesseract instance = new Tesseract(); // JNA Interface Mapping
//            ITesseract instance = new Tesseract1(); // JNA Direct Mapping
//            instance.setTessVariable("LC_NUMERIC", "C");
            instance.setDatapath("/usr/local/share"); // parent directory of tessdata
            instance.setLanguage("por");

            try {
                String result = instance.doOCR(imageFile.toFile());
                System.out.println(result);
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
        }

    }

}
