# Paylocity Technical Interview

This project is maven application, which contain two tests, one for backend and 
for frontend. 

## How to run it?

You can run it directly from your IDE, if its support Cucumber, or via Maven. 

### What I need to run Maven?

First make sure you have installed Java and Maven on your computer. If not, 
follow this guides to do so.

[How to install Java](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/How-do-I-install-Java-on-Windows)

[How to install Maven](https://toolsqa.com/maven/how-to-install-maven-on-windows/)

And I recommend to restart your PC after installation.

### How to proceed afterward?

1. Download code from repository.
2. In cmd line open root folder of project (i.e. C:\paylocity\).
3. Execute command 'mvn clean install' - to build project and download all dependencies
4. Then execute command 'mvn test -Dcuke=RunBackendTestCuke'.

This command will execute test set containing features for backend, in case you 
want to execute frontend test, run command 'mvn test -Dcuke=RunFrontendTestCuke'

## I do not see any execution

Currently, test is set to run windowless, meaning that whole execution is actually without 
existing GUI. It's created this way, to make it possible to run such test cases on server.

In case you want to see execution itself, it is necessary to do following:

1. Go to module frontend
2. Open class WebDriverLibrary (located in "interview.paylocity.frontend.settings.base")
3. Comment out lines 22 till 25. 

Your code will then look like this:

```java
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--disable-web-security");
        // Comment out following four lines to display browser UI
       // options.addArguments("--headless");
       // options.addArguments("--window-size=1920,1080");
       // options.addArguments("--disable-gpu");
       // options.addArguments("--disable-extensions");

        WebDriverManager.chromedriver().setup();
```

And once again, if you do not want to display GUI, uncomment it. 

## I want to run it from IDE

As each different IDE have different way how to run Cucumber tests, I would recommend
using Google in this case.

Here is at least how to run it in [IntelliJ IDEA](https://www.jetbrains.com/help/idea/running-cucumber-tests.html),
however, it is necessary to point out, that direct execution of Cucumber tests (*.feature file) is available in Ultimate
version only, and in all other version, it is necessary to run via class (RunCucumberTests in this case).
