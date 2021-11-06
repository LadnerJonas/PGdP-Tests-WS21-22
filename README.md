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
2. Add the test folder to your src-folder of your artemis java project
3. Make sure your IDE sees your added files
4. **Do not add them to your artemis git repository**
5. Run the tests and check your mistakes

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
