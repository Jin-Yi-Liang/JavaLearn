package org.maxing.learning.domain;

import java.util.Objects;

public abstract class Product
        implements Discountable, StockManageable, Identifyable {
    private static int productCount=0;
    private static String storeName="Maxing_store";

    private final int productId;
    private String productCode;
    private String name;
    private double price;
    private int stock;

    public Product(){
        productCount++;
        this.productId=productCount;
    }

    public Product(String productCode,String name,double price,int stock){
        this();
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setProductCode(productCode);
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName()+",storeName:"+storeName+", productId:"+productId+", product code:"+productCode+", name:"+name+", price:"+price+", stock:"+stock+
                ", productCount:"+productCount;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==this){
            return true;
        }
        if(obj==null){
            return false;
        }

        if(!(obj instanceof Product other)){
            return false;
        }

        return Objects.equals(this.productCode,other.productCode);
    }

    @Override
    public int hashCode(){
        return Objects.hash(productCode);
    }

    @Override
    public String getIdentifier() {
        return this.getProductCode();
    }

    public void showInfo(){
        System.out.println("storeName:"+storeName+",productId:"+productId+",name:"+name+",price:"+price+",stock:"+stock+",productCount:"+productCount);
    }

    public abstract void printSpecificInfo();

    public void printProduct(int quantity,double discountrate){
        String runtimeType=this.getClass().getSimpleName();
        System.out.println("runtime type:"+runtimeType);

        double extraFee=calculateExtraFee();
        System.out.println("extraFee:"+extraFee);

        double totalPrice=calculateTotalPrice(quantity);
        System.out.println("totalPrice:"+totalPrice);

        double discountPrice=calculateDiscountPrice(quantity, discountrate);
        System.out.println("discountPrice:"+discountPrice);

        System.out.println("---------------");
    }

    public final double calculateTotalPrice(int quantity){
        if(quantity<=0){
            System.out.println("Invalid quantity");
            return -1;
        }
        double prodPrice = price*quantity;
        if(prodPrice<0){
            System.out.println("Invalid product price");
            return -1;
        }
        double extraFee=calculateExtraFee();
        if(extraFee<0){
            System.out.println("Invalid product extra fee");
            return -1;
        }
        return prodPrice+extraFee;
    }

    @Override
    public double calculateDiscountPrice(int quantity,double discountRate){
        if(quantity<=0){
            System.out.println("Invalid quantity");
            return -1;
        }
        if(!isDiscountValid(discountRate)){
            System.out.println("Invalid discount rate");
            return -1;
        }

        double totalPrice=calculateTotalPrice(quantity);
        if(totalPrice<0){
            System.out.println("Invalid total price");
            return -1;
        }
        return (1-discountRate)*totalPrice;
    }

    public abstract double calculateExtraFee();

    @Override
    public void sell(int quantity){
        if(quantity<=0){
            System.out.println("quantity should >0");
            return;
        }
        if(quantity>stock){
            System.out.println("no enough stocks");
            return;
        }
        stock-=quantity;
        System.out.println("sold price:"+quantity*price);
        System.out.println("remaining stock:"+stock);
    }

    @Override
    public void restock(int quantity){
        if(quantity<=0){
            System.out.println("quantity should >0");
            return;
        }
        stock+=quantity;
        System.out.println("remaining stock:"+stock);
    }

    public String getName(){return name;}
    public double getPrice(){return price;}
    public int getStock(){return stock;}

    public void setName(String name){
        if(name==null || name.isBlank()){
            System.out.println("name is not valid");
            return;
        }
        this.name=name;
    }

    public void setPrice(double price){
        if(price<=0){
            System.out.println("price should >0");
            return;
        }
        this.price=price;
    }

    private void setStock(int stock){
        if(stock<0){
            System.out.println("stock should >=0");
            return;
        }
        this.stock=stock;
    }

    public static int getProductCount() {
        return productCount;
    }

    public static void setStoreName(String storeName) {
        if(storeName==null || storeName.isBlank()){
            System.out.println("storeName is not valid");
            return;
        }
        Product.storeName = storeName;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String product_code){
        if(product_code==null || product_code.isBlank()){
            System.out.println("product_code is not valid");
            return;
        }
        this.productCode=product_code;
    }

    public static String getStoreName() {
        return storeName;
    }

    public int getProductId() {
        return productId;
    }
}
