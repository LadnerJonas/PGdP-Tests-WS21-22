# PGdP-Tests-WS21-22
 PGdP-Tests-WS21/22 is a student-created repository used to share code tests.
 
---
## "Legal status"
>Es ist alles erlaubt, was die Lösung nicht verrät.

The so-called **__Übungsleitung__** will have an eye on this repository. But as long as the tests do not reveal the solution of the homework, this repository will be tolerated. To ensure the solution is not getting revealed, the tests should be Input/Output-Tests.

### Examples:

correct:
```java
// Test Area calculation
// Arrange
Rectangle r1 = new Rectangle(length: 4, width: 4)
// Act & Assert
Assert.assertEquals(16, r1.calculateArea());
```
wrong!:
```java
// Test Area calculation
// Arrange
Rectangle r1 = new Rectangle(length: 4, width: 6)
// Act & Assert
// Calculate using length * width             // <-- reveales the solution
Assert.assertEquals(4*6, r1.calculateArea()); // <-- reveales the solution
```
---

## Usage
1. Clone / Pull this repository
2. Add the test folder to your src-folder of your artemis java project or use the syslink instruction below.
3. Make sure your IDE sees your added files
4. Include JUnit to your Project by pressing ```ALT + ENTER``` on one of the red underlined @Test Annotations in the test file.
5. **Do not add them to your artemis git repository**
6. Run the tests and check your mistakes

### How to use syslinks
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
1. Fork Repository
2. Add / edit tests
3. **Ensure no solution or homework-code is getting revealed**
4. Commit and push to your fork repository
5. Open a Pull Request to this repository
6. Wait until the changes are merged

---
## Contact
Feel free to contact me via email: 

[jonas.ladner@tum.de](mailto:jonas.ladner@tum.de)

---
