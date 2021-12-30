package test.pgdp.pools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pgdp.pools.*;
class TupleTest {
  
  @Test
  void getT() {
    Integer int0 = 123;
    String string0 = "Ladner";
    
    Tuple<Integer, String> tuple0 = new Tuple<>(int0, string0);
    
    assertEquals(int0, tuple0.getT());
    assertEquals(string0, tuple0.getS());
  }
  
  @Test
  void testEquals() {
    Integer int0 = 123;
    String string0 = "Ladner";
    
    Tuple<Object, Object> tuple0 = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<Object, Object> tuple0Reversed = new Tuple<>(string0, int0);
    
    assertEquals(tuple0, tuple0Duplicate);
    assertNotEquals(tuple0, tuple0Reversed);
    
    Tuple<Integer, String> tuple2 = new Tuple<>(null, null);
    assertEquals(tuple2, tuple2);
  }

  @Test
  void testHashCode() {
    Integer int0 = 123;
    String string0 = "Ladner";
    Integer int1 = 321;
    String string1 = "Jonas";
    
    Tuple<Integer, String> tuple0 = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple1 = new Tuple<>(int1, string1);
    
    assertEquals(tuple0, tuple0Duplicate);
    assertNotEquals(tuple0.hashCode(), tuple1.hashCode());
  }
}