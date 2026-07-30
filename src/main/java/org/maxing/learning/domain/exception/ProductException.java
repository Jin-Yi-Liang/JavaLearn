package org.maxing.learning.domain.exception;

public class ProductException extends RuntimeException{
    ProductException(String message){
        super(message);
    }

    ProductException(String message,Throwable cause){
        super(message,cause);
    }
}
