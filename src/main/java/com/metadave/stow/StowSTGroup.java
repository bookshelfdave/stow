package com.metadave.stow;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.compiler.CompiledST;

import java.util.Map;

public class StowSTGroup extends STGroup {

    public StowSTGroup() {
    }

    public StowSTGroup(char delimiterStartChar, char delimiterStopChar) {
        super(delimiterStartChar, delimiterStopChar);
    }

    public Map<String, CompiledST> getTemplates() {
        load();
        return templates;
    }
}
