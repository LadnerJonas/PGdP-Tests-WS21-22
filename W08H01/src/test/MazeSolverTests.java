package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pgdp.maze.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTests {
  
  @ParameterizedTest
  @MethodSource
  void testSolveMazeFrom(Maze maze, Position position, boolean possible) {
    Path path = MazeSolver.solveMazeFrom(maze, position);
    
    verifyPathFromPosition(maze, path, position, possible);
  }
  
  static Stream<Arguments> testSolveMazeFrom() {
    return Stream.of(
            Arguments.of(mazeFromName("pathThroughEntranceMaze"), new Position(1, 1), true),
            Arguments.of(mazeFromName("impossible-1"), new Position(1, 1), false),
            Arguments.of(mazeFromName("easy"), new Position(8, 3), false), //https://zulip.in.tum.de/#narrow/stream/864-PGdP-W08H01--.20Rekursive.20Pfade/topic/solveMazeFrom.28.29/near/401361
            Arguments.of(mazeFromName("noEntranceMaze"), new Position(0, 4), true), //https://zulip.in.tum.de/#narrow/stream/864-PGdP-W08H01--.20Rekursive.20Pfade/topic/.E2.9C.94.20No.20Entrance.20.3F.20-.20solveMazeFrom.28.29/near/401446
            Arguments.of(mazeFromName("impossible-2"), new Position(1, 3), false),
            Arguments.of(mazeFromName("easy"), new Position(8, 4), true));
  }
  
  
  @ParameterizedTest
  @MethodSource
  void testMaze(Maze maze, boolean possible) {
    Path path = MazeSolver.solveMaze(maze);
    verifyPath(maze, path, possible);
  }
  
  static Stream<Arguments> testMaze() {
    return Stream.of(
            Arguments.of(mazeFromName("easy"), true),
            Arguments.of(mazeFromName("medium"), true),
            Arguments.of(mazeFromName("hard"), true),
            Arguments.of(mazeFromName("roomMaze-debug-1"), true),
            Arguments.of(mazeFromName("roomMaze-debug-2"), true),
            Arguments.of(mazeFromName("roomMaze-3"), true),
            Arguments.of(mazeFromName("roomMaze-2"), true),
            Arguments.of(mazeFromName("roomMaze-1"), true),
            Arguments.of(mazeFromName("mixedMaze-1"), true),
            Arguments.of(mazeFromName("cornerMaze-tl"), true),
            Arguments.of(mazeFromName("cornerMaze-tr"), true),
            Arguments.of(mazeFromName("cornerMaze-bl"), true),
            Arguments.of(mazeFromName("cornerMaze-br"), true),
            Arguments.of(mazeFromName("edgeMaze-1"), true),
            Arguments.of(mazeFromName("noBorderMaze-1"), true),
            Arguments.of(mazeFromName("noBorderMaze-2"), true)
            //, du kannst aber davon ausgehen, dass der exit immer vom entrance aus erreichbar ist
            // Arguments.of(mazeFromName("impossible-1"), false)
    );
  }
  
  @Test
  void testNull() {
    assertNull(MazeSolver.solveMazeFrom(null, null));
    assertNull(MazeSolver.solveMazeFrom(mazeFromName("easy"), null));
    assertNull(MazeSolver.solveMaze(null));
  }
  
  @Test
  void testExcellenceMaze() {
    /* This test maze is quite huge. This can lead to stackoverflow errors, when running multiple tests in parallel.
     * So try running this test alone, if it fails.
     * Hinweis: Wir werden eure Lösungen nur mit Labyrinthen mit Seitenlängen <= 20 testen, also maximal mit einem 20x20-Labyrinth.
     */
    Maze excellenceMaze = mazeFromName("exzellenz");
    Path path = MazeSolver.solveMaze(excellenceMaze);
    verifyPath(excellenceMaze, path, true);
  }
  
  private final static String MAZE_SAVE_LOCATION = "src/test/mazes/%s.txt";
  
  static Maze mazeFromName(String difficulty) {
    String uri = MAZE_SAVE_LOCATION.formatted(difficulty);
    return MazeParser.parseFromFile(uri);
  }
  
  private void verifyPathFromPosition(Maze maze, Path path, Position startPosition, boolean possible) {
    if (path == null) {
      // verify that no path exists
      assertFalse(possible, "No path found for solvable maze ┗|｀O′|┛");
      return;
    }
    assertTrue(possible, "You found a path for an unsolvable maze, magic?");
    // assuming that toPositionSet is not "faking" a path
    Position[] positions = path.toPositionSet(startPosition).toArray(Position[]::new);
    for (Position position : positions) {
      assertFalse(isWall(maze, position), "The path taken leads us into a wall :( (at " + position + ")");
    }
    assertFalse(duplicatesIn(positions), "The path taken contains duplicates (path " + Arrays.toString(positions) + ")");
    if (maze.getEntrance() != null) {
      System.out.println(maze.toString(path));
      if (Objects.equals(path.toString(), "[]")) {
        assertEquals(1, path.toPositionSet(maze.getEntrance()).size(), "The path most likely contains useless movement");
      } else {
        assertEquals(path.toString().split(",").length + 1, path.toPositionSet(maze.getEntrance()).size(), "The path most likely contains useless movement");
      }
    }
    assertTrue(positionComparator(maze.getExit(), positions[0]) || positionComparator(maze.getExit(), positions[positions.length - 1]) ||
            Arrays.stream(positions).anyMatch(x -> positionComparator(maze.getExit(), x)), "The path does not include the exit");
    System.out.println("Path (above) entered seems to be correct. Due to the nature of the ToString method the path can not be visualised\n");
    //System.out.println(maze.toString(path));
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
    System.out.println(maze.toString(path));
    assertTrue(maze.getEntrance() == positions[0] || maze.getEntrance() == positions[positions.length - 1] || Arrays.stream(positions).anyMatch(x -> x == maze.getEntrance()), "The path does not include the entrance");
    assertFalse(duplicatesIn(positions), "The path taken contains duplicates (path " + Arrays.toString(positions) + ")");
    assertTrue(maze.getEntrance() == positions[0] || maze.getEntrance() == positions[positions.length - 1] || Arrays.stream(positions).anyMatch(x -> x == maze.getEntrance()), "The path does not include the exit");
    assertEquals(path.toString().split(",").length + 1, path.toPositionSet(maze.getEntrance()).size(), "The path most likely contains useless movement");
    System.out.println("Path (above) entered seems to be correct\n");
    //System.out.println(maze.toString(path));
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
  
  private static boolean positionComparator(Position p1, Position p2) {
    return p1.getI() == p2.getI() && p1.getJ() == p2.getJ();
    
  }
}
