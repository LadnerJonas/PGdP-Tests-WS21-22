package publictest;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Author;
import pgdp.searchengine.util.Date;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {
  @Test
  public void getAgeTest() {
    Author author1 = new Author("Jonas", "Ladner", "Garching-City", "tum@tum.de", new Date(27, 11, 2000));
    assertEquals(20, author1.getAge());
    
    Author author2 = new Author("Max", "Mustermann", "Garching-City", "tum@tum.de", new Date(Date.today().getDay(), Date.today().getMonth(), Date.today().getYear()));
    assertEquals(0, author2.getAge());
    
    Author author3 = new Author("Erika", "Mustermann", "Garching-City", "tum@tum.de", new Date(27, 1, 2000));
    assertEquals(21, author3.getAge());
  }
}