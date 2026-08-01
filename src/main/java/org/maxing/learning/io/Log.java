package org.maxing.learning.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Log {
    public static void writeLog(String path,String fileName,String info,boolean appendable)
            throws InvalidFileException, InvalidParentDirectoryException {
        File logFile=FileConstructorDemo.createFileRaw(path,fileName);
        info+="\n";
        byte[]bytes=info.getBytes(StandardCharsets.UTF_8);
        try(OutputStream outputStream=new FileOutputStream(logFile,appendable)){
            outputStream.write(bytes);
        }catch(IOException ex){
            throw new InvalidFileException("fail to write log into "+logFile.getName(),ex);
        }
    }

    public static void writeLogDefault(String info)
            throws InvalidFileException, InvalidParentDirectoryException {
        writeLog("log","log.md",info,true);
    }
}
