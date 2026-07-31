package org.maxing.learning.oop.service;

import org.maxing.learning.oop.domain.Product;

public class ProductService {
    public static void purchase(Product product,int quantity){
        if(product==null){
            throw new IllegalArgumentException("product should not be null");
        }

        product.sell(quantity);
    }
}