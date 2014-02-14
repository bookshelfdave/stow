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
