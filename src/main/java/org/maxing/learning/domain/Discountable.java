package org.maxing.learning.domain;

public interface Discountable {
    public static final double MAX_DISCOUNT_RATE=0.5;
    double calculateDiscountPrice(int quantity,double discountRate);
    default boolean isDiscountValid(double discountRate){
        return discountRate>=0 && discountRate<=MAX_DISCOUNT_RATE;
    }
}
