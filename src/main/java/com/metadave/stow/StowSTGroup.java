package com.metadave.stow;

import org.antlr.runtime.Token;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.compiler.CompiledST;

import java.util.Arrays;
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
