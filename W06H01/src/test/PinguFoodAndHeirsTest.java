package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pgdp.PinguLib;
import pgdp.saleuine2.Anchovie;
import pgdp.saleuine2.Crustacean;
import pgdp.saleuine2.PinguFood;
import pgdp.saleuine2.Sardine;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */


public class PinguFoodAndHeirsTest {
  
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
  
  // PinguFood
  @Test
  void isEdiblePinguFoodTest() {
    PinguFood pf1 = new PinguFood(1, 100);
    
    assertFalse(pf1.isEdible());
  }
  
  @Test
  void toStringPinguFoodTest() {
    PinguFood pf1 = new PinguFood(1, 100);
    
    assertEquals("Alter: 1 Jahre, Gewicht: 100g", pf1.toString());
  }
  
  //Anchovie
  @Test
  void isEdibleAnchovieTest() {
    PinguFood pf1 = new Anchovie(0, 99);
    PinguFood pf2 = new Anchovie(2, 4);
    PinguFood pf3 = new Anchovie(0, 5);
    
    assertFalse(pf1.isEdible());
    assertFalse(pf2.isEdible());
    assertFalse(pf3.isEdible());
    
    PinguFood pf4 = new Anchovie(1, 5);
    
    assertTrue(pf4.isEdible());
  }
  
  @Test
  void toStringAnchovieTest() {
    PinguFood pf1 = new Anchovie(1, 100);
    
    assertEquals("Sardelle(Alter: 1 Jahre, Gewicht: 100g)", pf1.toString());
  }
  
  //Crustacean
  @Test
  void isEdibleCrustaceanest() {
    PinguFood pf1 = new Crustacean(1);
    PinguFood pf2 = new Crustacean(0);
    
    assertTrue(pf1.isEdible());
    assertTrue(pf2.isEdible());
  }
  
  @Test
  void toStringCrustaceanTest() {
    PinguFood pf1 = new Crustacean(100);
    
    assertEquals("Krill(100g)", pf1.toString());
  }
  
  //Sardine
  @Test
  void isEdibleSardineest() {
    PinguFood pf1 = new Sardine(0, 100, 14);
    PinguFood pf2 = new Sardine(1, 99, 14);
    PinguFood pf3 = new Sardine(1, 100, 13);
    
    assertFalse(pf1.isEdible());
    assertFalse(pf2.isEdible());
    assertFalse(pf3.isEdible());
    
    PinguFood pf4 = new Sardine(1, 100, 14);
    
    assertTrue(pf4.isEdible());
  }
  
  @Test
  void toStringSardineTest() {
    PinguFood pf1 = new Sardine(1, 100, 14);
    
    assertEquals("Sardine(Alter: 1 Jahre, Gewicht: 100g, Länge: 14)", pf1.toString());
  }
}
