package publictest;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.util.WordCount;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */

// Not finished yet
// Feel free to contribute

public class WordCountTests {
  @Test
  void WordCountCtorTest() throws IllegalAccessException {
    WordCount wc1 = new WordCount("Test1", 0);
    
    Field weight = null;
    Field normalizedWeight = null;
    try {
      weight = wc1.getClass().getDeclaredField("weight");
      normalizedWeight = wc1.getClass().getDeclaredField("normalizedWeight");
      weight.setAccessible(true);
      normalizedWeight.setAccessible(true);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
    
    assertEquals(0.0, weight.get(wc1));
    assertEquals(0.0, normalizedWeight.get(wc1));
  
    WordCount wc2 = new WordCount("Test2", 1);
  
    assertEquals(1.0, weight.get(wc2));
    assertEquals(1.0, normalizedWeight.get(wc2));
  }
}
