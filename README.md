## STOw - StringTemplate4 Object WLibrary (silent W?)

====

### What is it?

Stow generates Java wrapper classes for StringTemplate4 template groups. This allows you to easy access
your templates by *method* instead of using a String keys, and will also catch certain template
parameter problems at compile time:

  - renamed parameters
  - removed parameters
  - changes in parameter case

### Installing

```
mvn compile install
```



### Contributing

Fork this repo, create a branch with

	git checkout -b your_branch_name

Submit a pull request when your code is ready for review.
### License

http://www.apache.org/licenses/LICENSE-2.0.html

---

© 2014 Dave Parfitt
