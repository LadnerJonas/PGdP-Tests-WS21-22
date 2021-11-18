package publictest;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Author;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.pagerepository.Review;
import pgdp.searchengine.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {
  @Test
  public void getAgeTextTest() {
    Author author1 = new Author("Jonas", "Ladner", "Garching-City", "tum@tum.de", new Date(27, 11, 2000));
    Document document1 = new Document("PGdP-Tests-WS21-22", "PGdP-Tests-WS21/22 is a student-created repository used to share code tests.", "public tests", Date.today(), author1);
    Review review1 = new Review("Great Repository!", "self-praise stinks", Date.today(), author1, document1, 10);
    
    assertEquals("Vor 0 Tagen gepostet", review1.getAgeText());
  }
}
