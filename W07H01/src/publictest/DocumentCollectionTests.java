package publictest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.pagerepository.DocumentCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */

// TODO Not finished yet
// TODO Feel free to contribute

public class DocumentCollectionTests {
  
  public static Document doc0;
  static Document doc1;
  static Document doc2;
  static Document doc3;
  
  @BeforeAll
  public static void PrepareDocuments() {
    doc0 = new Document("0", "0", "a aa aaa aaaa", null, null);
    doc1 = new Document("1", "1", "", null, null);
    doc2 = new Document("2", "2", "b bb bbb", null, null);
    doc3 = new Document("3", "3", "0 c a 1", null, null);
  }
  
  
  @Test
  void equalizeAllWordCountArraysTest() {
    DocumentCollection dc1 = new DocumentCollection(2);
    
    dc1.equalizeAllWordCountArrays();
    
    dc1.add(doc1);
    dc1.equalizeAllWordCountArrays();
    assertEquals(0, dc1.getBuckets()[1].getHead().getWordCountArray().length);
    
    dc1.add(doc0);
    dc1.add(doc2);
    dc1.equalizeAllWordCountArrays();
    assertEquals(7, dc1.getBuckets()[0].getHead().getWordCountArray().length);
    assertEquals(7, dc1.getBuckets()[1].getHead().getWordCountArray().length);
    assertEquals(7, dc1.getBuckets()[1].getTail().getWordCountArray().length);
    
    dc1.add(doc3);
    dc1.equalizeAllWordCountArrays();
    
    assertEquals(10, dc1.getBuckets()[0].getHead().getWordCountArray().length);
    assertEquals(10, dc1.getBuckets()[0].getTail().getWordCountArray().length);
    assertEquals(10, dc1.getBuckets()[1].getHead().getWordCountArray().length);
    assertEquals(10, dc1.getBuckets()[1].getTail().getWordCountArray().length);
    
    assertEquals("c", dc1.getBuckets()[1].getTail().getWordCountArray()[9].getWord());
  }
}
