package dev.div0.dev.div0.outputString;

import dev.div0.dev.div0.IOutputString;

public class PlaneJSOutputString implements IOutputString{
    @Override
    public String getString(String prefix, String finalFile) {
        String fileString = "<script type='javascript' src='"+prefix+finalFile+"'></script>\n";
        return fileString;
    }
}
