package org.maxing.learning.app;

import org.maxing.learning.domain.*;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new StandardProduct("P100", "service_card", 1299, 5),
                new PhysicalProduct("P101", "keyboard", 200, 10, 2.5),
                new ServiceProduct("P101", "message", 300, 10, 90)
        };
        for (int i = 0; i < products.length; ++i) {
            products[i].printSpecificInfo();
            products[i].printProduct(1,0.2);
        }
    }
}