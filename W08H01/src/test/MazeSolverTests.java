package test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pgdp.maze.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTests {

  @ParameterizedTest
  @MethodSource
  void testMaze(Maze maze, boolean possible) {
    Path path = MazeSolver.solveMaze(maze);
    verifyPath(maze, path, possible);
  }

  static Stream<Arguments> testMaze() {
    return Stream.of(
      Arguments.of(easyMaze(), true),
      Arguments.of(mediumMaze(), true),
      Arguments.of(hardMaze(), true),
      Arguments.of(impossibleMaze(), false)
    );
  }

  static Maze easyMaze() {
    return mazeFromName("easy");
  }

  static Maze mediumMaze() {
    return mazeFromName("medium");
  }

  static Maze hardMaze() {
    return mazeFromName("hard");
  }

  static Maze impossibleMaze() {
    return mazeFromName("impossible");
  }

  private final static String MAZE_SAVE_LOCATION = "src/test/mazes/%s.txt";

  static Maze mazeFromName(String difficulty) {
    String uri = MAZE_SAVE_LOCATION.formatted(difficulty);
    return MazeParser.parseFromFile(uri);
  }

  private void verifyPath(Maze maze, Path path, boolean possible) {
    if (path == null) {
      // verify that no path exists
      assertFalse(possible, "No path found for solvable maze ┗|｀O′|┛");
      return;
    }
    assertTrue(possible, "You found a path for an unsolvable maze, magic?");
    // assuming that toPositionSet is not "faking" a path
    Position[] positions = path.toPositionSet(maze.getEntrance()).toArray(Position[]::new);
    for (Position position : positions) {
      assertFalse(isWall(maze, position), "The path taken leads us into a wall :( (at " + position + ")");
    }
    assertEquals(maze.getEntrance(), positions[0], "The path does not include the entrance");
    assertFalse(duplicatesIn(positions), "The path taken contains duplicates (path " + Arrays.toString(positions) +")");
    assertEquals(maze.getExit(), positions[positions.length - 1], "The path does not include the exit");
    System.out.println("Path entered seems to be correct");
    System.out.println(maze.toString(path));
  }

  private <T> boolean duplicatesIn(T[] array) {
    Set<T> set = new HashSet<>();
    for (T t : array) {
      if (set.contains(t)) {
        return true;
      }
      set.add(t);
    }
    return false;
  }

  private boolean isWall(Maze maze, Position position) {
    return isWall(maze, position.getI(), position.getJ());
  }

  private boolean isWall(Maze maze, int i, int j) {
    try {
      Field field = Maze.class.getDeclaredField("tiles");
      if (!field.isAccessible()) {
        field.setAccessible(true);
      }
      Object[][] tiles = (Object[][]) field.get(maze);
      Enum<?> tileState = (Enum<?>) tiles[i][j];
      return tileState.name().equals("WALL");
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return false;
  }
}
