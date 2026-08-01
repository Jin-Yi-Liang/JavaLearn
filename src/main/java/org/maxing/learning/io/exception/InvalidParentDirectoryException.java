package org.maxing.learning.io.exception;

import java.io.IOException;

public class InvalidParentDirectoryException extends IOException {
    public InvalidParentDirectoryException(String message) {
        super(message);
    }
}
