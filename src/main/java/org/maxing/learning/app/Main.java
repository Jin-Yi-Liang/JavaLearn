package org.maxing.learning.app;

import org.maxing.learning.domain.*;
import org.maxing.learning.domain.exception.InsufficientStockException;
import org.maxing.learning.domain.exception.ProductException;
import org.maxing.learning.service.ProductService;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new StandardProduct("P100", "service_card", 1299, 5),
                new PhysicalProduct("P101", "keyboard", 200, 10, 2.5),
                new ServiceProduct("P101", "message", 300, 10, 90)
        };
        for (Product product : products) {
            product.printSpecificInfo();
            System.out.println(product.calculateDiscountPrice(1, 0.3));
        }

        try{
            ProductService.purchase(products[0],10000);
        }catch(InsufficientStockException e){
            System.out.println(e.getMessage());
            System.out.println("product code: "+e.getProductCode());
            System.out.println("remaining stock: "+e.getStock());
            System.out.println("request quantity: "+e.getRequestCount());
        }catch(ProductException e){
            System.out.println("Product exception:"+e.getMessage());
        }
    }
}