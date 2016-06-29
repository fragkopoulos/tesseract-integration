package br.com.thiaguten.ocr;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;
import org.kohsuke.args4j.spi.IntOptionHandler;
import org.kohsuke.args4j.spi.MultiPathOptionHandler;

import java.nio.file.Path;
import java.util.List;

/**
 * OCR arguments
 *
 * @author Thiago Gutenberg Carvalho da Costa
 */
public class OCRArgs {

    @Option(handler = IntOptionHandler.class, aliases = "--threads", name = "-t", usage = "Sets a number of worker threads", metaVar = "Number of threads")
    private int threads = 4;

    @Option(required = true, handler = MultiPathOptionHandler.class, aliases = "--files", name = "-f", usage = "Sets the file's paths", metaVar = "File's paths (separated by colon)")
    private List<Path> paths;

    @Option(handler = IntOptionHandler.class, aliases = "--threadsbreath", name = "-tb", usage = "Sets a number of worker threads breath", metaVar = "Number of threads breath in seconds")
    private int threadBreath = 3;

    public OCRArgs(final String[] args) {
        parseArgs(args);
    }

    private void parseArgs(final String[] args) {
        ParserProperties parserProps = ParserProperties.defaults().withUsageWidth(160);
        CmdLineParser parser = new CmdLineParser(this, parserProps);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            parser.printUsage(System.err);
            throw new RuntimeException(e.getMessage());
        }
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public int getThreadBreath() {
        return threadBreath;
    }

    public void setThreadBreath(int threadBreath) {
        this.threadBreath = threadBreath;
    }

}


