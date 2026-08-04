package org.maxing.learning.collection.app;

import org.maxing.learning.io.InvalidParentDirectoryException;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[]args){
        //创建父目录
        File parentPath=new File("doc");
        try {
            if (!parentPath.exists() || !parentPath.isDirectory()) {
                boolean result=parentPath.mkdirs();
                if(result){
                    throw new InvalidParentDirectoryException("create parent directory failed!");
                }
            }
        }catch(InvalidParentDirectoryException e){
            e.printStackTrace();
        }

        //打开文件描述符
        File readFile=new File(parentPath,"log.md");
        File writeFile=new File(parentPath,"copy_log.md");

        if(!readFile.exists() || !readFile.isFile()){
            throw new IllegalArgumentException("read file "+readFile.getAbsolutePath()+" is invalid");
        }
        if(!writeFile.exists()){
            try{
                writeFile.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        else if(!writeFile.isFile()){
            throw new IllegalArgumentException("write file "+writeFile.getAbsolutePath()+" is invalid");
        }

        try(Writer fileWriter=new FileWriter(writeFile);
            FileReader fileReader=new FileReader(readFile);){
            char[]buffer=new char[4096];
            int len=fileReader.read(buffer);
            fileWriter.write(buffer,0,len);
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
