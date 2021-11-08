package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pgdp.saleuine.Kaufuin;
import pgdp.saleuine.Market;
import pgdp.saleuine.Order;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// 7-8 / 10 tests successful are okay, just check the homework instruction on the failing tests again. 
// Due to the different line breaks on different operating systems some tests will fail on some systems.
// Just check if *your method* creates the right output. 
public class UnitTests {
    // Variables used to check console output
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Test
    public void OrderToStringTest() {
        // Arrange
        Order o1 = new Order(3, 4, 5);

        // Act & Assert
        Assertions.assertEquals("3 Krustentiere, 4 Sardellen und 5 Sardinen", o1.toString());
    }

    @Test
    public void OrderAddOrderTest() {
        // Arrange
        Order o1 = new Order(10, 20, 30);

        // Act
        o1.addOrder(new Order(1, 2, 3));

        // Assert
        Assertions.assertEquals(11, o1.getAmountCrustaceans());
        Assertions.assertEquals(22, o1.getAmountAnchovies());
        Assertions.assertEquals(33, o1.getAmountSardines());
    }

    @Test
    public void KaufuinGetOrderTest() {
        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act & Assert
        Assertions.assertEquals(1, k1.getOrder().getAmountCrustaceans());
        Assertions.assertEquals(2, k1.getOrder().getAmountAnchovies());
        Assertions.assertEquals(3, k1.getOrder().getAmountSardines());
    }

    @Test
    public void KaufuinGiveNewOrderTest() {
        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act
        k1.giveNewOrder(new Order(10, 20, 30));

        // Assert
        Assertions.assertEquals(10, k1.getOrder().getAmountCrustaceans());
        Assertions.assertEquals(20, k1.getOrder().getAmountAnchovies());
        Assertions.assertEquals(30, k1.getOrder().getAmountSardines());
    }

    @Test
    public void KaufuinAddToOrderTest() {
        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act
        k1.addToOrder(new Order(10, 20, 30));

        // Assert
        Assertions.assertEquals(11, k1.getOrder().getAmountCrustaceans());
        Assertions.assertEquals(22, k1.getOrder().getAmountAnchovies());
        Assertions.assertEquals(33, k1.getOrder().getAmountSardines());
    }

    @Test
    public void KaufuinGiveInformationTest() {
        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act & Assert
        Assertions.assertEquals("T1(20) hätte gerne 1 Krustentiere, 2 Sardellen und 3 Sardinen.", k1.giveInformation());
    }

    @Test
    public void KaufuinPayTest() {
        // Prepare
        System.setOut(new PrintStream(outputStreamCaptor));

        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act
        k1.pay(99);

        // Assert
        Assertions.assertEquals("T1 zahlt 99.0 und hat noch 901.0PD übrig.", outputStreamCaptor.toString()
                .trim());

        // Cleanup
        System.setOut(standardOut);
    }

        
    // This test can fail on some systems, just check the homework instruction again. 
    // Due to the different line breaks on different operating systems some tests will fail on some systems.
    // Just check if *your method* creates the right output. 
    @Test
    public void MarketServeCustomerTest() {
        // Prepare
        System.setOut(new PrintStream(outputStreamCaptor));

        // Arrange
        Market m1 = new Market(11, 22, 33);
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);


        // Act
        m1.serveCustomer(k1);

        // Assert
        // note: removes the automatic-generated last line of console output
        Assertions.assertEquals("Neue Bestellung wird angenommen: T1(20) hätte gerne 1 Krustentiere, 2 Sardellen und 3 Sardinen." + System.lineSeparator() +
                "Die Bestellung kostet 154.0PD." + System.lineSeparator() +
                "T1 zahlt 154.0 und hat noch 846.0PD übrig." + System.lineSeparator(), outputStreamCaptor.toString().substring(0, (outputStreamCaptor.toString()).length() - 2));

        // Cleanup
        System.setOut(standardOut);
    }

        
    // This test can fail on some systems, just check the homework instruction again. 
    // Due to the different line breaks on different operating systems some tests will fail on some systems.
    // Just check if *your method* creates the right output. 
    @Test
    public void MarketEndDayOutputTest() {
        // Arrange
        Market m1 = new Market(11, 22, 33);
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);


        // Act
        m1.serveCustomer(k1);
        // Prepare
        System.setOut(standardOut);
        System.setOut(new PrintStream(outputStreamCaptor));
        //
        m1.endDay();

        // Assert
        // note: this test can fail, even if the strings are equal. But as long as the strings are equal, this failed test can be ignored.
        Assertions.assertEquals(
                "Der Laden der Saleuine Claudia und Karl-Heinz hat am 1. Tag 154.0PD eingenommen." + System.lineSeparator() +
                        "Dafür wurden 1 Krustentiere, 2 Sardellen und 3 Sardinen verkauft." + System.lineSeparator() +
                        "Insgesamt hat der Laden 154.0PD eingenommen." + System.lineSeparator(),
                outputStreamCaptor.toString().substring(0, (outputStreamCaptor.toString()).length() - 2));

        // Cleanup
        System.setOut(standardOut);
    }

        
    // This test can fail on some systems, just check the homework instruction again. 
    // Due to the different line breaks on different operating systems some tests will fail on some systems.
    // Just check if *your method* creates the right output. 
    @Test
    public void MarketEndDayPropertiesTest() {
        // Arrange
        Market m1 = new Market(10, 20, 40);
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act
        m1.serveCustomer(k1);
        m1.endDay();
        m1.serveCustomer(k1);
        // Prepare
        System.setOut(standardOut);
        System.setOut(new PrintStream(outputStreamCaptor));
        //
        m1.endDay();

        // Assert
        // note: this test can fail, even if the strings are equal. But as long as the strings are equal, this failed test can be ignored.
        Assertions.assertEquals(
                "Der Laden der Saleuine Claudia und Karl-Heinz hat am 2. Tag 85.0PD eingenommen." + System.lineSeparator() +
                        "Dafür wurden 1 Krustentiere, 2 Sardellen und 3 Sardinen verkauft." + System.lineSeparator() +
                        "Insgesamt hat der Laden 255.0PD eingenommen." + System.lineSeparator(),
                outputStreamCaptor.toString().substring(0, (outputStreamCaptor.toString()).length() - 2));

        // Cleanup
        System.setOut(standardOut);

    }


}
