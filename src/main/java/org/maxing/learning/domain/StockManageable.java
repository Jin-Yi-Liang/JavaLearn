package org.maxing.learning.domain;

public interface StockManageable {
    public void sell(int quantity);
    public void restock(int quantity);
}
