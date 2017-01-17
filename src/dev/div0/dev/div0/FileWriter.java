package dev.div0.dev.div0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter
{
    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void write(String data){
        try
        {
            Files.write(Paths.get(filePath), data.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
