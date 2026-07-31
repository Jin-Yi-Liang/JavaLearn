package org.maxing.learning.string;

public class Main {
    public static void main(String[] args){
        String str="123123";
        Integer num=Integer.parseInt(str);
        System.out.println(num.getClass());

        str=String.valueOf(num);
        System.out.println(str.getClass());

        String str2="javaWeb";
        String upper=str2.toUpperCase();
        System.out.println(upper);
        String lower=str2.toLowerCase();
        System.out.println(lower);

        String str3="ok,i will told him when i met her";
        String[]strs=str3.split(" ");
        for(String curStr:strs){
            System.out.println(curStr);
        }

        String str4=String.join(" ",strs);
        System.out.println(str4);
    }
}
