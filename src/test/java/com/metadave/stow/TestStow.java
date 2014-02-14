/*
 * -------------------------------------------------------------------
 *
 *   Copyright (c) 2014 Dave Parfitt
 *
 *   This file is provided to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License.  You may obtain
 *   a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 * -------------------------------------------------------------------
 */

package com.metadave.stow;

import org.junit.Test;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public class TestStow {
    @Test
    public void testGen() throws Exception {
        InputStream is = this.getClass().getResourceAsStream("/Stow.stg");
        File f = File.createTempFile("stow","test.stg");
        String root = f.getParent();
        String classdir =  root + File.separator + "stowtest";

        String stgPath = f.getAbsolutePath();
        String stowStg = org.apache.commons.io.IOUtils.toString(is);

        org.apache.commons.io.FileUtils.writeStringToFile(f, stowStg);

        File d = new File(classdir);
        d.mkdirs();

        Stow.generateObjects(stgPath, "stowtest", "Test", classdir);

        File testSTBean = new File(classdir + File.separator + "TestSTBean.java");
        assertTrue(testSTBean.exists());

        File testSTAccessor = new File(classdir + File.separator +  "TestSTAccessor.java");
        assertTrue(testSTAccessor.exists());

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, testSTBean.getAbsolutePath());
        compiler.run(null, null, null, testSTAccessor.getAbsolutePath());

        File testSTAccessorClass = new File(classdir + File.separator + "TestSTAccessor.class");
        assertTrue(testSTAccessorClass.exists());

        File testSTBeanClass = new File(classdir + File.separator + "TestSTBean.class");
        assertTrue(testSTBeanClass.exists());

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(root).toURI().toURL()});
        Class<?> cls = Class.forName("stowtest.TestSTBean", true, classLoader);

        Constructor<?> c = cls.getConstructors()[0];
        STGroup stg = new STGroupFile(f.getAbsolutePath());
        Object o = c.newInstance(stg);

        Set<String> methods = new HashSet<String>();
        methods.add("addPackage");
        methods.add("addBeanClass");
        methods.add("addTemplateName");
        methods.add("addAccessor");

        int count = 0;
        for(Method m :o.getClass().getMethods()) {
            if(methods.contains(m.getName())) {
                count++;
            }
        }
        assertEquals("Look for 8 generated methods", 8, count);
    }
}
