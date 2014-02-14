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
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import com.metadave.stow.AbstractStow;

// generated by stow
// https://github.com/metadave/stow
public class STOWSTBean implements AbstractStow {
    ST st = null;
    public static final String templateName = "STBean";

    public STOWSTBean(STGroup g) {
        st = g.getInstanceOf("STBean");
    }

    public ST getST() {
        return st;
    }

    public STOWSTBean addPackage(AbstractStow val) {
        st.add("Package", val.getST());
        return this;
    }

    public STOWSTBean addPackage(Object val) {
        st.add("Package", val);
        return this;
    }

    public STOWSTBean addBeanClass(AbstractStow val) {
        st.add("BeanClass", val.getST());
        return this;
    }

    public STOWSTBean addBeanClass(Object val) {
        st.add("BeanClass", val);
        return this;
    }

    public STOWSTBean addTemplateName(AbstractStow val) {
        st.add("TemplateName", val.getST());
        return this;
    }

    public STOWSTBean addTemplateName(Object val) {
        st.add("TemplateName", val);
        return this;
    }

    public STOWSTBean addAccessor(AbstractStow val) {
        st.add("Accessor", val.getST());
        return this;
    }

    public STOWSTBean addAccessor(Object val) {
        st.add("Accessor", val);
        return this;
    }
}