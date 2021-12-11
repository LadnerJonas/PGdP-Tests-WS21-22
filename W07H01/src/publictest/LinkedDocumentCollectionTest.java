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

public class LinkedDocumentCollectionTests {
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
        Method method = LinkedDocumentCollection.class.getDeclaredMethod("updateIncomingLinks", LinkedDocument.class, String[].class);
        method.setAccessible(true);

        LinkedDocumentCollection collection = new LinkedDocumentCollection(2);
        collection.add(doc0);
        collection.add(doc1);
        collection.add(doc2);
        collection.add(doc3);

        assertEquals(4, collection.size());

        method.invoke(collection, doc0, doc0.getOutgoingAddresses());
        assertEquals(5, collection.size());
        LinkedDocument l_doc0 = (LinkedDocument)collection.find(doc0.getAddress());
        assertEquals(1, l_doc0.getOutgoingLinks().size());

        method.invoke(collection, doc1, doc1.getOutgoingAddresses());
        assertEquals(7, collection.size());
        LinkedDocument l_doc1 = (LinkedDocument)collection.find(doc1.getAddress());
        assertEquals(2, l_doc1.getOutgoingLinks().size());

        method.invoke(collection, doc2, doc2.getOutgoingAddresses());
        assertEquals(7, collection.size());
        LinkedDocument l_doc2 = (LinkedDocument)collection.find(doc2.getAddress());
        assertNotNull(l_doc2.getOutgoingLinks().find(0));

        method.invoke(collection, doc3, doc3.getOutgoingAddresses());
        assertEquals(7, collection.size());
        LinkedDocument l_doc3 = (LinkedDocument)collection.find(doc3.getAddress());
        assertEquals(1, l_doc3.getOutgoingLinks().size());

        AbstractLinkedDocument zzzz = collection.find("zzzz");
        assertEquals(2, zzzz.getIncomingLinks().size());

        AbstractLinkedDocument zeze = collection.find("zeze");
        assertEquals(1, zeze.getIncomingLinks().size());

        AbstractLinkedDocument zeze1 = collection.find("zeze1");
        assertEquals(1, zeze1.getIncomingLinks().size());
    }

    @Test
    public void testUpdateOutgoingLinks() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = LinkedDocumentCollection.class.getDeclaredMethod("updateOutgoingLinks", AbstractLinkedDocument.class);
        method.setAccessible(true);

        Method incomingLinks = LinkedDocumentCollection.class.getDeclaredMethod("updateIncomingLinks", LinkedDocument.class, String[].class);
        incomingLinks.setAccessible(true);

        LinkedDocumentCollection collection = new LinkedDocumentCollection(2);
        //collection.add(doc0);
        collection.add(doc1);
        collection.add(doc2);
        collection.add(doc3);
        DummyLinkedDocument dummy = new DummyLinkedDocument("abc", 2);

        collection.add(dummy);
        incomingLinks.invoke(collection, doc2, doc2.getOutgoingAddresses());
        assertEquals(4, collection.size());
        assertEquals(1, doc2.getOutgoingLinks().size());
        assertTrue(doc2.getOutgoingLinks().find(doc0.getAddress()) instanceof DummyLinkedDocument);
        method.invoke(collection, doc0);
        assertEquals(3, collection.size());
        assertEquals(1, doc2.getOutgoingLinks().size());
        assertTrue(doc2.getOutgoingLinks().find(doc0.getAddress()) instanceof LinkedDocument);
        collection.add(doc0);
        assertEquals(4, collection.size());
        assertEquals(1, doc2.getOutgoingLinks().size());
        assertTrue(doc2.getOutgoingLinks().find(doc0.getAddress()) instanceof LinkedDocument);
    }

}
