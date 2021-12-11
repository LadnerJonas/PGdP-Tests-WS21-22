package publictest;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.util.WordCount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */

// TODO Not finished yet
// TODO Feel free to contribute

public class WordCountTests {
  
  @Test
  void friendlyReminder() {
    /*
     * This failing test is just a friendly reminder that this collection of tests incomplete.
     * Thus, you will have to debug your code and find your bugs yourself.
     * Otherwise, tests can be added by opening a pull request via GitHub.
     * */
    assertFalse(true, "\u001B[36m" + """
            
            This failing test is just a friendly reminder that this collection of tests is incomplete.\s
            Thus, you will have to debug your code and find your bugs yourself.
            Otherwise, tests can be added by opening a pull request on GitHub.
            """);
  }
  
  @Test
  void WordCountCtorTest() {
    WordCount wc1 = new WordCount("Test1", 0);
    
    assertEquals(0.0, wc1.getWeight());
    assertEquals(0.0, wc1.getNormalizedWeight());
    
    WordCount wc2 = new WordCount("Test2", 1);
    
    assertEquals(1.0, wc2.getWeight());
    assertEquals(1.0, wc2.getNormalizedWeight());
  }
}
