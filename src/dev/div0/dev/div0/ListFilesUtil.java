package dev.div0.dev.div0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ListFilesUtil {
    private String initFolderPath;
    private String prefix;
    private String filePathToPutOnGeneratedFilePath;
    private String filesContentCompositionFilePath;

    private String resultString;
    private int saveToSingleFile;

    private FileWriter fileWriter;
    private FileReader fileReader;

    /**
     * List all the files and folders from a directory
     * @param directoryName to be listed
     */
    public void listFilesAndFolders(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            System.out.println(file.getName());
        }
    }
    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public void listFiles(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
                System.out.println(file.getName());
            }
        }
    }
    /**
     * List all the folder under a directory
     * @param directoryName to be listed
     */
    public void listFolders(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isDirectory()){
                System.out.println(file.getName());
            }
        }
    }
    /**
     * List all files from a directory and its subdirectories
     * @param directoryName to be listed
     */
    public void listFilesAndFilesSubDirectories(String directoryName) {
        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList)
        {
            if (file.isFile()) {

                String fileExtension = getFileExtension(file);
                if(fileExtension.equals("js")){
                    System.out.println("file extension: "+getFileExtension(file));
                    if(saveToSingleFile == 1){
                        try
                        {
                            String fileContent = fileReader.read(file);
                            fileWriter.write(fileContent);

                        } catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    String finalFile = file.getAbsolutePath();

                    finalFile = finalFile.replace(initFolderPath, "");

                    finalFile = finalFile.replace("\\", "/");
                    String fileString = "wp_enqueue_script('"+String.valueOf(Math.round(Math.random()*100000))+"_"+String.valueOf(Math.round(Math.random()*100000))+"', plugins_url('/"+prefix+finalFile+"',FPD_PLUGIN_ROOT_PHP), null, Fancy_Product_Designer::FPD_VERSION);\n";

                    try
                    {
                        Files.write(Paths.get(filePathToPutOnGeneratedFilePath), fileString.getBytes(), StandardOpenOption.APPEND);
                    }
                    catch (IOException e) {
                        System.out.println("error append to file "+e.getMessage());
                    }

                    System.out.println(fileString);
                }
            } else if (file.isDirectory()) {
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }

    public void createFile(){
        try {
            Files.delete(Paths.get(filePathToPutOnGeneratedFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.write(Paths.get(filePathToPutOnGeneratedFilePath), "".getBytes(), StandardOpenOption.CREATE_NEW);
        }
        catch (IOException e)
        {
            System.out.println("error creating file "+e.getMessage());
        }
    }

    public void setInitFolderPath(String initFolderPath) {
        this.initFolderPath = initFolderPath;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setFilePathToPutOnGeneratedFilePath(String filePathToPutOnGeneratedFilePath) {
        this.filePathToPutOnGeneratedFilePath = filePathToPutOnGeneratedFilePath;
    }

    public void setSaveToSingleFile(int saveToSingleFile) {
        this.saveToSingleFile = saveToSingleFile;
    }

    public int getSaveToSingleFile() {
        return saveToSingleFile;
    }

    public void setFilesContentCompositionFilePath(String filesContentCompositionFilePath) {
        this.filesContentCompositionFilePath = filesContentCompositionFilePath;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    private String getFileExtension(File file){
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
