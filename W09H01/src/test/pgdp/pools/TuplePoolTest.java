package pgdp.pools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TuplePoolTest {
  
  @Test
  void insert() {
    Integer int0 = 123;
    String string0 = "Ladner";
    Integer int1 = 321;
    String string1 = "Jonas";
    
    Tuple<Integer, String> tuple0 = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple1 = new Tuple<>(int1, string1);
    Tuple<Integer, String> tuple2 = new Tuple<>(int1, string0);
    
    TuplePool<Integer, String> pool0 = new TuplePool<>();
    
    assertEquals(tuple0, pool0.insert(tuple0));
    assertEquals(tuple1, pool0.insert(tuple1));
    
    assertEquals(tuple0, pool0.insert(tuple0Duplicate));
    
    assertEquals(tuple2, pool0.insert(tuple2));
  }
  
  @Test
  void getByValue() {
    Integer int0 = 123;
    String string0 = "Ladner";
    Integer int1 = 321;
    String string1 = "Jonas";
    
    Tuple<Integer, String> tuple0 = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple1 = new Tuple<>(int1, string1);
    Tuple<Integer, String> tuple2 = new Tuple<>(int1, string0);
    Tuple<Integer, String> tuple3 = new Tuple<>(int0, string1);
    
    TuplePool<Integer, String> pool0 = new TuplePool<>();
    
    
    assertNull(pool0.getByValue(tuple0.getT(), tuple0.getS()));
    assertEquals(tuple0, pool0.insert(tuple0));
    assertEquals(tuple1, pool0.insert(tuple1));
    assertEquals(tuple2, pool0.insert(tuple2));
    assertEquals(tuple3, pool0.insert(tuple3));
    
    assertEquals(tuple0, pool0.getByValue(tuple0.getT(), tuple0.getS()));
    assertEquals(tuple0, pool0.getByValue(tuple0Duplicate.getT(), tuple0Duplicate.getS()));
    assertEquals(tuple1, pool0.getByValue(tuple1.getT(), tuple1.getS()));
    assertEquals(tuple2, pool0.getByValue(tuple2.getT(), tuple2.getS()));
    assertEquals(tuple3, pool0.getByValue(tuple3.getT(), tuple3.getS()));
    assertEquals(tuple3, pool0.getByValue(123, "Jonas"));
    
  }
  
  @Test
  void getNumberOfTuples() {
    Integer int0 = 123;
    String string0 = "Ladner";
    Integer int1 = 321;
    String string1 = "Jonas";
    
    Tuple<Integer, String> tuple0 = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple1 = new Tuple<>(int1, string1);
    Tuple<Integer, String> tuple2 = new Tuple<>(int1, string0);
    
    TuplePool<Integer, String> pool0 = new TuplePool<>();
    
    
    assertEquals(0, pool0.getNumberOfTuples());
    assertEquals(tuple0, pool0.insert(tuple0));
    assertEquals(1, pool0.getNumberOfTuples());
    assertEquals(tuple1, pool0.insert(tuple1));
    assertEquals(2, pool0.getNumberOfTuples());
    
    assertEquals(tuple0, pool0.insert(tuple0Duplicate));
    assertEquals(2, pool0.getNumberOfTuples());
    
    assertEquals(tuple2, pool0.insert(tuple2));
    assertEquals(3, pool0.getNumberOfTuples());
  }
}