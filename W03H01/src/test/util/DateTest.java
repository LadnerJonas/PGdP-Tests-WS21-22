package pgdp.test.util;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.util.Date;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTest {

    @Test
    void testEquals() {
        var date1 = new Date(1, 1, 1990);
        var date2 = new Date(1, 1, 1990);
        assertTrue(date1.equals(date1));
        assertTrue(date1.equals(date2));

        var date3 = new Date(1, 1, 1989);
        assertFalse(date1.equals(date3));
    }

    @Test
    void testToString() {
        var date = new Date(12, 11, 1990);
        assertTrue(Pattern.compile("(?=.*\\b12\\b)(?=.*\\b11\\b)(?=.*\\b1990).*").matcher(date.toString()).matches());
    }

}
