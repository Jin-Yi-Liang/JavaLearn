package org.maxing.learning.io.service;

import org.maxing.learning.io.exception.InvalidFileException;
import org.maxing.learning.io.exception.InvalidParentDirectoryException;
import org.maxing.learning.io.log.Log;

import java.io.*;

public class FileService {
    public static boolean isValidPath(String path) throws InvalidParentDirectoryException {
        if(path==null || path.isBlank() || path.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean isParentDirectoryExists(String parentPath)throws InvalidParentDirectoryException{
        if(!isValidPath(parentPath)){
            return false;
        }
        File parent=new File(parentPath);
        if(parent==null){
            throw new InvalidParentDirectoryException("parent directory "+parent.getName()+" is null");
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
            if((!result && !parent.exists()) || !parent.isDirectory()){
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
        }catch(IOException ex){
            throw new InvalidFileException("create parent directory failed",ex);
        }

        if(!isValidPath(fileName)){
            throw new InvalidFileException("Invalid file name");
        }
        File file=new File(parentPath,fileName);
        if(file.exists()){
            String logInfo = "file " + file.getAbsolutePath() + " exists!";
            Log.writeLogDefault(logInfo);
            return file;
        }
        try {
            boolean result=file.createNewFile();
            if(!result){
                throw new InvalidFileException("Create file failed");
            }
        }catch(IOException e){
            throw new InvalidFileException("create file failed",e);
        }
        String logInfo="Create file "+file.getAbsolutePath()+" successfully!";
        Log.writeLogDefault(logInfo);

        return file;
    }

    public static void copyFileWithBuff(String readPath,String readFileName,String writePath,String writeFIleName)
            throws InvalidParentDirectoryException, InvalidFileException {
        if(!isValidPath(readPath) || !isValidPath(writePath)){
            throw new InvalidParentDirectoryException("Invalid read or write directory path");
        }

        File readDir=new File(readPath);
        File writeDir=new File(writePath);

        if(!readDir.exists()){
            boolean result=readDir.mkdirs();
            if(!result){
                throw new InvalidParentDirectoryException("Create parent directory failed");
            }
        }
        if(!writeDir.exists()){
            boolean result=writeDir.mkdirs();
            if(!result){
                throw new InvalidParentDirectoryException("Create parent directory failed");
            }
        }

        File readFile = new File(readDir,readFileName);
        File writeFile=new File(writeDir,writeFIleName);

        try(
            FileReader filereader=new FileReader(readFile);
            FileWriter fileWriter=new FileWriter(writeFile);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            BufferedReader bufferedReader=new BufferedReader(filereader);
        ){
            char[]buffer=new char[4096];
            int len=bufferedReader.read(buffer);
            bufferedWriter.write(buffer,0,len);
        }catch(FileNotFoundException ex){
            throw new InvalidFileException("copy file failed",ex);
        }catch(IOException ex){
            throw new InvalidFileException("copy file failed",ex);
        }
    }

    public static void recursiveListFile(String path)throws InvalidParentDirectoryException{
        isParentDirectoryExists(path);
        File parent=new File(path);
        File[]files=parent.listFiles();
        if(files==null){
            throw new InvalidParentDirectoryException("parent directory "+parent.getAbsolutePath()+" is not exists");
        }

        for(File file:files){
            if(file.isDirectory()){
                recursiveListFile(file.getAbsolutePath());
            }
            else if(file.isFile()){
                printFileDetails(file);
            }
            else{
                System.out.println("other type of objects "+file.getAbsolutePath());
            }
        }
    }

    public static void recursiveDelete(String path)throws InvalidParentDirectoryException,InvalidFileException{
        isParentDirectoryExists(path);
        File parent=new File(path);
        File[]files=parent.listFiles();
        if(files==null){
            throw new InvalidParentDirectoryException("parent directory "+parent.getAbsolutePath()+" is not exists");
        }
        for(File file:files){
            if(file.isDirectory()){
                recursiveDelete(file.getAbsolutePath());
            }
            else{
                boolean result=file.delete();
                if(!result){
                    throw new InvalidFileException("delete file "+file.getName()+" failed");
                }
                String logInfo="Delete file "+file.getAbsolutePath();
                System.out.println(logInfo);
                Log.writeLogDefault(logInfo);
            }
        }
        boolean result=parent.delete();
        if(!result){
            throw new InvalidFileException("delete file failed");
        }
        String logInfo="Delete directory "+parent.getAbsolutePath();
        System.out.println(logInfo);
        Log.writeLogDefault(logInfo);
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
            boolean result=directory.delete();
            if(!result) throw new InvalidFileException("delete directory failed");
        }catch(InvalidParentDirectoryException ex){
            throw new InvalidFileException("get parent directory failed",ex);
        }
        System.out.println("Delete directory successfully!");
    }

    public static File[] listFiles(String parentPath) throws InvalidParentDirectoryException{
        File directory=getParentDirectory(parentPath);
        File[]ans=directory.listFiles();
        return ans;
    }

    public static int fileCount(String parentPath) throws InvalidParentDirectoryException{
        File[]files=listFiles(parentPath);
        return files.length;
    }

    public static File[] listJavaFiles(String path)throws InvalidParentDirectoryException{
        isParentDirectoryExists(path);
        FileFilter ff=new FileFilter(){
            @Override
            public boolean accept(File file){
                return file.isFile() && file.getName().endsWith(".java");
            }
        };
        File parent=getParentDirectory(path);
        File[]files=parent.listFiles(ff);
        if(files==null){
            throw new InvalidParentDirectoryException("list files of "+path+" failed");
        }
        return files;
    }

    public static File[] listDirectory(String path)throws InvalidParentDirectoryException{
        isParentDirectoryExists(path);
        FileFilter ff=new FileFilter(){
            @Override
            public boolean accept(File file){
                return file.isDirectory();
            }
        };
        File parent=new File(path);
        File[]files=parent.listFiles(ff);
        if(files==null){
            throw new InvalidParentDirectoryException("list directory of "+path+" failed");
        }
        return files;
    }

    public static void printFileDetails(File file){
        if(file==null){
            throw new IllegalArgumentException("file is null");
        }
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

        System.out.println("Length: "+file.length()+" Bytes");
        System.out.println("Last modified: "+file.lastModified());
        System.out.println("------------------");
    }

    public static File createFileRaw(String parentPath,String fileName) throws InvalidFileException, InvalidParentDirectoryException {
        try {
            File parent = createParentDirectory(parentPath);
        }catch(InvalidParentDirectoryException ex){
            throw new InvalidFileException("create parent directory failed",ex);
        }catch(IOException ex){
            throw new InvalidFileException("create parent directory failed",ex);
        }

        if(!isValidPath(fileName)){
            throw new InvalidFileException("Invalid file name");
        }
        File file=new File(parentPath,fileName);
        if(file.exists()){
            System.out.println("file "+fileName+" exists");
            return file;
        }
        try {
            boolean result=file.createNewFile();
            if(!result){
                throw new InvalidFileException("Create file failed");
            }
        }catch(IOException e){
            throw new InvalidFileException("create file failed",e);
        }
        String logInfo="Create file "+file.getAbsolutePath()+" successfully!";
        System.out.println(logInfo);
        return file;
    }
}
