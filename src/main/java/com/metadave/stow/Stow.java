package com.metadave.stow;

import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.compiler.FormalArgument;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Stow {

    public static String getNiceName(String id) {
        if(id == null || id.length() == 0) {
            return "";
        }
        return id.substring(0, 1).toUpperCase() + id.substring(1);
    }


    public static void generateObjects(String groupFile, String classPackage, String classPrefix, String dest) {
        if(classPrefix == null) {
            classPrefix = "";
        }

        Set<String> templateNames = new HashSet<String>();


        StowSTGroupFile stg = new StowSTGroupFile(groupFile);
        STGroup outputGroup = new STGroupFile("Stow.stg");
        Map<String, CompiledST> ts = stg.getTemplates();
        for(String s: ts.keySet()) {
            CompiledST t = ts.get(s);
            if(t.isAnonSubtemplate) {
                continue;
            }
            if(!templateNames.contains(t.name.toUpperCase())) {
                templateNames.add(t.name.toUpperCase());
            } else {
                System.out.println("Skipping " + t.name);
                continue;
            }
            ST bean = outputGroup.getInstanceOf("STBean");
            bean.add("Package", classPackage);
            bean.add("TemplateName", t.name);
            String className = classPrefix + t.name;
            bean.add("BeanClass", className);
            if(t.hasFormalArgs) {
                Map<String, FormalArgument> fas = t.formalArguments;
                if(fas != null) {
                    for(String fa: fas.keySet()) {
                        FormalArgument f = fas.get(fa);

                        ST acc = outputGroup.getInstanceOf("STAccessor");
                        acc.add("BeanClass", className);
                        acc.add("MethodName",getNiceName(f.name));
                        acc.add("ParamName",f.name);
                        bean.add("Accessor", acc);
                        //System.out.println(f.name + ":" + f.index);
                    }
                }
            }

            String outputFileName = dest + File.separator + className + ".java";
            System.out.println("Generating " + outputFileName);
            File f = new File(outputFileName);

            try {
                FileUtils.writeStringToFile(f, bean.render());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished!");
    }
    public static void main(String args[]) {
        // TODO: from Maven
        // http://mojo.codehaus.org/exec-maven-plugin/examples/example-exec-for-java-programs.html
        CommandLineParser parser = new BasicParser();

        Options options = new Options();
        //options.addOption( "a", "all", false, "do not hide entries starting with .");

        Option javaPackage = new Option("java_package", "package for generated classes");
        javaPackage.setArgs(1);
        //javaPackage.setRequired(true);

        Option destDir =  new Option("dest", "destination directory for generated .java files");
        destDir.setArgs(1);

        Option stgFile =  new Option("stg", "StringTemplate4 group file");
        stgFile.setArgs(1);

        Option classPrefix =  new Option("class_prefix", "Prefix to use for generated classes");
        classPrefix.setArgs(1);

        //destDir.setRequired(true);

        options.addOption(javaPackage);
        options.addOption(destDir);
        options.addOption(stgFile);
        options.addOption(classPrefix);

        try {
            CommandLine line = parser.parse(options, args);

            if(line.hasOption("java_package") && line.hasOption("dest") && line.hasOption("stg")) {
                generateObjects(line.getOptionValue("stg"),
                                line.getOptionValue("java_package"),
                                line.hasOption("class_prefix") ? line.getOptionValue("class_prefix") : "",
                                line.getOptionValue("dest")
                                );
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("stow", options);
            }
        }
        catch( ParseException exp ) {
            System.out.println( "Unexpected exception:" + exp.getMessage() );
        }
    }
}
