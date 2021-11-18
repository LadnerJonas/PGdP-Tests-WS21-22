package publictest;

import org.junit.jupiter.api.Test;
import pgdp.searchengine.pagerepository.Document;
import pgdp.searchengine.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentTest {
  @Test
  public void yearsSinceReleaseTest() {
    Document document1 = new Document("Doc1", "Description1", "Cool story", new Date(31, 12, 2017), null);
    
    assertEquals(3, document1.yearsSinceRelease());
  }
  
  @Test
  public void daysSinceLastUpdateTestReturn() {
    Document document1 = new Document("Doc1", "Description1", "Cool story", new Date(Date.today().getDay(), 11, 2020), null);
    
    assertEquals(365, document1.daysSinceLastUpdate());
  }
  
  @Test
  public void daysSinceLastUpdateTestUpdate() {
    Document document1 = new Document("Doc1", "Description1", "Cool story", new Date(Date.today().getDay(), 11, 2020), null);
    
    assertEquals(365, document1.daysSinceLastUpdate());
    
    document1.setLastUpdateDate(new Date(Date.today().getDay(), 11, 2019));
  
    assertEquals(731, document1.daysSinceLastUpdate());
  
    document1.setContent("Cool story!");
    
    assertEquals(0, document1.daysSinceLastUpdate());
  }
}
