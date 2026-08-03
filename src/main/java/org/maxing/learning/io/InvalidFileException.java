package org.maxing.learning.io;

import java.io.IOError;
import java.io.IOException;

public class InvalidFileException extends IOException {
    public InvalidFileException(String message) {
        super(message);
    }
    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
