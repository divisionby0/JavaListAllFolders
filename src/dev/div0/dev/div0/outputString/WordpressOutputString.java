package dev.div0.dev.div0.outputString;

import dev.div0.dev.div0.IOutputString;

public class WordpressOutputString implements IOutputString {
    @Override
    public String getString(String prefix, String finalFile){
        String fileString = "wp_enqueue_script('"+String.valueOf(Math.round(Math.random()*100000))+"_"+String.valueOf(Math.round(Math.random()*100000))+"', plugins_url('/"+prefix+finalFile+"'));\n";
        return fileString;
    }
}
