package pgdp.test.pagerepository;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Author;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.util.Date;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentTest {

    @Test
    void testConstructor() {
        int initial = Document.numberOfCreatedDocuments();
        var document = document();
        assertEquals(initial + 1, Document.numberOfCreatedDocuments());
        assertEquals(initial, document.getDocumentId());
        var document1 = document();
        assertEquals(initial + 2, Document.numberOfCreatedDocuments());
        assertEquals(initial + 1, document1.getDocumentId());

        assertTrue(new Date(12, 11, 1990).equals(document.getReleaseDate()));
        assertTrue(new Date(12, 11, 1990).equals(document.getLastUpdateDate()));
    }

    @Test
    void testToPrintText() {
        assertTrue(Pattern.compile("""
                ^.*Musterdokument.*
                (?=.*\\bMax\\b)(?=.*\\bMustermann\\b).*
                .*Mustertext.*
                (?=\\b.*12\\b)(?=\\b.*11\\b)(?=\\b.*1990\\b).*$""".replace("\r\n", "\n")).matcher(document().toPrintText().replace("\r\n", "\n")).matches());
    }

    @Test
    void testEquals() {
        var document = document();
        assertTrue(document.equals(document));

        var document2 = document();
        assertFalse(document2.equals(document));
    }

    @Test
    void testToString() {
        var pattern = Pattern.compile("^(?=.*\\bMusterdokument\\b)(?=.*12)(?=.*11)(?=.*1990)(?=.*\\bMax\\b)(?=.*\\bMustermann).*$");
        assertTrue(pattern.matcher(document().toString()).matches());
    }

    private Document document() {
        var author = new Author("Max", "Mustermann", "Pinguinstraße 1", "test@example.de", new Date(1, 1, 1990));
        return new Document("Musterdokument", "Mustertext", "Lorem Ipsum dolor sit amet, consectetur adipiscing elit", new Date(12, 11, 1990), author);
    }

}
