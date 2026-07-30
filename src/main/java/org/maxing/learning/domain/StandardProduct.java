package org.maxing.learning.domain;

public class StandardProduct extends Product{
    public StandardProduct(){
        super();
    }

    public StandardProduct(String productCode,String name,double price,int stock) {
        super(productCode,name,price,stock);
    }

    @Override
    public void printSpecificInfo(){
        String runtimeType=this.getClass().getSimpleName();
        System.out.println("runtimeType:"+runtimeType);

        System.out.println("Standard product do not have specific info");
    }

    @Override
    public double calculateExtraFee() {
        return 0;
    }
}
