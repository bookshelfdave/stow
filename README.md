Stow

===

**StringTemplate4 Object WLibrary (silent W)**

* (the W is a joke)


### What is it?

Stow generates Java wrapper classes for StringTemplate4 template groups. This allows you to easily access
your templates by *method* instead of using a String keys, and will also catch certain template
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

### Integration with Maven

This needs some cleanup, it seems like my IDE picks up the generated classes
but Maven doesn't. 

Change the arguments `-java_package`, `-class_prefix`, `-dest`, and `-stg` in the monsterous snippet of XML below:

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
                            <argument>My</argument>
                        <argument>-dest</argument>
                            <argument>${project.build.sourceDirectory}/com/foo/bar</argument>
                        <argument>-stg</argument>
                            <argument>${project.build.sourceDirectory}/../resources/Foo.stg</argument>

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
