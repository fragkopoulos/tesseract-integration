# Tesseract Java Integration Example

## System Requirements

    First of all, do make optical character recognition (OCR) you will need some native libraries:

        1 - Compile and install Leptonica 1.73 API

            https://github.com/DanBloomberg/leptonica

        2 - Compile and install Tesseract OCR 3.04.1 API

            https://github.com/tesseract-ocr/tesseract/wiki/Compiling

        3 - Set Tesseract Language Data

            https://github.com/tesseract-ocr/tesseract/wiki/Compiling#language-data

        4 - Ghostscript

            http://ghostscript.com/

        5 - JDK 1.7 or above

    Make sure the libraries are installed correctly by typing the following command line:
        <pre>
        thiago@thiago-linux:~$ tesseract --version
        tesseract 3.04.01
         leptonica-1.73
          zlib 1.2.8

        thiago@thiago-linux:~$ ghostscript --version
        9.10
        </pre>
## What is it?

    The library provides optical character recognition (OCR) support for:

        TIFF, JPEG, GIF, PNG, and BMP image formats
        Multi-page TIFF images
        PDF document format

## Run it
<pre>
    This program uses Tess4j 3.0 API and Apache MAVEN. To run it execute the follow steps:

        mvn clean package

        java -jar ocr-api-xxx.jar -b 2 -t 8 -f demo.pdf:demo.tif:demo.bmp:demo.png:demo.jpg

        -f (--files) File's paths                         : Sets the file's paths (separated by colon)
        -t (--threads) Number of threads                  : Sets a number of worker threads (default: 4)
        -b (--breath) Number of threads breath            : Sets a number of worker threads breath (default: 10 seconds)
</pre>