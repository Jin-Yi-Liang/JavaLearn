package org.maxing.learning.functional_interface;

public class FunctionalInterface {
    public static void main(String[] args) {
        AddAble addable=(a,b)->a+b;
        int ans=addable.add(10,20);
        System.out.println(ans);
    }
}
