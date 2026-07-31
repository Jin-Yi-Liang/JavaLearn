package org.maxing.learning.oop.app;

import org.maxing.learning.oop.domain.PhysicalProduct;
import org.maxing.learning.oop.domain.Product;
import org.maxing.learning.oop.domain.ServiceProduct;
import org.maxing.learning.oop.domain.StandardProduct;
import org.maxing.learning.oop.domain.exception.InsufficientStockException;
import org.maxing.learning.oop.domain.exception.ProductException;
import org.maxing.learning.oop.service.ProductService;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new StandardProduct("P100", "service_card", 1299, 5),
                new PhysicalProduct("P101", "keyboard", 200, 10, 2.5),
                new ServiceProduct("P101", "message", 300, 10, 90)
        };
        for (Product product : products) {
            System.out.println(product.buildProductInfo());
            //product.printSpecificInfo();
            //System.out.println(product.calculateDiscountPrice(1, 0.3));
        }
    }
}