package org.maxing.learning.io.log;

import org.maxing.learning.io.exception.InvalidFileException;
import org.maxing.learning.io.exception.InvalidParentDirectoryException;
import org.maxing.learning.io.util.FileIO;

public class Log {
    public static void writeLogDefault(String info)
            throws InvalidFileException, InvalidParentDirectoryException {
        FileIO.writeFile("log","log.md",info,true);
    }

    public static void readLogDefault()
            throws InvalidFileException, InvalidParentDirectoryException {
        FileIO.readFile("log","log.md");
    }
}
