package publictest;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.pagerepository.DocumentListElement;
import pgdp.searchengine.util.WordCount;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentListElementTests {
  @Test
  void DocumentListElementCtorTest() throws IllegalAccessException {
    Document doc1 = new Document("Titel1", "Beschreibung1", "a aa aaa aaaa", null, null);
    
    DocumentListElement dcl1 = new DocumentListElement(doc1);
    
    Field wordCountArray = null;
    Field similarity = null;
    try {
      wordCountArray = dcl1.getClass().getDeclaredField("wordCountArray");
      similarity = dcl1.getClass().getDeclaredField("similarity");
      wordCountArray.setAccessible(true);
      similarity.setAccessible(true);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
    
    for (int i = 0; i < doc1.getWordCountArray().length; i++) {
      assertEquals(doc1.getWordCountArray()[i].getWord(), ((WordCount[]) wordCountArray.get(dcl1))[i].getWord());
      assertEquals(doc1.getWordCountArray()[i].getCount(), ((WordCount[]) wordCountArray.get(dcl1))[i].getCount());
    }
    assertEquals(0.0, similarity.get(dcl1));
  }
}
