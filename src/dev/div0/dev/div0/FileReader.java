package dev.div0.dev.div0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String read(File path) throws FileNotFoundException
    {
        BufferedReader br = new BufferedReader(new java.io.FileReader(path));

        try
        {
            StringBuilder sb = new StringBuilder();
            String line = null;
            try
            {
                line = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            while (line != null)
            {
                sb.append(line);
                sb.append("\n");
                try
                {
                    line = br.readLine();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            return sb.toString();
            //System.out.println("\nFileContent:\n" + sb.toString());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
