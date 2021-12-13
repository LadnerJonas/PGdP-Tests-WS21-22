package test;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pgdp.PinguLib;
import pgdp.saleuine2.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TradeOrderAndHeirsTest {
  
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
  
  // TradeOrder
  @Test
  void orderTypeTradeOrder() {
    TradeOrder to1 = new TradeOrder();
    
    assertEquals("Einzeln", to1.orderType());
  }
  
  @Test
  void toStringTradeOrder() {
    TradeOrder to1 = new TradeOrder();
    
    assertEquals("Die Bestellung(Einzeln) hat ein Gesamtgewicht von 0g und kostet 0PD.", to1.toString());
    to1.supplyOrder(new Anchovie(100, 200), BigDecimal.ONE);
    assertEquals("Die Bestellung(Einzeln) hat ein Gesamtgewicht von 200g und kostet 1PD.", to1.toString());
  }
  
  @Test
  void isOrderFulfilledTradeOrder() {
    TradeOrder to1 = new TradeOrder();
    
    assertFalse(to1.isOrderFulfilled());
    
    to1.supplyOrder(new Anchovie(100, 200), BigDecimal.ONE);
    assertTrue(to1.isOrderFulfilled());
  }
  
  @Test
  void supplyOrderTradeOrder() {
    TradeOrder to1 = new TradeOrder();
    
    assertFalse(to1.supplyOrder(new PinguFood(1, 100), BigDecimal.ONE));
    assertTrue(to1.supplyOrder(new Anchovie(1, 100), BigDecimal.ONE));
    assertFalse(to1.supplyOrder(new Anchovie(1, -100), BigDecimal.ONE));
    assertTrue(to1.isOrderFulfilled());
  }
  
  // WeightOrder
  @Test
  void orderTypeWeightOrder() {
    TradeOrder to1 = new WeightOrder(100);
    
    assertEquals("Zielgewicht: 100g", to1.orderType());
  }
  
  @Test
  void toStringWeightOrder() {
    TradeOrder to1 = new WeightOrder(100);
    
    assertEquals("Die Bestellung(Zielgewicht: 100g) hat ein Gesamtgewicht von 0g und kostet 0PD.", to1.toString());
    
    to1.supplyOrder(new Anchovie(100, 200), BigDecimal.ONE);
    assertEquals("Die Bestellung(Zielgewicht: 100g) hat ein Gesamtgewicht von 200g und kostet 1PD.", to1.toString());
  }
  
  @Test
  void isOrderFulfilledWeightOrder() {
    TradeOrder to1 = new WeightOrder(100);
    
    assertFalse(to1.isOrderFulfilled());
    
    to1.supplyOrder(new Anchovie(100, 200), BigDecimal.ONE);
    assertTrue(to1.isOrderFulfilled());
  }
  
  @Test
  void supplyOrderWeightOrder() {
    TradeOrder to1 = new WeightOrder(100);
    
    assertFalse(to1.supplyOrder(new PinguFood(1, 100), BigDecimal.ONE));
    assertTrue(to1.supplyOrder(new Anchovie(1, 50), BigDecimal.ONE));
    assertFalse(to1.isOrderFulfilled());
    assertTrue(to1.supplyOrder(new Anchovie(1, 50), BigDecimal.ONE));
    assertFalse(to1.supplyOrder(new Anchovie(1, -100), BigDecimal.ONE));
    assertTrue(to1.isOrderFulfilled());
  }
  
  // AmountOrder
  @Test
  void orderTypeAmountOrder() {
    TradeOrder to1 = new AmountOrder(59, 75, 83);
    
    assertEquals("Anzahl: [59,75,83]", to1.orderType());
  }
  
  @Test
  void toStringAmountOrder() {
    TradeOrder to1 = new AmountOrder(59, 75, 83);
    
    assertEquals("Die Bestellung(Anzahl: [59,75,83]) hat ein Gesamtgewicht von 0g und kostet 0PD.", to1.toString());
    
    to1.supplyOrder(new Anchovie(100, 200), BigDecimal.ONE);
    assertEquals("Die Bestellung(Anzahl: [59,75,83]) hat ein Gesamtgewicht von 200g und kostet 1PD.", to1.toString());
  }
  
  @Test
  void isOrderFulfilledAmountOrder() {
    TradeOrder to1 = new AmountOrder(1, 2, 3);
    
    assertFalse(to1.isOrderFulfilled());
    
    to1.supplyOrder(new Anchovie(17, 200), BigDecimal.ONE);
    assertFalse(to1.isOrderFulfilled());
    to1.supplyOrder(new Crustacean(200), BigDecimal.ONE);
    to1.supplyOrder(new Crustacean(300), BigDecimal.ONE);
    assertFalse(to1.isOrderFulfilled());
    to1.supplyOrder(new Sardine(1, 100, 15), BigDecimal.ONE);
    to1.supplyOrder(new Sardine(1, 100, 15), BigDecimal.ONE);
    to1.supplyOrder(new Sardine(1, 100, 15), BigDecimal.ONE);
    assertTrue(to1.isOrderFulfilled());
    assertFalse(to1.supplyOrder(new Sardine(1, 100, 15), BigDecimal.ONE));
    // see https://zulip.in.tum.de/#narrow/stream/846-PGdP-W06H01--.20Saleuine.20Grosshandel/topic/.E2.9C.94.20isOrderFulfilled.20AmountOrder/near/369739
    assertTrue(to1.isOrderFulfilled());
  }
  
  @Test
  void supplyOrderAmountOrder() {
    TradeOrder to1 = new AmountOrder(1, 2, 3);
    
    assertFalse(to1.supplyOrder(new PinguFood(1, 100), BigDecimal.ONE));
    
    assertTrue(to1.supplyOrder(new Anchovie(1, 100), BigDecimal.ONE));
    assertFalse(to1.isOrderFulfilled());
    
    assertFalse(to1.supplyOrder(new Anchovie(1, 100), BigDecimal.ONE)); // Target already reached
    
    assertTrue(to1.supplyOrder(new Crustacean(100), BigDecimal.ONE));
    assertTrue(to1.supplyOrder(new Crustacean(100), BigDecimal.ONE));
    assertFalse(to1.isOrderFulfilled());
    
    assertFalse(to1.supplyOrder(new Crustacean(100), BigDecimal.ONE)); // Target already reached
    
    assertFalse(to1.supplyOrder(new Sardine(0, 100, 14), BigDecimal.ONE));
    assertFalse(to1.supplyOrder(new Sardine(1, 100, 13), BigDecimal.ONE));
    
    assertTrue(to1.supplyOrder(new Sardine(1, 100, 14), BigDecimal.ONE));
    assertTrue(to1.supplyOrder(new Sardine(1, 100, 14), BigDecimal.ONE));
    assertTrue(to1.supplyOrder(new Sardine(1, 100, 14), BigDecimal.ONE));
    assertTrue(to1.isOrderFulfilled());
    
    assertFalse(to1.supplyOrder(new Sardine(1, 100, 15), BigDecimal.ONE)); // Order is fulfilled
  }
}