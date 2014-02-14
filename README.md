Stow
===

[StringTemplate 4](http://www.stringtemplate.org/) Object Wrapper

StringTemplate is © Copyright StringTemplate / Terence Parr 2014

Stow is © Copyright Dave Parfitt 2014

### What is it?

Stow generates Java wrapper classes for StringTemplate4 template groups. This allows you to easily access
your templates by *method* instead of using String based keys, and will also catch certain template
parameter problems at compile time:

  - renamed parameters
  - removed parameters
  - changes in parameter case

### Installing

```
<dependency>
  <groupId>com.github.metadave</groupId>
  <artifactId>Stow</artifactId>
  <version>0.1-SNAPSHOT</version>
</dependency>
```

### Example

The following example takes a StringTemplate group file (`Greeting.stg`), and generates two classes: `DemoGreeting` and `DemoFancyName`. Finally, a demo program uses these classes to generate output.


Greeting.stg

```
Greeting(YourName, MyName) ::= <<
Hello <YourName>, my name is <MyName>!
>>

FancyName(Name) ::= <<
  ~~~<Name>~~~
>>
```


Run stow with the following parameters:

- -java_package com.foo.bar
- -class_prefix Demo
- -dest /path/to/some_directory
- -stg resources/Greeting.stg



which generates:


<pre style="background:#fff;color:#000"><span style="color:#ff5600">package</span> <span style="color:#ff5600">com.foo.bar</span>;
<span style="color:#ff5600">import</span> <span style="color:#ff5600">org.stringtemplate.v4.ST</span>;
<span style="color:#ff5600">import</span> <span style="color:#ff5600">org.stringtemplate.v4.STGroup</span>;
<span style="color:#ff5600">import</span> <span style="color:#ff5600">com.metadave.stow.AbstractStow</span>;

<span style="color:#919191">// generated by stow</span>
<span style="color:#919191">// https://github.com/metadave/stow</span>
<span style="color:#ff5600">public</span> <span style="color:#ff5600">class</span> <span style="color:#21439c">DemoGreeting</span> <span style="color:#ff5600">implements</span> AbstractStow {
    ST st;
    <span style="color:#ff5600">public</span> <span style="color:#ff5600">static</span> <span style="color:#ff5600">final</span> <span style="color:#ff5600">String</span> templateName <span style="color:#ff5600">=</span> <span style="color:#00a33f">"Greeting"</span>;

    <span style="color:#ff5600">public</span> <span style="color:#21439c">DemoGreeting</span>(<span style="color:#ff5600">STGroup</span> g) {
        st <span style="color:#ff5600">=</span> g<span style="color:#ff5600">.</span>getInstanceOf(templateName);
    }

    <span style="color:#ff5600">public</span> <span style="color:#ff5600">ST</span> <span style="color:#21439c">getST</span>() {
        <span style="color:#ff5600">return</span> st;
    }

    <span style="color:#ff5600">public</span> <span style="color:#ff5600">DemoGreeting</span> <span style="color:#21439c">addYourName</span>(<span style="color:#ff5600">AbstractStow</span> val) {
        st<span style="color:#ff5600">.</span>add(<span style="color:#00a33f">"YourName"</span>, val<span style="color:#ff5600">.</span>getST());
        <span style="color:#ff5600">return</span> this;
    }

    <span style="color:#ff5600">public</span> <span style="color:#ff5600">DemoGreeting</span> <span style="color:#21439c">addYourName</span>(<span style="color:#ff5600">Object</span> val) {
        st<span style="color:#ff5600">.</span>add(<span style="color:#00a33f">"YourName"</span>, val);
        <span style="color:#ff5600">return</span> this;
    }

    <span style="color:#ff5600">public</span> <span style="color:#ff5600">DemoGreeting</span> <span style="color:#21439c">addMyName</span>(<span style="color:#ff5600">AbstractStow</span> val) {
        st<span style="color:#ff5600">.</span>add(<span style="color:#00a33f">"MyName"</span>, val<span style="color:#ff5600">.</span>getST());
        <span style="color:#ff5600">return</span> this;
    }

    <span style="color:#ff5600">public</span> <span style="color:#ff5600">DemoGreeting</span> <span style="color:#21439c">addMyName</span>(<span style="color:#ff5600">Object</span> val) {
        st<span style="color:#ff5600">.</span>add(<span style="color:#00a33f">"MyName"</span>, val);
        <span style="color:#ff5600">return</span> this;
    }
}
</pre>

and

<pre style="background:#fff;color:#000"><span style="color:#ff5600">package</span> <span style="color:#ff5600">com.foo.bar</span>;
<span style="color:#ff5600">import</span> <span style="color:#ff5600">org.stringtemplate.v4.ST</span>;
<span style="color:#ff5600">import</span> <span style="color:#ff5600">org.stringtemplate.v4.STGroup</span>;
<span style="color:#ff5600">import</span> <span style="color:#ff5600">com.metadave.stow.AbstractStow</span>;

<span style="color:#919191">// generated by stow</span>
<span style="color:#919191">// https://github.com/metadave/stow</span>
<span style="color:#ff5600">public</span> <span style="color:#ff5600">class</span> <span style="color:#21439c">DemoFancyName</span> <span style="color:#ff5600">implements</span> AbstractStow {
    ST st;
    <span style="color:#ff5600">public</span> <span style="color:#ff5600">static</span> <span style="color:#ff5600">final</span> <span style="color:#ff5600">String</span> templateName <span style="color:#ff5600">=</span> <span style="color:#00a33f">"FancyName"</span>;

    <span style="color:#ff5600">public</span> <span style="color:#21439c">DemoFancyName</span>(<span style="color:#ff5600">STGroup</span> g) {
        st <span style="color:#ff5600">=</span> g<span style="color:#ff5600">.</span>getInstanceOf(templateName);
    }

    <span style="color:#ff5600">public</span> <span style="color:#ff5600">ST</span> <span style="color:#21439c">getST</span>() {
        <span style="color:#ff5600">return</span> st;
    }

    <span style="color:#ff5600">public</span> <span style="color:#ff5600">DemoFancyName</span> <span style="color:#21439c">addName</span>(<span style="color:#ff5600">AbstractStow</span> val) {
        st<span style="color:#ff5600">.</span>add(<span style="color:#00a33f">"Name"</span>, val<span style="color:#ff5600">.</span>getST());
        <span style="color:#ff5600">return</span> this;
    }

    <span style="color:#ff5600">public</span> <span style="color:#ff5600">DemoFancyName</span> <span style="color:#21439c">addName</span>(<span style="color:#ff5600">Object</span> val) {
        st<span style="color:#ff5600">.</span>add(<span style="color:#00a33f">"Name"</span>, val);
        <span style="color:#ff5600">return</span> this;
    }
}
</pre>


The snippet of code below shows basic usage of the generated code:


<pre style="background:#fff;color:#000"><span style="color:#ff5600">STGroup</span> stg <span style="color:#ff5600">=</span> <span style="color:#ff5600">new</span> <span style="color:#ff5600">STGroupFile</span>(<span style="color:#00a33f">"Greeting.stg"</span>);

<span style="color:#ff5600">DemoGreeting</span> g <span style="color:#ff5600">=</span> <span style="color:#ff5600">new</span> <span style="color:#ff5600">DemoGreeting</span>(stg);
g<span style="color:#ff5600">.</span>addMyName(<span style="color:#00a33f">"Dave"</span>);

<span style="color:#ff5600">DemoFancyName</span> you <span style="color:#ff5600">=</span> <span style="color:#ff5600">new</span> <span style="color:#ff5600">DemoFancyName</span>(stg);
you<span style="color:#ff5600">.</span>addName(<span style="color:#00a33f">"User"</span>);
<span style="color:#919191">// added as an ST object using the AbstractStow interface</span>
g<span style="color:#ff5600">.</span>addYourName(you);

<span style="color:#ff5600">System</span><span style="color:#ff5600">.</span>out<span style="color:#ff5600">.</span>println(g<span style="color:#ff5600">.</span>getST()<span style="color:#ff5600">.</span>render());
</pre>

When the code above is run, the following text is displayed in the console:

	Hello ~~~User~~~, my name is Dave!

### Integration with Maven

This needs some cleanup, it seems like my IDE picks up the generated classes
but Maven doesn't. 

Change the arguments `-java_package`, `-class_prefix`, `-dest`, and `-stg` in the monsterous snippet of XML below, and add to your pom.xml. 

```
  <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>1.1.1</version>
                <executions>
                    <execution>
                        <id>RunStow</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>exec</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <executable>java</executable>
                    <arguments>
                        <argument>-classpath</argument>
                        <classpath/>
                        <argument>com.metadave.stow.Stow</argument>

                        <argument>-java_package</argument>
                            <argument>com.foo.bar</argument>
                        <argument>-class_prefix</argument>
                            <argument>Demo</argument>
                        <argument>-dest</argument>
                            <argument>${project.build.sourceDirectory}/com/foo/bar</argument>
                        <argument>-stg</argument>
                            <argument>${project.build.sourceDirectory}/../resources/Demo.stg</argument>

                    </arguments>
                </configuration>
            </plugin>
        </plugins>
    </build>
```


### Contributing

Fork this repo, create a branch with

	git checkout -b your_branch_name

Submit a pull request when your code is ready for review.
### License

http://www.apache.org/licenses/LICENSE-2.0.html

---

© 2014 Dave Parfitt
