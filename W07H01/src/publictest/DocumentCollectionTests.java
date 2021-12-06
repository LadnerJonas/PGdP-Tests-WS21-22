package publictest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.pagerepository.DocumentCollection;
import pgdp.searchengine.pagerepository.DocumentListElement;
import pgdp.searchengine.util.WordCount;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentCollectionTests {
  
  static Document doc0;
  static Document doc1;
  static Document doc2;
  static Document doc3;
  
  @BeforeAll
  static void PrepareDocuments() {
    doc0 = new Document("0", "0", "a aa aaa aaaa", null, null);
    doc1 = new Document("1", "1", "", null, null);
    doc2 = new Document("2", "2", "b bb bbb", null, null);
    doc3 = new Document("3", "3", "0 c a 1", null, null);
  }
  
  
  @Test
  void equalizeAllWordCountArraysTest() throws InvocationTargetException, IllegalAccessException {
    DocumentCollection dc1 = new DocumentCollection(2);
    
    DocumentListElement dcl1 = new DocumentListElement(null);
    Field wordCountArray = null;
    Method getCompleteWordCountArray = null;
    Method equalizeAllWordCountArrays = null;
    try {
      wordCountArray = dcl1.getClass().getDeclaredField("wordCountArray");
      getCompleteWordCountArray = dc1.getClass().getDeclaredMethod("getCompleteWordCountArray");
      equalizeAllWordCountArrays = dc1.getClass().getDeclaredMethod("equalizeAllWordCountArrays");
      
      wordCountArray.setAccessible(true);
      getCompleteWordCountArray.setAccessible(true);
      equalizeAllWordCountArrays.setAccessible(true);
    } catch (NoSuchMethodException | NoSuchFieldException e) {
      e.printStackTrace();
    }
    
    equalizeAllWordCountArrays.invoke(dc1);
    
    dc1.add(doc1);
    equalizeAllWordCountArrays.invoke(dc1);
    assertEquals(0, ((WordCount[]) wordCountArray.get(dc1.getBuckets()[1].getHead())).length);
    
    dc1.add(doc0);
    dc1.add(doc2);
    equalizeAllWordCountArrays.invoke(dc1);
    assertEquals(7, ((WordCount[]) wordCountArray.get(dc1.getBuckets()[0].getHead())).length);
    assertEquals(7, ((WordCount[]) wordCountArray.get(dc1.getBuckets()[1].getHead())).length);
    assertEquals(7, ((WordCount[]) wordCountArray.get(dc1.getBuckets()[1].getTail())).length);
    
    dc1.add(doc3);
    equalizeAllWordCountArrays.invoke(dc1);
    
    assertEquals(10, ((WordCount[]) wordCountArray.get(dc1.getBuckets()[0].getHead())).length);
    assertEquals(10, ((WordCount[]) wordCountArray.get(dc1.getBuckets()[0].getTail())).length);
    assertEquals(10, ((WordCount[]) wordCountArray.get(dc1.getBuckets()[1].getHead())).length);
    assertEquals(10, ((WordCount[]) wordCountArray.get(dc1.getBuckets()[1].getTail())).length);
    
    assertEquals("c", ((WordCount[]) wordCountArray.get(dc1.getBuckets()[1].getTail()))[9].getWord());
    assertEquals(10, ((WordCount[]) getCompleteWordCountArray.invoke(dc1, null)).length);
  }
}
