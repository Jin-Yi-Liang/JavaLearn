package org.maxing.learning.string;

import java.nio.charset.StandardCharsets;

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

        String str3="  ok,i will told him when i met her";
        String[]strs=str3.split(" ");
        for(String curStr:strs){
            System.out.println(curStr);
        }

        String str4=String.join(" ",strs);
        System.out.println(str4);

        str4=str4.strip();
        System.out.println(str4);

        byte[]bytes=str4.getBytes(StandardCharsets.UTF_8);
        for(byte b:bytes){
            System.out.print(b+" ");
        }
        System.out.println();
        String str5=new String(bytes,StandardCharsets.UTF_8);
        System.out.println(str5);

        String str6=String.format("Name:%s, age:%d","Michael",19);
        System.out.println(str6);
    }
}
