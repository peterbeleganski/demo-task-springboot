package com.example.exceptions;

public class CsvNotCreatedException extends Exception {
    public CsvNotCreatedException(String message) {
        super(message);
    }

    public CsvNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
