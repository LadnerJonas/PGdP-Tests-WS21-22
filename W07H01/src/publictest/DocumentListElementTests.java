package publictest;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.pagerepository.DocumentListElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */

// TODO Not finished yet
// TODO Feel free to contribute


public class DocumentListElementTests {
  
  @Test
  void DocumentListElementCtorTest() {
    DocumentCollectionTests.PrepareDocuments();
    Document document0 = DocumentCollectionTests.doc0;
    
    DocumentListElement dcl1 = new DocumentListElement(document0);
    
    for (int i = 0; i < document0.getWordCountArray().length; i++) {
      assertEquals(document0.getWordCountArray()[i].getWord(), dcl1.getWordCountArray()[i].getWord());
      assertEquals(document0.getWordCountArray()[i].getCount(), dcl1.getWordCountArray()[i].getCount());
    }
    assertEquals(0.0, dcl1.getSimilarity());
  }
}
