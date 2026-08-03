package org.maxing.learning.io;

import java.io.File;

public class Main {
    public static void main(String[]args) {
        try {
            File f1 = FileConstructorDemo.createFile("data/md", "test.md");
            FileConstructorDemo.printFileDetails(f1);
        }catch(InvalidFileException fileEx){
            fileEx.getCause().printStackTrace();
        }catch(InvalidParentDirectoryException p){
            p.getCause().printStackTrace();
        }
//
//        try {
//            FileConstructorDemo.deleteFile("data","test.md");
//            FileConstructorDemo.deleteDirectory("data");
//        }catch(InvalidFileException fileEx){
//            fileEx.printStackTrace();
//        }catch(InvalidParentDirectoryException p){
//            p.getCause().printStackTrace();
//        }

        try {
            String path="src/main/java/org/maxing/learning";
            FileConstructorDemo.recursiveListFile(path);
            FileConstructorDemo.recursiveDelete("data");
        }catch(InvalidParentDirectoryException p){
            p.printStackTrace();
        }
    }
}
