package pgdp.pools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TupleFactoryTest {
  
  @Test
  void create() {
    Integer int0 = 123;
    String string0 = "Ladner";
    Integer int1 = 321;
    String string1 = "Jonas";
    
    Tuple<Integer, String> tuple0 = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple1 = new Tuple<>(int1, string1);
    Tuple<Integer, String> tuple2 = new Tuple<>(int1, string0);
    
    TupleFactory<Integer, String> integerStringTupleFactory0 = new TupleFactory<>();
    
    assertEquals(tuple0Duplicate, integerStringTupleFactory0.create(tuple0Duplicate.getT(), tuple0Duplicate.getS()));
    assertEquals(tuple0Duplicate, integerStringTupleFactory0.create(tuple0Duplicate.getT(), tuple0Duplicate.getS()));
    
    assertEquals(tuple0, integerStringTupleFactory0.create(tuple0.getT(), tuple0.getS()));
    assertEquals(tuple1, integerStringTupleFactory0.create(tuple1.getT(), tuple1.getS()));
    assertEquals(tuple1, integerStringTupleFactory0.create(tuple1.getT(), tuple1.getS()));
    
    assertEquals(tuple2, integerStringTupleFactory0.create(tuple2.getT(), tuple2.getS()));
  }
  
  @Test
  void intern() {
    Integer int0 = 123;
    String string0 = "Ladner";
    Integer int1 = 321;
    String string1 = "Jonas";
    
    Tuple<Integer, String> tuple0 = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple0Duplicate = new Tuple<>(int0, string0);
    Tuple<Integer, String> tuple1 = new Tuple<>(int1, string1);
    Tuple<Integer, String> tuple2 = new Tuple<>(int1, string0);
    
    TupleFactory<Integer, String> integerStringTupleFactory0 = new TupleFactory<>();
    
    assertSame(tuple0, integerStringTupleFactory0.intern(tuple0));
    assertSame(tuple1, integerStringTupleFactory0.intern(tuple1));
    
    assertSame(tuple0, integerStringTupleFactory0.intern(tuple0Duplicate));
    assertNotSame(tuple0Duplicate, integerStringTupleFactory0.intern(tuple0Duplicate));
    
    assertSame(tuple2, integerStringTupleFactory0.intern(tuple2));
  }
  
  
}