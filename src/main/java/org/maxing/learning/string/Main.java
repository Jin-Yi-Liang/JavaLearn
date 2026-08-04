package org.maxing.learning.string;

import org.maxing.learning.io.exception.InvalidParentDirectoryException;
import org.maxing.learning.io.service.FileService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args){
        try {
            FileService.copyFileWithBuff("doc", "log.md", "doc", "log_copy.md");
        }catch(InvalidParentDirectoryException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
