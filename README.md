# Tesseract Java Integration Example

[![License](https://img.shields.io/badge/license-apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

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

        MAC OSX: (MacPorts):

        Thiagos-MacBook-Pro:~ thiago$ tesseract --version
        tesseract 3.04.00
         leptonica-1.73
          libgif 4.2.3 : libjpeg 9a : libpng 1.6.23 : libtiff 4.0.6 : zlib 1.2.8 : libwebp 0.5.0 : libopenjp2 2.1.0

        Thiagos-MacBook-Pro:~ thiago$ gs -v
        GPL Ghostscript 9.19 (2016-03-23)
        Copyright (C) 2016 Artifex Software, Inc.  All rights reserved.

        LINUX (Manual Compiling):

        thiago@thiago-linux:~$ tesseract --version
        tesseract 3.04.01
         leptonica-1.73
          zlib 1.2.8

        thiago@thiago-linux:~$ ghostscript --version
        9.10

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