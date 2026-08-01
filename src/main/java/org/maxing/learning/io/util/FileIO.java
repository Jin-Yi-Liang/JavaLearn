package org.maxing.learning.io.util;

import org.maxing.learning.io.service.FileService;
import org.maxing.learning.io.exception.InvalidFileException;
import org.maxing.learning.io.exception.InvalidParentDirectoryException;
import org.maxing.learning.io.log.Log;

import java.io.*;
import java.nio.charset.StandardCharsets;

public final class FileIO {

    public static void writeFile(String path,String fileName,String info,boolean appendable)
            throws InvalidFileException, InvalidParentDirectoryException {
        File logFile= FileService.createFileRaw(path,fileName);
        info+="\n";
        byte[]bytes=info.getBytes(StandardCharsets.UTF_8);
        try(OutputStream outputStream=new FileOutputStream(logFile,appendable)){
            outputStream.write(bytes);
        }catch(IOException ex){
            throw new InvalidFileException("fail to write log into "+logFile.getName(),ex);
        }
    }

    public static void readFile(String path,String fileName)
            throws InvalidFileException, InvalidParentDirectoryException {
        FileService.isParentDirectoryExists(path);
        File logFile= FileService.createFileRaw(path,fileName);
        try(InputStream inputStream=new FileInputStream(logFile)){
            System.out.println("-------------------------");
            System.out.println("Read log from: "+ logFile.getAbsolutePath());
            byte[]buffer=new byte[1024];
            int len;
            while((len=inputStream.read(buffer))!=-1){
                String str=new String(buffer,0,len,StandardCharsets.UTF_8);
                System.out.print(str);
            }
        }catch(FileNotFoundException ex){
            throw new InvalidFileException("fail to read log into "+logFile.getName(),ex);
        }catch(IOException ex){
            throw new InvalidFileException("fail to read log into "+logFile.getName(),ex);
        }
    }

    public static void copyFileWithLength(String sourcePath,String sourceFileName,String targetPath,String targetFileName,final int length)
            throws InvalidFileException, InvalidParentDirectoryException {
        if(length<=0){
            throw new InvalidFileException("buffer length should be greater than 0");
        }
        FileService.isParentDirectoryExists(sourcePath);
        FileService.isParentDirectoryExists(targetPath);
        File sourceFile= FileService.createFileRaw(sourcePath,sourceFileName);
        File targetFile= FileService.createFileRaw(targetPath,targetFileName);
        try(InputStream inputStream=new FileInputStream(sourceFile);
            OutputStream outputStream=new FileOutputStream(targetFile) ){
            byte[] buffer = new byte[length];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        }catch(FileNotFoundException ex){
            throw new InvalidFileException("find file error",ex);
        }catch(IOException ex){
            throw new InvalidFileException("copy file error",ex);
        }
    }

    public static void copyFile(String sourcePath,String sourceFileName,String targetPath,String targetFileName)
            throws InvalidFileException, InvalidParentDirectoryException {
        FileService.isParentDirectoryExists(sourcePath);
        FileService.isParentDirectoryExists(targetPath);
        File sourceFile= FileService.createFileRaw(sourcePath,sourceFileName);
        File targetFile= FileService.createFileRaw(targetPath,targetFileName);
        try(InputStream inputStream=new FileInputStream(sourceFile);
            OutputStream outputStream=new FileOutputStream(targetFile) ){
            long len=inputStream.transferTo(outputStream);
            System.out.println("Copy length: "+len);
            Log.writeLogDefault("copy "+len+" Bytes from "+sourceFile.getAbsolutePath()+
                    " to "+targetFile.getAbsolutePath());
        }catch(FileNotFoundException ex){
            throw new InvalidFileException("find file error",ex);
        }catch(IOException ex){
            throw new InvalidFileException("copy file error",ex);
        }
    }
}
