package test;

import org.junit.Test;
import org.junit.Assert;
import pgdp.saleuine.Kaufuin;
import pgdp.saleuine.Market;
import pgdp.saleuine.Order;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// If some tests with console output fail chance \r\n to \n and see if that helps
public class UnitTests {
    // Variables used to check console output
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Test
    public void OrderToStringTest() {
        // Arrange
        Order o1 = new Order(3, 4, 5);

        // Act & Assert
        Assert.assertEquals("3 Krustentiere, 4 Sardellen und 5 Sardinen", o1.toString());
    }

    @Test
    public void OrderAddOrderTest() {
        // Arrange
        Order o1 = new Order(10, 20, 30);

        // Act
        o1.addOrder(new Order(1, 2, 3));

        // Assert
        Assert.assertEquals(11, o1.getAmountCrustaceans());
        Assert.assertEquals(22, o1.getAmountAnchovies());
        Assert.assertEquals(33, o1.getAmountSardines());
    }

    @Test
    public void KaufuinGetOrderTest() {
        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act & Assert
        Assert.assertEquals(1, k1.getOrder().getAmountCrustaceans());
        Assert.assertEquals(2, k1.getOrder().getAmountAnchovies());
        Assert.assertEquals(3, k1.getOrder().getAmountSardines());
    }

    @Test
    public void KaufuinGiveNewOrderTest() {
        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act
        k1.giveNewOrder(new Order(10, 20, 30));

        // Assert
        Assert.assertEquals(10, k1.getOrder().getAmountCrustaceans());
        Assert.assertEquals(20, k1.getOrder().getAmountAnchovies());
        Assert.assertEquals(30, k1.getOrder().getAmountSardines());
    }

    @Test
    public void KaufuinAddToOrderTest() {
        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act
        k1.addToOrder(new Order(10, 20, 30));

        // Assert
        Assert.assertEquals(11, k1.getOrder().getAmountCrustaceans());
        Assert.assertEquals(22, k1.getOrder().getAmountAnchovies());
        Assert.assertEquals(33, k1.getOrder().getAmountSardines());
    }

    @Test
    public void KaufuinGiveInformationTest() {
        // Arrange
        Kaufuin k1 = new Kaufuin("T1", 20, 1000.0, 1, 2, 3);

        // Act & Assert
        Assert.assertEquals("T1(20) hätte gerne 1 Krustentiere, 2 Sardellen und 3 Sardinen.", k1.giveInformation());
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
        Assert.assertEquals("T1 zahlt 99.0 und hat noch 901.0PD übrig.", outputStreamCaptor.toString()
                .trim());

        // Cleanup
        System.setOut(standardOut);
    }

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
        Assert.assertEquals("Neue Bestellung wird angenommen: T1(20) hätte gerne 1 Krustentiere, 2 Sardellen und 3 Sardinen." + System.lineSeparator() +
                "Die Bestellung kostet 154.0PD." + System.lineSeparator() +
                "T1 zahlt 154.0 und hat noch 846.0PD übrig." + System.lineSeparator(), outputStreamCaptor.toString().substring(0, (outputStreamCaptor.toString()).length() - 2));

        // Cleanup
        System.setOut(standardOut);
    }

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
        Assert.assertEquals(
                "Der Laden der Saleuine Claudia und Karl-Heinz hat am 1. Tag 154.0PD eingenommen." + System.lineSeparator() +
                        "Dafür wurden 1 Krustentiere, 2 Sardellen und 3 Sardinen verkauft." + System.lineSeparator() +
                        "Insgesamt hat der Laden 154.0PD eingenommen." + System.lineSeparator(),
                outputStreamCaptor.toString().substring(0, (outputStreamCaptor.toString()).length() - 2));

        // Cleanup
        System.setOut(standardOut);
    }

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
        Assert.assertEquals(
                "Der Laden der Saleuine Claudia und Karl-Heinz hat am 2. Tag 85.0PD eingenommen." + System.lineSeparator() +
                        "Dafür wurden 1 Krustentiere, 2 Sardellen und 3 Sardinen verkauft." + System.lineSeparator() +
                        "Insgesamt hat der Laden 255.0PD eingenommen." + System.lineSeparator(),
                outputStreamCaptor.toString().substring(0, (outputStreamCaptor.toString()).length() - 2));

        // Cleanup
        System.setOut(standardOut);

    }


}
