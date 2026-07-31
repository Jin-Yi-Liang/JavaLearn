package org.maxing.learning.io;

import java.io.File;
import java.io.IOException;

public class FileConstructorDemo {
    public static boolean isValidPath(String path) throws InvalidParentDirectoryException{
        if(path==null || path.isBlank() || path.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean isParentDirectoryExists(String parentPath)throws InvalidParentDirectoryException{
        File parent=new File(parentPath);
        if(parent==null){
            throw new InvalidParentDirectoryException("parent directory "+parent.getName()+" is null");
        }
        else if(!parent.isDirectory()){
            throw new InvalidParentDirectoryException("parent directory "+parent.getName()+" is not a directory");
        }
        else if(!parent.exists()){
            throw new InvalidParentDirectoryException("parent directory "+parent.getName()+" does not exist");
        }
        else if(!parent.canRead()){
            throw new InvalidParentDirectoryException("parent directory "+parent.getName()+" can not be read");
        }

        if(!parent.exists()){
            return false;
        }
        return true;
    }

    public static File getParentDirectory(String parentPath) throws InvalidParentDirectoryException{
        if(!isValidPath(parentPath)){
            throw new InvalidParentDirectoryException("parent directory "+parentPath+" is invalid");
        }
        File parent=new File(parentPath);
        boolean result=isParentDirectoryExists(parentPath);
        if(!result){
            throw new InvalidParentDirectoryException("parent directory "+parentPath+" is not exists");
        }

        return parent;
    }

    public static File createParentDirectory(String path)throws InvalidParentDirectoryException{
        if(!isValidPath(path)){
            throw new InvalidParentDirectoryException("Invalid parent directory path");
        }
        File parent=new File(path);
        if(!isParentDirectoryExists(path)){
            boolean result=parent.mkdirs();
            if((!result && !parent.exists()) || !parent.exists()){
                throw new InvalidParentDirectoryException("Create parent directory failed");
            }
            else{
                System.out.println("Parent directory created: "+parent.getAbsolutePath());
            }
        }
        return parent;
    }

    public static File createFile(String parentPath,String fileName) throws InvalidFileException, InvalidParentDirectoryException {
        try {
            File parent = createParentDirectory(parentPath);
        }catch(InvalidParentDirectoryException ex){
            throw new InvalidFileException("create parent directory failed",ex);
        }

        if(!isValidPath(fileName)){
            throw new InvalidFileException("Invalid file name");
        }
        File file=new File(parentPath,fileName);
        if(file.exists()){
            System.out.println("File exists!");
            return file;
        }
        try {
            file.createNewFile();
        }catch(IOException e){
            throw new InvalidFileException("create file failed",e);
        }
        System.out.println("Create file!");

        return file;
    }

    public static void deleteFile(String parentPath,String fileName)throws InvalidFileException{
        try {
            File parent = getParentDirectory(parentPath);
        }catch(InvalidParentDirectoryException ex){
            throw new InvalidFileException("get parent directory failed",ex);
        }

        File file=new File(parentPath,fileName);
        if (file==null || !file.exists()) {
            throw new InvalidFileException("file " + fileName + " do not exists");
        }

        boolean result=file.delete();
        if(result && !file.exists()){
            System.out.println("Delete file successfully!");
        }
        else{
            throw new InvalidFileException("Delete file failed");
        }
    }

    public static void deleteDirectory(String direstoryPath) throws InvalidFileException, InvalidParentDirectoryException {
        try {
            File directory = getParentDirectory(direstoryPath);
            if(directory==null){
                throw new InvalidParentDirectoryException("get parent directory failed");
            }
            boolean result=directory.delete();
            if(!result) throw new InvalidFileException("delete directory failed");
        }catch(InvalidParentDirectoryException ex){
            throw new InvalidFileException("get parent directory failed",ex);
        }
        System.out.println("Delete directory successfully!");
    }

    public static void printFileDetails(File file){
        if(file==null){
            throw new IllegalArgumentException("file is null");
        }
        System.out.println("---------------------");
        System.out.println("FIle info:");
        System.out.println("User directory: "+System.getProperty("user.dir"));
        System.out.println("File name: "+file.getName());
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
            System.out.println(file.getName()+" is not hidden");
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
        System.out.println("------------------");
    }
}
