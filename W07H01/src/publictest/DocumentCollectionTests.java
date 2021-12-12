package publictest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.pagerepository.DocumentCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
  static Document doc4;
  static Document doc5;
  static Document doc6;
  static Document doc7;
  static Document doc8;
  static Document doc9;
  
  @BeforeAll
  public static void PrepareDocuments() {
    doc0 = new Document("0", "0", "a aa aaa aaaa", null, null);
    doc1 = new Document("1", "1", "", null, null);
    doc2 = new Document("2", "2", "b bb bbb", null, null);
    doc3 = new Document("3", "3", "0 c a 1", null, null);
    // sortBuckets
    doc4 = new Document("4", "4", "a aa aaa aaaa", null, null);
    doc5 = new Document("5", "5", "c cc ccc cccc", null, null);
    doc6 = new Document("6", "6", "b bb bbb bbbb", null, null);
    doc7 = new Document("7", "7", "d", null, null);
    doc8 = new Document("8", "8", "a aa aaa", null, null);
    doc9 = new Document("9", "9", "d dd ddd", null, null);
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
  
  @Test
  void sortBucketsTest() {
    DocumentCollection dc1 = new DocumentCollection(2);
    dc1.add(doc4);
    dc1.add(doc6);
    dc1.add(doc8);
    dc1.add(doc7);
    dc1.add(doc5);
    dc1.add(doc9);

    
    dc1.query("aa");
    
    dc1.sortBuckets();
    assertEquals(doc8, dc1.getBuckets()[0].getHead().getDocument());
    assertNull(dc1.getBuckets()[0].getHead().getPre());
    assertEquals(doc8, dc1.getBuckets()[0].getHead().getNext().getPre().getDocument());
    
    assertEquals(doc4, dc1.getBuckets()[0].getHead().getNext().getDocument());
    assertEquals(doc4, dc1.getBuckets()[0].getHead().getNext().getPre().getNext().getDocument());
    assertEquals(doc4, dc1.getBuckets()[0].getHead().getNext().getNext().getPre().getDocument());
    
    assertEquals(doc6, dc1.getBuckets()[0].getHead().getNext().getNext().getDocument());
    assertEquals(doc6, dc1.getBuckets()[0].getHead().getNext().getNext().getPre().getNext().getDocument());
    assertNull(dc1.getBuckets()[0].getHead().getNext().getNext().getNext());
    assertEquals(doc6, dc1.getBuckets()[0].getTail().getDocument());
  
    
    DocumentCollection dc2 = new DocumentCollection(2);
    dc2.add(doc4);
    dc2.add(doc6);
    dc2.add(doc8);
    dc2.add(doc7);
    dc2.add(doc5);
    dc2.add(doc9);
  
    dc2.query("dd");
    dc2.sortBuckets();
    
    assertEquals(doc9, dc2.getBuckets()[1].getHead().getDocument());
    assertNull(dc2.getBuckets()[1].getHead().getPre());
    assertEquals(doc9, dc2.getBuckets()[1].getHead().getNext().getPre().getDocument());
  
    assertEquals(doc5, dc2.getBuckets()[1].getHead().getNext().getDocument());
    assertEquals(doc5, dc2.getBuckets()[1].getHead().getNext().getPre().getNext().getDocument());
    assertEquals(doc5, dc2.getBuckets()[1].getHead().getNext().getNext().getPre().getDocument());
  
    assertEquals(doc7, dc2.getBuckets()[1].getHead().getNext().getNext().getDocument());
    assertEquals(doc7, dc2.getBuckets()[1].getHead().getNext().getNext().getPre().getNext().getDocument());
    assertNull(dc2.getBuckets()[1].getHead().getNext().getNext().getNext());
    assertEquals(doc7, dc2.getBuckets()[1].getTail().getDocument());
    
  }
  
  
}
