package dev.div0.dev.div0;

import java.io.File;

public class ListFolderContent
{
    private String currentFileString = "/";
    private void listFilesForFolder(final File folder)
    {
        /*
        //System.out.println("folder is: "+folder);
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory())
            {
                currentFileString+=fileEntry.getName()+"/";
                listFilesForFolder(fileEntry);
            }
            else
            {
                currentFileString+=fileEntry.getName();
                System.out.println("/js/div0"+currentFileString);
                currentFileString = "/";
            }
        }

        System.out.println(folder.getAbsoluteFile());

        if(folder.isDirectory()){
            String[] subNote = folder.list();
            for(String filename : subNote){
                listFilesForFolder(new File(folder, filename));
            }
        }
        */
    }

    public void execute(File folder){
        listFilesForFolder(folder);
    }
}
