package org.maxing.learning.oop.domain;

public class ServiceProduct extends Product{
    private int duration;

    public ServiceProduct(){
        super();
    }
    public ServiceProduct(String productCode,String name,double price,int stock,int duration){
        super(productCode,name,price,stock);
        setDuration(duration);
    }

    @Override
    public double calculateExtraFee(){
        return calculateServiceFee();
    }

    public double calculateServiceFee(){
        if(duration<=0){
            System.out.println("Invalid duration");
            return -1;
        }
        if(duration<=30){
            return 0;
        }
        else if(duration<=60){
            return 20;
        }
        else{
            return 50;
        }
    }

    @Override
    public String toString(){
        return super.toString()+",duration:"+duration+",service fee:"+calculateServiceFee();
    }

    @Override
    public void showInfo(){
        super.showInfo();
        System.out.println("duration:"+duration+",service fee:"+calculateServiceFee());
    }

    @Override
    public void printSpecificInfo(){
        String runtimeType=this.getClass().getSimpleName();
        System.out.println("runtimeType:"+runtimeType);

        System.out.println("duration:"+duration);

        double serviceFee=calculateServiceFee();
        System.out.println("serviceFee:"+serviceFee);
    }

    private void setDuration(int duration){
        if(duration<=0){
            System.out.println("Invalid duration");
            return;
        }
        this.duration=duration;
    }

    public int getDuration(){
        return this.duration;
    }
}
