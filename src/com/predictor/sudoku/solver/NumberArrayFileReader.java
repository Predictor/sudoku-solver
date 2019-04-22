package com.predictor.sudoku.solver;

import java.io.*;
import java.nio.charset.Charset;
import java.util.InputMismatchException;

public class NumberArrayFileReader implements IArrayFileReader {
    private static char min = '0';
    private static char max = '9';
    private static char[] allowedSeparators = {'\n', '\r', ' ', '\t'};

    @Override
    public byte[] Read(String path, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length");
        }
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }

        var file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("path must be an existing file path");
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("path must be a file path, not directory");
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("path must be an existing file path");
        }
        byte[] result = new byte[length];
        int k = 0;
        int c;
        while (true) {
            try {
                if ((c = reader.read()) == -1) break;
            } catch (IOException e) {
                throw new UnsupportedOperationException(String.format("Can not read file %s", path), e);
            }
            char character = (char) c;
            boolean isSeparator = false;
            for (char separator : allowedSeparators) {
                if (character == separator) {
                    isSeparator = true;
                    break;
                }
            }
            if (isSeparator) continue;

            if (character < min || character > max) {
                throw new InputMismatchException(String.format("Unsupported character value %c", character));
            }
            result[k++] = (byte) (character - min);
        }
        return result;
    }
}
