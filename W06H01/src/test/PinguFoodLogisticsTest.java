package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pgdp.PinguLib;
import pgdp.saleuine2.AmountOrder;
import pgdp.saleuine2.PinguFoodLogistics;
import pgdp.saleuine2.TradeOrder;
import pgdp.saleuine2.WeightOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */


public class PinguFoodLogisticsTest {
  private final static String lineSeparator = System.lineSeparator();
  private PrintStream old;
  private ByteArrayOutputStream baos = new ByteArrayOutputStream();

  @BeforeEach
  void resetSeed() {
    // Using Reflection to access private fields outside of class.
    // (Psst! Do not tell the so-called Übungsleitung)
    try {
      Field field = PinguLib.class.getDeclaredField("rand");
      field.setAccessible(true);
      field.set(null/* field is static */, null);
    } catch (NoSuchFieldException | IllegalAccessException exception) {
      exception.printStackTrace();
    }
    PinguLib.setRandom();
  }

  @BeforeEach
  void prepareConsole() {
    baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);
  }

  void resetConsole() {
    System.out.flush();
  }

  @AfterEach
  void unsetConsole() {
    System.out.flush();
    System.setOut(old);
  }

  @Test
  void registerUnusedFoodAndprintWasteStatisticsTest() {
    PinguFoodLogistics pfl1 = new PinguFoodLogistics(BigDecimal.valueOf(5), BigDecimal.valueOf(10), BigDecimal.valueOf(15));
    pfl1.printWasteStatistics();

    assertContains(baos, "Bisher konnten 0 Tiere mit einem Gesamtgewicht von 0g nicht verwertet werden." + lineSeparator +
      "Claudia und Karl-Heinz ist dadurch ein Profit von 0PD entgangen.");
    pfl1.acceptNewOrder(new AmountOrder(1, 2, 3));
    pfl1.clearOrderBook();
    resetConsole();
    pfl1.printWasteStatistics();

    //assertEquals("", baos.toString()); can be used to check what your console output is
    assertContains(baos, "Bisher konnten 15 Tiere mit einem Gesamtgewicht von 2373g nicht verwertet werden." + lineSeparator +
      "Claudia und Karl-Heinz ist dadurch ein Profit von 35595PD entgangen.");

    pfl1.acceptNewOrder(new WeightOrder(1000));
    pfl1.clearOrderBook();
    resetConsole();
    pfl1.printWasteStatistics();

    assertContains(baos, "Bisher konnten 37 Tiere mit einem Gesamtgewicht von 4959g nicht verwertet werden." + lineSeparator +
      "Claudia und Karl-Heinz ist dadurch ein Profit von 73735PD entgangen.");

    pfl1.acceptNewOrder(new TradeOrder());
    pfl1.clearOrderBook();
    resetConsole();
    pfl1.printWasteStatistics();

    assertContains(baos, "Bisher konnten 38 Tiere mit einem Gesamtgewicht von 5055g nicht verwertet werden." + lineSeparator +
      "Claudia und Karl-Heinz ist dadurch ein Profit von 75175PD entgangen.");
  }

  @Test
  void clearOrderBookTest() {
    PinguFoodLogistics pfl1 = new PinguFoodLogistics(BigDecimal.valueOf(5), BigDecimal.valueOf(10), BigDecimal.valueOf(15));
    pfl1.clearOrderBook();

    //assertEquals("", baos.toString()); can be used to check what your console output is
    assertContains(baos, "Es können 0 Bestellungen abgearbeitet werden.");
    resetConsole();

    pfl1.acceptNewOrder(new AmountOrder(3, 0, 5));
    pfl1.clearOrderBook();

    assertContains(baos, "Es können 1 Bestellungen abgearbeitet werden.");
    assertContains(baos, "Die Bestellung(Anzahl: [3,0,5]) hat ein Gesamtgewicht von 1107g und kostet 15685PD.");
    resetConsole();
    pfl1.printWasteStatistics();
    assertContains(baos, "Bisher konnten 17 Tiere mit einem Gesamtgewicht von 2289g nicht verwertet werden." + lineSeparator +
      "Claudia und Karl-Heinz ist dadurch ein Profit von 33525PD entgangen.");
    resetConsole();

    pfl1.acceptNewOrder(new TradeOrder());
    pfl1.acceptNewOrder(new WeightOrder(0));
    pfl1.acceptNewOrder(new AmountOrder(0, 0, 0));
    pfl1.clearOrderBook();
    assertContains(baos, "Es können 3 Bestellungen abgearbeitet werden.");
    assertContains(baos, "Die Bestellung(Einzeln) hat ein Gesamtgewicht von 2g und kostet 20PD.");
    resetConsole();
    pfl1.printWasteStatistics();
    assertContains(baos, "Bisher konnten 17 Tiere mit einem Gesamtgewicht von 2289g nicht verwertet werden." + lineSeparator +
      "Claudia und Karl-Heinz ist dadurch ein Profit von 33525PD entgangen.");

    pfl1.acceptNewOrder(new TradeOrder());
    pfl1.acceptNewOrder(new WeightOrder(500));
    pfl1.acceptNewOrder(new AmountOrder(9, 7, 8));
    pfl1.clearOrderBook();
    assertContains(baos, "Es können 3 Bestellungen abgearbeitet werden.");
    assertContains(baos, "Die Bestellung(Einzeln) hat ein Gesamtgewicht von 10g und kostet 100PD.");
    assertContains(baos, "Die Bestellung(Zielgewicht: 500g) hat ein Gesamtgewicht von 597g und kostet 7180PD.");
    assertContains(baos, "Die Bestellung(Anzahl: [9,7,8]) hat ein Gesamtgewicht von 2029g und kostet 27340PD.");
    resetConsole();
    pfl1.printWasteStatistics();
    assertContains(baos, "Bisher konnten 32 Tiere mit einem Gesamtgewicht von 4452g nicht verwertet werden." + lineSeparator +
      "Claudia und Karl-Heinz ist dadurch ein Profit von 65510PD entgangen.");

  }

  private void assertContains(ByteArrayOutputStream baos, String needle) {
    String haystack = baos.toString();
    if (!haystack.contains(needle)) {
      fail("\"%s\" (length %d) does not contain \"%s\"(length %d) "
        .formatted(
          haystack.replace("\n", "\\n").replace("\r", "\\r"),
          haystack.length(),
          needle.replace("\n", "\\n").replace("\r", "\\r"),
          needle.length()
        )
      );
    }
  }
}
