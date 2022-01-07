package pgdp.pools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    
    Tuple<Integer, String> tuple0 = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<String, Integer> tuple0Reversed = new Tuple<>(string0, int0);
    Tuple<Integer, String> tuple1 = new Tuple<>(int0 + 1, string0);
    Tuple<Integer, String> tuple2 = new Tuple<>(int0, string0 + " ");
    
    assertEquals(tuple0, tuple0Duplicate);
    assertEquals(tuple0Duplicate, tuple0);
    assertNotSame(tuple0, tuple0Duplicate);
    
    assertNotEquals(tuple0, tuple0Reversed);
    assertNotEquals(tuple0, tuple1);
    assertNotEquals(tuple0, tuple2);
    assertNotEquals(tuple1, tuple2);
    
    Tuple<Integer, String> tuple3 = new Tuple<>(null, null);
    assertEquals(tuple3, tuple3);
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
    
    assertEquals(tuple0.hashCode(), tuple0Duplicate.hashCode());
    assertNotEquals(tuple0.hashCode(), tuple1.hashCode());
  }
}