package pgdp.pools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TuplePoolTest {
  
  @Test
  void insert() {
    Integer int0 = 123;
    String string0 = "Ladner";
    Integer int1 = 321;
    String string1 = "Jonas";
    
    Tuple<Object, Object> tuple0 = new Tuple<>(int0, string0);
    Tuple<Object, Object> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<Object, Object> tuple1 = new Tuple<>(int1, string1);
    
    Tuple<Object, Object> tuple2 = new Tuple<>(int1, string0);
    Tuple<Object, Object> tuple2Inverted = new Tuple<>(string0, int1);
    
    TuplePool<Object, Object> pool0 = new TuplePool<>();
    
    assertSame(pool0.insert(tuple0), tuple0);
    assertSame(pool0.insert(tuple0Duplicate), tuple0);
    
    assertSame(pool0.insert(tuple1), tuple1);
    
    assertSame(pool0.insert(tuple2), tuple2);
    assertSame(pool0.insert(tuple2Inverted), tuple2Inverted);
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
    assertSame(pool0.insert(tuple0), tuple0);
    assertSame(pool0.insert(tuple1), tuple1);
    assertSame(pool0.insert(tuple2), tuple2);
    assertSame(pool0.insert(tuple3), tuple3);
    
    assertSame(pool0.getByValue(tuple0.getT(), tuple0.getS()), tuple0);
    assertSame(pool0.getByValue(tuple0Duplicate.getT(), tuple0Duplicate.getS()), tuple0);
    
    assertSame(pool0.getByValue(tuple1.getT(), tuple1.getS()), tuple1);
    assertSame(pool0.getByValue(tuple2.getT(), tuple2.getS()), tuple2);
    assertSame(pool0.getByValue(tuple3.getT(), tuple3.getS()), tuple3);
    assertSame(pool0.getByValue(123, "Jonas"), tuple3);
    
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
    
    assertSame(pool0.insert(tuple0), tuple0);
    assertEquals(1, pool0.getNumberOfTuples());
    assertSame(pool0.insert(tuple1), tuple1);
    assertEquals(2, pool0.getNumberOfTuples());
    
    assertSame(pool0.insert(tuple0Duplicate), tuple0);
    assertEquals(2, pool0.getNumberOfTuples());
    
    assertSame(pool0.insert(tuple2), tuple2);
    assertEquals(3, pool0.getNumberOfTuples());
  }
}