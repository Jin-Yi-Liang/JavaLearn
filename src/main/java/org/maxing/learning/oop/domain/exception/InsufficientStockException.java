package org.maxing.learning.oop.domain.exception;

public class InsufficientStockException extends ProductException{
    private final String productCode;
    private final int stock;
    private final int requestCount;

    public InsufficientStockException(String productCode, int stock, int requestCount) {
        super("Insufficient stock: productCode:"+ productCode + ", remaining stock:"+stock + ", requestCount="+ requestCount);
        this.productCode = productCode;
        this.stock = stock;
        this.requestCount = requestCount;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getStock() {
        return stock;
    }

    public int getRequestCount() {
        return requestCount;
    }
}