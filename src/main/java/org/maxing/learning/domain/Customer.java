package org.maxing.learning.domain;

public class Customer implements Identifyable{
    private final String customerId;
    private String name;

    public Customer(String customerId,String name){
        this.customerId=customerId;
        this.name=name;
    }

    @Override
    public String getIdentifier() {
        return customerId;
    }
}
