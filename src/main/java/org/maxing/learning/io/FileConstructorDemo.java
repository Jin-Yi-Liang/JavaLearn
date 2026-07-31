package org.maxing.learning.io;

import java.io.File;

public class FileConstructorDemo {
    public static void printFileDetails(File file){
        if(file==null){
            throw new IllegalArgumentException("file is null");
        }

        System.out.println(file.getName());
        System.out.println("Path: "+file.getPath());
        System.out.println("Absolute path: "+file.getAbsolutePath());
        if(file.exists()){
            System.out.println("file exists");
        }
        else{
            System.out.println("file doesn't exists");
        }
        if(file.isDirectory()){
            System.out.println(file.getName()+" is a directory");
        }
        else if(file.isFile()){
            System.out.println(file.getName()+" is a file");
        }
        else{
            System.out.println(file.getName()+" is another type of object");
        }
        if(file.canRead()){
            System.out.println(file.getName()+" can read");
        }
        else{
            System.out.println(file.getName()+" can not read");
        }
        if(file.canWrite()){
            System.out.println(file.getName()+" can write");
        }
        else{
            System.out.println(file.getName()+" can not write");
        }
        if(file.canExecute()){
            System.out.println(file.getName()+" can execute");
        }
        else{
            System.out.println(file.getName()+" can not execute");
        }
        if(file.isHidden()){
            System.out.println(file.getName()+" is hidden");
        }
        else{
            System.out.println(file.getName()+" is hidden");
        }

        File parent=file.getParentFile();
        if(parent==null){
            System.out.println("parent doesn't exists");
        }
        else {
            System.out.println("Parent name: " + parent.getName());
            if (!parent.exists()) {
                System.out.println("parent do not exists");
            } else {
                System.out.println("parent absolute path: " + parent.getAbsolutePath());
            }
        }

        System.out.println("Length: "+file.length());
        System.out.println("Last modified: "+file.lastModified());
        System.out.println("---------------------------");
    }
}
