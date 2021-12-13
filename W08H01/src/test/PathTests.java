package test;

import org.junit.jupiter.api.Test;
import pgdp.maze.Direction;
import pgdp.maze.Path;
import pgdp.maze.Position;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pgdp.maze.Direction.*;

public class PathTests {
  
  @Test
  public void testEmpty() {
    Path path = new Path();
//    assertNull(path.getStep(0));
    assertEquals("[]", path.toString());
  }
  
  @Test
  public void testBasicPath() {
    Direction[] directions = {
            DOWN, DOWN, UP, UP, LEFT, RIGHT, RIGHT, LEFT
    };
    Path path = new Path();
    for (Direction direction : directions) {
      path.prepend(direction);
    }
    for (int i = 0; i < directions.length; i++) {
      Direction direction = directions[i];
      assertEquals(direction, path.getStep(directions.length - i - 1));
    }
    assertEquals("[LEFT, RIGHT, RIGHT, LEFT, UP, UP, DOWN, DOWN]", path.toString());
    Set<Position> positions = path.toPositionSet(new Position(0, 0));
    matchAll(positions,
            positionOf(0, 0),
            positionOf(0, -1),
            positionOf(0, 0),
            positionOf(0, 1),
            positionOf(-1, 0),
            positionOf(-2, 0),
            positionOf(-1, 0),
            positionOf(0, 0)
    );
  }
  
  private Position positionOf(int i, int j) {
    return new Position(i, j);
  }
  
  @SafeVarargs
  private <T> boolean matchAll(Set<T> set, T... array) {
    if (set.size() != array.length) {
      return false;
    }
    int i = 0;
    for (Object t : set) {
      if (!t.equals(array[i++])) {
        return false;
      }
    }
    return true;
  }
}