package org.maxing.learning.io;

import java.io.File;

public class Main {
    public static void main(String[]args){
        File f1=new File("data");
        FileConstructorDemo.printFileDetails(f1);

        File f2=new File("data","test.data");
        FileConstructorDemo.printFileDetails(f2);

        File f3=new File("src/main/java/org/maxing/learning/io/Main.java");
        FileConstructorDemo.printFileDetails(f3);

        File f4=new File(f1,"test.md");
        FileConstructorDemo.printFileDetails(f4);

        System.out.println(System.getProperty("user.dir"));
    }
}
