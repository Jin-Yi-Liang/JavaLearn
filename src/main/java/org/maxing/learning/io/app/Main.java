package org.maxing.learning.io.app;

import org.maxing.learning.io.service.FileService;
import org.maxing.learning.io.exception.InvalidFileException;
import org.maxing.learning.io.exception.InvalidParentDirectoryException;
import org.maxing.learning.io.log.Log;
import org.maxing.learning.io.util.FileIO;

import java.io.File;

public class Main {
    public static void main(String[]args) {
        try {
            File f1 = FileService.createFile("data/md", "test.md");
            FileService.printFileDetails(f1);
        }catch(InvalidFileException fileEx){
            fileEx.getCause().printStackTrace();
        }catch(InvalidParentDirectoryException p){
            p.getCause().printStackTrace();
        }
//
//        try {
//            FileConstructor.deleteFile("data","test.md");
//            FileConstructor.deleteDirectory("data");
//        }catch(InvalidFileException fileEx){
//            fileEx.printStackTrace();
//        }catch(InvalidParentDirectoryException p){
//            p.getCause().printStackTrace();
//        }

        try {
            String path="src/main/java/org/maxing/learning";
            FileService.recursiveListFile(path);
            FileService.recursiveDelete("data");
        }catch(InvalidParentDirectoryException p){
            p.printStackTrace();
        }catch(InvalidFileException fileEx){
            fileEx.getCause().printStackTrace();
        }

        try {
            Log.readLogDefault();
            FileIO.copyFile("log","log.md","log","logBck.md");
        }catch(InvalidParentDirectoryException ex){
            ex.printStackTrace();
        }catch(InvalidFileException fileEx){
            fileEx.getCause().printStackTrace();
        }
    }
}
