package org.maxing.learning.service;

import org.maxing.learning.domain.Product;

public class ProductService {
    public static void purchase(Product product,int quantity){
        if(product==null){
            throw new IllegalArgumentException("product should not be null");
        }

        product.sell(quantity);
    }
}
