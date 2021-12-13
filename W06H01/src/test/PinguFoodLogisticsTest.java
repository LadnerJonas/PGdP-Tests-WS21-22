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

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */


public class PinguFoodLogisticsTest {
  private PrintStream old;
  private ByteArrayOutputStream baos = new ByteArrayOutputStream();
  
  @BeforeEach
  void ResetRandomSeed() {
    // Using Reflection to access private fields outside of class.
    // (Psst! Do not tell the so-called Übungsleitung)
    PinguLib pinguLib = new PinguLib();
    Class obj = pinguLib.getClass();
    try {
      Field field = obj.getDeclaredField("rand");
      field.setAccessible(true);
      field.set(pinguLib, null);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
    PinguLib.setRandom();
  }
  
  @BeforeEach
  void PrepareConsole() {
    baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);
  }
  
  @AfterEach
  void ResetConsole() {
    System.out.flush();
    System.setOut(old);
  }
  
  @Test
  void registerUnusedFoodAndprintWasteStatisticsTest() {
    PinguFoodLogistics pfl1 = new PinguFoodLogistics(BigDecimal.valueOf(5), BigDecimal.valueOf(10), BigDecimal.valueOf(15));
    pfl1.printWasteStatistics();
    
    assertTrue(baos.toString().contains("Bisher konnten 0 Tiere mit einem Gesamtgewicht von 0g nicht verwertet werden.\n" +
            "Claudia und Karl-Heinz ist dadurch ein Profit von 0PD entgangen."));
    
    pfl1.acceptNewOrder(new AmountOrder(1, 2, 3));
    pfl1.clearOrderBook();
    PrepareConsole();
    pfl1.printWasteStatistics();
    
    //assertEquals("", baos.toString()); can be used to check what your console output is
    assertTrue(baos.toString().contains("Bisher konnten 15 Tiere mit einem Gesamtgewicht von 2373g nicht verwertet werden.\n" +
            "Claudia und Karl-Heinz ist dadurch ein Profit von 35595PD entgangen."));
    
    pfl1.acceptNewOrder(new WeightOrder(1000));
    pfl1.clearOrderBook();
    PrepareConsole();
    pfl1.printWasteStatistics();
    
    assertTrue(baos.toString().contains("Bisher konnten 37 Tiere mit einem Gesamtgewicht von 4959g nicht verwertet werden.\n" +
            "Claudia und Karl-Heinz ist dadurch ein Profit von 73735PD entgangen."));
    
    pfl1.acceptNewOrder(new TradeOrder());
    pfl1.clearOrderBook();
    PrepareConsole();
    pfl1.printWasteStatistics();
    
    assertTrue(baos.toString().contains("Bisher konnten 38 Tiere mit einem Gesamtgewicht von 5055g nicht verwertet werden.\n" +
            "Claudia und Karl-Heinz ist dadurch ein Profit von 75175PD entgangen."));
  }
  
  @Test
  void clearOrderBookTest() {
    PinguFoodLogistics pfl1 = new PinguFoodLogistics(BigDecimal.valueOf(5), BigDecimal.valueOf(10), BigDecimal.valueOf(15));
    pfl1.clearOrderBook();
    
    //assertEquals("", baos.toString()); can be used to check what your console output is
    assertTrue(baos.toString().contains("Es können 0 Bestellungen abgearbeitet werden."));
    PrepareConsole();
    
    pfl1.acceptNewOrder(new AmountOrder(3, 0, 5));
    pfl1.clearOrderBook();
    
    assertTrue(baos.toString().contains("Es können 1 Bestellungen abgearbeitet werden."));
    assertTrue(baos.toString().contains("Die Bestellung(Anzahl: [3,0,5]) hat ein Gesamtgewicht von 1107g und kostet 15685PD."));
    PrepareConsole();
    pfl1.printWasteStatistics();
    assertTrue(baos.toString().contains("Bisher konnten 17 Tiere mit einem Gesamtgewicht von 2289g nicht verwertet werden.\n" +
            "Claudia und Karl-Heinz ist dadurch ein Profit von 33525PD entgangen."));
    PrepareConsole();
    
    pfl1.acceptNewOrder(new TradeOrder());
    pfl1.acceptNewOrder(new WeightOrder(0));
    pfl1.acceptNewOrder(new AmountOrder(0, 0, 0));
    pfl1.clearOrderBook();
    assertTrue(baos.toString().contains("Es können 3 Bestellungen abgearbeitet werden."));
    assertTrue(baos.toString().contains("Die Bestellung(Einzeln) hat ein Gesamtgewicht von 2g und kostet 20PD."));
    PrepareConsole();
    pfl1.printWasteStatistics();
    assertTrue(baos.toString().contains("Bisher konnten 17 Tiere mit einem Gesamtgewicht von 2289g nicht verwertet werden.\n" +
            "Claudia und Karl-Heinz ist dadurch ein Profit von 33525PD entgangen."));
    
    pfl1.acceptNewOrder(new TradeOrder());
    pfl1.acceptNewOrder(new WeightOrder(500));
    pfl1.acceptNewOrder(new AmountOrder(9, 7, 8));
    pfl1.clearOrderBook();
    assertTrue(baos.toString().contains("Es können 3 Bestellungen abgearbeitet werden."));
    assertTrue(baos.toString().contains("Die Bestellung(Einzeln) hat ein Gesamtgewicht von 10g und kostet 100PD."));
    assertTrue(baos.toString().contains("Die Bestellung(Zielgewicht: 500g) hat ein Gesamtgewicht von 597g und kostet 7180PD."));
    assertTrue(baos.toString().contains("Die Bestellung(Anzahl: [9,7,8]) hat ein Gesamtgewicht von 2029g und kostet 27340PD."));
    PrepareConsole();
    pfl1.printWasteStatistics();
    assertTrue(baos.toString().contains("Bisher konnten 32 Tiere mit einem Gesamtgewicht von 4452g nicht verwertet werden.\n" +
            "Claudia und Karl-Heinz ist dadurch ein Profit von 65510PD entgangen."));
    
  }
  
  
}