package publictest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.AbstractLinkedDocument;
import pgdp.searchengine.pagerepository.DummyLinkedDocument;
import pgdp.searchengine.pagerepository.LinkedDocument;
import pgdp.searchengine.pagerepository.LinkedDocumentCollection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/*
 If you think a test is wrong, please open an Issue on GitHub.
 See https://github.com/LadnerJonas/PGdP-Tests-WS21-22#important-note-1
 */


public class LinkedDocumentCollectionTest {
  public static LinkedDocument doc0;
  public static LinkedDocument doc1;
  public static LinkedDocument doc2;
  public static LinkedDocument doc3;
  
  @BeforeAll
  public static void PrepareDocuments() {
    doc0 = new LinkedDocument("0", "0", "link::zzzz abcdwasd", null, null, "abc", 2);
    doc1 = new LinkedDocument("1", "1", "link::zeze link::zeze1", null, null, "abcd", 2);
    doc2 = new LinkedDocument("2", "2", "link::abc", null, null, "abcde", 2);
    doc3 = new LinkedDocument("3", "3", "link::zzzz", null, null, "abcdef", 2);
  }
  
  @Test
  public void testUpdateIncomingLinks() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Method updateIncomingLinksReflection = LinkedDocumentCollection.class.getDeclaredMethod("updateIncomingLinks", LinkedDocument.class, String[].class);
    updateIncomingLinksReflection.setAccessible(true); // Reflection is not allowed in your own tests
    
    LinkedDocumentCollection collection = new LinkedDocumentCollection(2);
    collection.add(doc0);
    collection.add(doc1);
    collection.add(doc2);
    collection.add(doc3);
    
    assertEquals(4, collection.size());
    
    updateIncomingLinksReflection.invoke(collection, doc0, doc0.getOutgoingAddresses());
    assertEquals(5, collection.size());
    LinkedDocument l_doc0 = (LinkedDocument) collection.find(doc0.getAddress());
    assertEquals(1, l_doc0.getOutgoingLinks().size());
    
    updateIncomingLinksReflection.invoke(collection, doc1, doc1.getOutgoingAddresses());
    assertEquals(7, collection.size());
    LinkedDocument l_doc1 = (LinkedDocument) collection.find(doc1.getAddress());
    assertEquals(2, l_doc1.getOutgoingLinks().size());
    
    updateIncomingLinksReflection.invoke(collection, doc2, doc2.getOutgoingAddresses());
    assertEquals(7, collection.size());
    LinkedDocument l_doc2 = (LinkedDocument) collection.find(doc2.getAddress());
    assertNotNull(l_doc2.getOutgoingLinks().find(0));
    
    updateIncomingLinksReflection.invoke(collection, doc3, doc3.getOutgoingAddresses());
    assertEquals(7, collection.size());
    LinkedDocument l_doc3 = (LinkedDocument) collection.find(doc3.getAddress());
    assertEquals(1, l_doc3.getOutgoingLinks().size());
    
    AbstractLinkedDocument zzzz = collection.find("zzzz");
    assertEquals(2, zzzz.getIncomingLinks().size());
    assertTrue(zzzz instanceof DummyLinkedDocument);
    
    AbstractLinkedDocument zeze = collection.find("zeze");
    assertEquals(1, zeze.getIncomingLinks().size());
    assertTrue(zeze instanceof DummyLinkedDocument);
    
    AbstractLinkedDocument zeze1 = collection.find("zeze1");
    assertEquals(1, zeze1.getIncomingLinks().size());
    assertTrue(zeze1 instanceof DummyLinkedDocument);
  }
  
  @Test
  public void testUpdateOutgoingLinks() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Method updateOutgoingLinksReflection = LinkedDocumentCollection.class.getDeclaredMethod("updateOutgoingLinks", AbstractLinkedDocument.class);
    updateOutgoingLinksReflection.setAccessible(true); // Reflection is not allowed in your own tests
    
    Method incomingLinksReflection = LinkedDocumentCollection.class.getDeclaredMethod("updateIncomingLinks", LinkedDocument.class, String[].class);
    incomingLinksReflection.setAccessible(true); // Reflection is not allowed in your own tests
    
    LinkedDocumentCollection collection = new LinkedDocumentCollection(2);
    
    collection.add(doc1);
    collection.add(doc2);
    collection.add(doc3);
    
    DummyLinkedDocument dummy = new DummyLinkedDocument("abc", 2);
    collection.add(dummy);
    
    incomingLinksReflection.invoke(collection, doc2, doc2.getOutgoingAddresses());
    assertEquals(4, collection.size());
    assertEquals(1, doc2.getOutgoingLinks().size());
    assertTrue(doc2.getOutgoingLinks().find(doc0.getAddress()) instanceof DummyLinkedDocument);
    
    updateOutgoingLinksReflection.invoke(collection, doc0);
    assertTrue(collection.size() == 3 || collection.size() == 4);
    assertEquals(1, doc2.getOutgoingLinks().size());
    assertTrue(doc2.getOutgoingLinks().find(doc0.getAddress()) instanceof LinkedDocument);
    
    collection.add(doc0);
    assertEquals(4, collection.size());
    assertEquals(1, doc2.getOutgoingLinks().size());
    assertTrue(doc2.getOutgoingLinks().find(doc0.getAddress()) instanceof LinkedDocument);
  }
  
}
