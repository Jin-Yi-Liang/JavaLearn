package org.maxing.learning.oop.domain;

import org.maxing.learning.oop.domain.exception.InsufficientStockException;

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
            throw new IllegalArgumentException("quantity should be greater than 0");
        }

        double prodPrice = price*quantity;
        if(prodPrice<0){
            throw new IllegalStateException("prodPrice should be greater than 0");
        }

        double extraFee=calculateExtraFee();
        if(extraFee<0){
            throw new IllegalStateException("extraFee should be greater than 0");
        }

        return prodPrice+extraFee;
    }

    @Override
    public double calculateDiscountPrice(int quantity,double discountRate){
        if(quantity<=0){
            throw new IllegalArgumentException("quantity should be greater than 0");
        }

        if(!isDiscountValid(discountRate)){
            throw new IllegalArgumentException("discountRate should between 0 and "+ getMaxDiscountRate());
        }

        double totalPrice=calculateTotalPrice(quantity);
        if(totalPrice<0){
            throw new IllegalArgumentException("totalPrice should be greater than 0");
        }

        return (1-discountRate)*totalPrice;
    }

    public abstract double calculateExtraFee();

    @Override
    public void sell(int quantity){
        if(quantity<=0){
            throw new IllegalArgumentException("quantity should be greater ore equal to 0");
        }

        if(quantity>stock){
            throw new InsufficientStockException(getProductCode(),getStock(),quantity);
        }

        stock-=quantity;
        System.out.println("sold price:"+quantity*price);
        System.out.println("remaining stock:"+stock);
    }

    @Override
    public void restock(int quantity){
        if(quantity<=0){
            throw new IllegalArgumentException("quantity should be greater than 0");
        }

        stock+=quantity;
        System.out.println("remaining stock:"+stock);
    }

    public String getName(){return name;}
    public double getPrice(){return price;}
    public int getStock(){return stock;}

    public void setName(String name){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("name should not be empty");
        }

        this.name=name;
    }

    public void setPrice(double price){
        if(price<=0){
            throw new IllegalArgumentException("price should be greater than 0");
        }

        this.price=price;
    }

    private void setStock(int stock){
        if(stock<0){
            throw new IllegalArgumentException("stock should be greater than 0");
        }

        this.stock=stock;
    }

    public static int getProductCount() {
        return productCount;
    }

    public static void setStoreName(String storeName) {
        if(storeName==null || storeName.isBlank()){
            throw new IllegalArgumentException("storeName should not be empty");
        }

        Product.storeName = storeName;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String product_code){
        if(product_code==null || product_code.isBlank()){
            throw new IllegalArgumentException("productCode should not be empty");
        }

        this.productCode=product_code;
    }

    public static String getStoreName() {
        return storeName;
    }

    public int getProductId() {
        return productId;
    }

    public String buildProductInfo(){
        StringBuilder sb=new StringBuilder();
        sb.append("productId:")
            .append(productId)
            .append(" ,product code: ")
            .append(this.getProductCode())
            .append(" ,name: ")
            .append(this.getName())
            .append(" ,price: ")
            .append(this.getPrice())
            .append(" ,stock: ")
            .append(this.getStock());
        return sb.toString();
    }
}
