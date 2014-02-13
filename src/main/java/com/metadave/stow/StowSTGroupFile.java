package com.metadave.stow;


import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.compiler.CompiledST;

import java.net.URL;
import java.util.Map;

public class StowSTGroupFile extends STGroupFile {
    public StowSTGroupFile(String fileName) {
        super(fileName);
    }

    public StowSTGroupFile(String fileName, char delimiterStartChar, char delimiterStopChar) {
        super(fileName, delimiterStartChar, delimiterStopChar);
    }

    public StowSTGroupFile(String fullyQualifiedFileName, String encoding) {
        super(fullyQualifiedFileName, encoding);
    }

    public StowSTGroupFile(String fullyQualifiedFileName, String encoding, char delimiterStartChar, char delimiterStopChar) {
        super(fullyQualifiedFileName, encoding, delimiterStartChar, delimiterStopChar);
    }

    public StowSTGroupFile(URL url, String encoding, char delimiterStartChar, char delimiterStopChar) {
        super(url, encoding, delimiterStartChar, delimiterStopChar);
    }

    public Map<String, CompiledST> getTemplates() {
        load();
        return templates;
    }
}
