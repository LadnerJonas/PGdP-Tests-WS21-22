# PGdP-Tests-WS21-22
 PGdP-Tests-WS21/22 is a student-created repository used to share code tests.

## State of the project

Due to lack of time on my part, and the general lack of qualitative contributions, I will put the project on ice for the time being. To take the wind out of the sails of false rumors: This step is not forced by the so-called Übungsleitung.

### Important Note:

In the near future, most exercises will contain test parts, like in W03H02. Therefore, this repository will only contain tests for the other parts.  

---
## "Legal status"
>Es ist alles erlaubt, was die Lösung nicht verrät.

The so-called **__Übungsleitung__** will have an eye on this repository. But as long as the tests do not reveal the solution of the homework, this repository will be tolerated. To ensure the solution is not getting revealed, the tests should be Input/Output-Tests.

The **__Übungsleitung__** is not responsible for this repository. Therefore no support is given by the **__Übungsleitung__**. Just use the [Issue](https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note) feature here.

### Examples:

correct:
```java
// Test Area calculation
// Arrange
Rectangle r1 = new Rectangle(length: 4, width: 4)
// Act & Assert
assertEquals(16, r1.calculateArea());
```
wrong!:
```java
// Test Area calculation
// Arrange
Rectangle r1 = new Rectangle(length: 4, width: 6)
// Act & Assert
// Calculate using length * width             // <-- reveales the solution
assertEquals(4*6, r1.calculateArea()); // <-- reveales the solution
```
---

## Usage
1. Clone this repository
```
git clone https://github.com/LadnerJonas/PGdP-Tests-WS21-22.git
```
2. Check for updates
```
git pull
```
3. Add the test folder to your ```src```-folder of your artemis java project
   
   **Or** use the syslink instruction below.
5. Make sure your IDE (e.g. IntelIJ or Eclipse) sees your added files
6. Include JUnit to your Project by pressing the red lightbulb (or ```ALT + ENTER``` in IntelIJ) on one of the red underlined @Test Annotations in the test file.
 
 ![image](https://user-images.githubusercontent.com/92096842/140649461-ea039c79-37bd-4188-b91b-290491e88261.png)

8. **Do not add them to your artemis git repository**
9. Run the tests and check your mistakes

![image](https://user-images.githubusercontent.com/92096842/140649844-6da6a9bb-19c3-43e6-97c7-2d53f9bc2b2b.png)

![image](https://user-images.githubusercontent.com/92096842/140649863-b5e9cd9d-548e-422c-8d11-71f4140f8a0d.png)

#### Important note
As this is a student-driven project please keep in mind that mistakes can happen. If you think a test is wrong, please feel free to open an [Issue](https://docs.github.com/en/issues/tracking-your-work-with-issues/creating-an-issue) (it is easy!). To help fixing bugs, please add following info to your Issue:
- Operating system
- IDE (IntelIJ, Eclipse, ...)
- Where is the mistake / error
- Citation of the homework instruction

### How to use syslinks (Advanced Users only!)
#### Windows
This has to be executed in the command line, run as administrator:
 ```
$ mklink /d \path\to\src\tests \path\to\repository\tests
 ```
#### Linux/MacOS 
```
$ ln -s /path/to/repository/tests /path/to/src/
```
Shoutout to the Contributors of the [TUM_GAD_Tests_SS21-Repository](https://github.com/N0W0RK/TUM_GAD_Tests_SS21) for this great tip!

---

## How to contribute
1. Fork Repository ([Github Documentation](https://docs.github.com/en/get-started/quickstart/fork-a-repo#forking-a-repository))
2. Add / edit tests under `<EXERCISE>/src/test`
3. **Ensure no solution or homework-code is getting revealed**
4. Commit and push to your fork repository
5. Open a Pull Request to this repository ([Github Documentation](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests))
6. Wait until the changes are merged

---
## Helpful links
Write Tests using [JUnit 5](https://junit.org/junit5/docs/current/user-guide/#writing-tests)

How to use [Git](https://www.atlassian.com/de/git/tutorials/learn-git-with-bitbucket-cloud)

How to use [Github](https://guides.github.com/activities/hello-world/)

---
## Contact
Feel free to contact me via email, but consider using [google](https://google.com) beforehand: 

[jonas.ladner@tum.de](mailto:jonas.ladner@tum.de)

---
