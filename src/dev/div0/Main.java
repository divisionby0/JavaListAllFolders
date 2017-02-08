package dev.div0;

import dev.div0.dev.div0.*;
import dev.div0.dev.div0.outputString.OutputType;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main
{
    private static String generatedFilePath;

    private static String version = "0.0.1";

    public static void main(String[] args) {
        System.out.println(version);
        try {
            String path = getProgramPath2();

            String fileSeparator = System.getProperty("file.separator");
            generatedFilePath = path + fileSeparator + "generated" + fileSeparator;
            System.out.println("generatedFilePath: "+generatedFilePath);

            File file = new File(generatedFilePath);
            file.mkdir();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        ListFilesUtil listFilesUtil = new ListFilesUtil();
        Scanner scan = new Scanner(System.in);

        String[] inputValues = new String[4];

        for (int i = 0; i < inputValues.length; i++)
        {
            if(i==0){
                System.out.println("Enter folder:");
            }
            else if(i==1){
                System.out.println("Enter prefix:");
            }
            else if(i==2){
                System.out.println("Save to single file all files content (0/1):");
            }
            else if(i==3){
                System.out.println("wordpress / plane js (0/1):");
            }

            inputValues[i] = scan.nextLine();
        }

        System.out.println("folder: "+inputValues[0]);
        System.out.println("prefix: "+inputValues[1]);
        System.out.println("single file included: "+inputValues[2]);
        System.out.println("wordpress / plane js : "+inputValues[3]);

        String text = scan.nextLine();
        listFilesUtil.setInitFolderPath(inputValues[0]);
        listFilesUtil.setPrefix(inputValues[1]);
        listFilesUtil.setSaveToSingleFile(Integer.parseInt(inputValues[2]));

        int outputType = Integer.parseInt(inputValues[3]);

        if(outputType == 0){
            listFilesUtil.setOutputStringType(OutputType.WORDPRESS);
        }
        else if(outputType == 1){
            listFilesUtil.setOutputStringType(OutputType.PLANE_JS);
        }
        else{
            System.err.println("unable to detect output type. You must select 0 or 1");
            return;
        }

        if(listFilesUtil.getSaveToSingleFile() == 1){
            listFilesUtil.setFilesContentCompositionFilePath(generatedFilePath+"/out.js");

            try
            {
                Files.delete(Paths.get(generatedFilePath+"/out.js"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                Files.write(Paths.get(generatedFilePath+"/out.js"), "".getBytes(), StandardOpenOption.CREATE_NEW);
            }
            catch (IOException e)
            {
                System.out.println("error creating file "+e.getMessage());
            }

            FileWriter fileWriter = new FileWriter();
            fileWriter.setFilePath(generatedFilePath+"/out.js");
            FileReader fileReader = new FileReader();

            listFilesUtil.setFileWriter(fileWriter);
            listFilesUtil.setFileReader(fileReader);
        }

        listFilesUtil.setFilePathToPutOnGeneratedFilePath(generatedFilePath+"/out.php");
        listFilesUtil.createFile();
        listFilesUtil.listFilesAndFilesSubDirectories(inputValues[0]);
    }

    public static String getProgramPath2() throws UnsupportedEncodingException
    {
        URL url = Main.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        String parentPath = new File(jarPath).getParentFile().getPath();
        return parentPath;
    }
}
