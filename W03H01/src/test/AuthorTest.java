package test;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Author;
import pgdp.searchengine.util.Date;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {

    private static final Author AUTHOR = new Author("Max", "Mustermann", "Pinguinstraße 1",
            "max.mustermann@example.de", new Date(12, 10, 1990));

    @Test
    void testConstructor() {
        assertEquals("Max", AUTHOR.getFirstName());
        assertEquals("Mustermann", AUTHOR.getLastName());
        assertEquals("Pinguinstraße 1", AUTHOR.getAddress());
        assertEquals("max.mustermann@example.de", AUTHOR.getEmail());
        assertTrue(new Date(12, 10, 1990).equals(AUTHOR.getBirthday()));
    }

    @Test
    void testToPrintText() {
        assertTrue(Pattern.compile("""
                ^(?=.*\\bMax\\b)(?=.*\\bMustermann\\b).*
                (?=\\b.*12\\b)(?=\\b.*10\\b)(?=\\b.*1990\\b).*
                .*Pinguinstraße 1.*
                .*max\\.mustermann@example\\.de.*$""".replace("\r\n", "\n")).matcher(AUTHOR.toPrintText().replace("\r\n", "\n")).matches());
    }

    @Test
    void testEquals() {
        assertTrue(AUTHOR.equals(AUTHOR));
        assertTrue(AUTHOR.equals(new Author("Max", "Mustermann", "Pinguinstraße 1",
                "max.mustermann@example.de", new Date(12, 10, 1990))));
        assertFalse(AUTHOR.equals(new Author("Max", "Mustermann", "Pinguinstraße 1",
                "max.mustermann@example.de", new Date(12, 10, 1989))));
        assertFalse(AUTHOR.equals(new Author("Jonas", "Mustermann", "Pinguinstraße 1",
                "max.mustermann@example.de", new Date(12, 10, 1990))));
        assertFalse(AUTHOR.equals(new Author("Max", "Musterfrau", "Pinguinstraße 1",
                "max.mustermann@example.de", new Date(12, 10, 1990))));

        // siehe https://github.com/LadnerJonas/PGdP-Tests-WS21-22/issues/9
        /*assertFalse(AUTHOR.equals(new Author("Max", "Mustermann", "Pinguinstraße 1",
                "max.mustermann@example.de", null)));*/
    }

    @Test
    void testToString() {
        assertTrue(Pattern.compile("^(?=.*\\bMax\\b)(?=.*\\bMustermann\\b).*").matcher(AUTHOR.toString()).matches());
    }


}
