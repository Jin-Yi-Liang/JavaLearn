package org.maxing.learning.domain;

public class PhysicalProduct extends Product{
    private double weight;

    public PhysicalProduct() {
        super();
    }
    public PhysicalProduct(String productCode,String name,double price,int stock,double weight) {
        super(productCode,name,price,stock);
        this.setWeight(weight);
    }

    @Override
    public double calculateExtraFee(){
        return calculateShippingFee();
    }

    public double calculateShippingFee(){
        if(this.weight<=0){
            System.out.println("invalid weight");
            return -1;
        }
        if(this.weight<=1){
            return 8;
        } else if (this.weight <= 3) {
            return 12;
        }
        else{
            return 20;
        }
    }

    @Override
    public String toString(){
        return super.toString()+",weight:"+weight+",shipping fee:"+this.calculateShippingFee();
    }

    @Override
    public void showInfo(){
        super.showInfo();
        System.out.println("weight:"+weight+",shipping fee:"+this.calculateShippingFee());
    }

    @Override
    public void printSpecificInfo(){
        String runtimeType=this.getClass().getSimpleName();
        System.out.println("runtimeType:"+runtimeType);

        System.out.println("weight:"+weight);

        double shippingFee=calculateShippingFee();
        System.out.println("shipping fee:"+shippingFee);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if(weight<=0){
            System.out.println("invalid weight");
            return;
        }
        this.weight = weight;
    }
}
