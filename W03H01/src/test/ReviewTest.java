package test;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Author;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.pagerepository.Review;
import pgdp.searchengine.util.Date;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    private Review review() {
        var author = new Author("Max", "Mustermann", "Pinguinstraße 1", "test@example.de", new Date(1, 1, 1990));
        var reviewer = new Author("Gabriella", "Musterfrau", "Pinguinstraße 2", "test2@example.tld", new Date(10, 11, 1991));
        var document = new Document("Musterdokument", "Mustertext", "Lorem Ipsum dolor sit amet, consectetur adipiscing elit", new Date(12, 11, 1990), author);

        return new Review("Ganz in Ordnung", "Gut", new Date(10, 12, 2011), reviewer, document, 10);
    }
    
    @Test
    void testConstructor() {
        int initial = Review.numberOfCreatedReviews();
        var review1 = review();
        assertEquals(initial + 1, Review.numberOfCreatedReviews());
        assertEquals(initial, review1.getPostId());
        var review2 = review();
        assertEquals(initial + 2, Review.numberOfCreatedReviews());
        assertEquals(initial + 1, review2.getPostId());
    }

    @Test
    void testToPrintText() {
        assertTrue(Pattern.compile("""
                ^(?=.*\\bMusterdokument\\b)(?=.*12)(?=.*11)(?=.*1990)(?=.*Max)(?=.*Mustermann).*
                .*10.*
                .*Ganz in Ordnung.*
                .*Gut.*
                (?=.*10)(?=.*12)(?=.*2011).*
                (?=.*\\bGabriella\\b)(?=.*\\bMusterfrau\\b).*$""".replace("\r\n", "\n")).matcher(review().toPrintText().replace("\r\n", "\n")).matches());
    }

    @Test
    void testEquals() {
        var review1 = review();
        var review2 = review();
        assertTrue(review1.equals(review1));
        assertFalse(review1.equals(review2));
    }

    @Test
    void testToString() {
        var pattern = Pattern.compile("^(?=.*\\bGanz in Ordnung\\b)(?=.*\\b10\\b)(?=.*\\b10\\b)" +
                "(?=.*\\bMusterdokument\\b)(?=.*12)(?=.*11)(?=.*1990)(?=.*Max)(?=.*Mustermann).*$");
        assertTrue(pattern.matcher(review().toString()).matches());
    }

}
