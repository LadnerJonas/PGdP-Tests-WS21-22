package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pgdp.pingulib.math.matrix.SquareMatrix;
import pgdp.pingulib.math.matrix.SquareMatrixAdd;
import pgdp.pingulib.math.matrix.SquareMatrixMul;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SquareMatrixTests {
  
  @ParameterizedTest
  @MethodSource("additionProvider")
  void testAddition(SquareMatrix matrixA, SquareMatrix matrixB, SquareMatrix expectedMatrix) {
    SquareMatrix matrixACopy = cloneMatrix(matrixA);
    SquareMatrix matrixBCopy = cloneMatrix(matrixB);
    
    SquareMatrix resultSequential = SquareMatrixAdd.addSequential(matrixA, matrixB);
    assertEquals(expectedMatrix, resultSequential, "Fehler bei addSequential");
    
    SquareMatrix resultParallel = SquareMatrixAdd.addParallel(matrixA, matrixB);
    assertEquals(expectedMatrix, resultParallel, "Fehler bei addParallel");
    
    // Test, dass Matrizen unverändert bleiben.
    assertEquals(matrixACopy, matrixA);
    assertEquals(matrixBCopy, matrixB);
  }
  
  
  static Stream<Arguments> additionProvider() {
    return Stream.of(
            Arguments.of(
                    longValuesToMatrix(new long[][]{
                            {-5, 100},
                            {20, 30},
                    }),
                    longValuesToMatrix(new long[][]{
                            {2, -100},
                            {2, 25},
                    }),
                    longValuesToMatrix(new long[][]{
                            {-3, 0},
                            {22, 55},
                    })
            ),
            Arguments.of(
                    longValuesToMatrix(new long[][]{
                            {1, 2, 3, 4},
                            {1, 2, 3, 4},
                            {1, 2, 3, 4},
                            {1, 2, 3, 4}
                    }),
                    longValuesToMatrix(new long[][]{
                            {1, 2, 3, 4},
                            {1, 2, 3, 4},
                            {1, 2, 3, 4},
                            {1, 2, 3, 4}
                    }),
                    longValuesToMatrix(new long[][]{
                            {2, 4, 6, 8},
                            {2, 4, 6, 8},
                            {2, 4, 6, 8},
                            {2, 4, 6, 8}
                    })
            ),
            Arguments.of(
                    new SquareMatrix( // SquareMatrix mit sehr großen Zahlen
                            IntStream.range(0, 4).mapToObj(i ->
                                    IntStream.range(0, 4).mapToObj(j ->
                                            BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TEN)
                                    ).toArray(BigInteger[]::new)
                            ).toArray(BigInteger[][]::new)
                    ),
                    new SquareMatrix(
                            IntStream.range(0, 4).mapToObj(i ->
                                    IntStream.range(0, 4).mapToObj(j ->
                                            BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TEN)
                                    ).toArray(BigInteger[]::new)
                            ).toArray(BigInteger[][]::new)
                    ),
                    new SquareMatrix(
                            IntStream.range(0, 4).mapToObj(i ->
                                    IntStream.range(0, 4).mapToObj(j ->
                                            BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.valueOf(20))
                                    ).toArray(BigInteger[]::new)
                            ).toArray(BigInteger[][]::new)
                    )
            )
            // Hier kannst du weitere Test-Matrizen hinzufügen
    );
  }
  
  @ParameterizedTest
  @MethodSource("multiplicationProvider")
  void testMultiplication(SquareMatrix matrixA, SquareMatrix matrixB, SquareMatrix expectedMatrix) {
    SquareMatrix matrixACopy = cloneMatrix(matrixA);
    SquareMatrix matrixBCopy = cloneMatrix(matrixB);
    
    SquareMatrix resultSequential = SquareMatrixMul.mulSequential(matrixA, matrixB);
    assertEquals(expectedMatrix, resultSequential, "Fehler bei mulSequential");
    
    SquareMatrix resultParallel = SquareMatrixMul.mulParallel(matrixA, matrixB);
    assertEquals(expectedMatrix, resultParallel, "Fehler bei mulParallel");
    
    // Test, dass Matrizen unverändert bleiben.
    assertEquals(matrixACopy, matrixA);
    assertEquals(matrixBCopy, matrixB);
  }
  
  static Stream<Arguments> multiplicationProvider() {
    return Stream.of(
            Arguments.of(
                    longValuesToMatrix(new long[][]{
                            {1000, 2000},
                            {5000, 6000},
                    }),
                    longValuesToMatrix(new long[][]{
                            {1000, 9000},
                            {7000, 0},
                    }),
                    longValuesToMatrix(new long[][]{
                            {15000000, 9000000},
                            {47000000, 45000000},
                    })
            ),
            Arguments.of(
                    longValuesToMatrix(new long[][]{
                            {2, 3, 5, 1},
                            {3, 7, 9, 12},
                            {51, 22, 5, 5},
                            {3, 62, 42, 11}
                    }),
                    longValuesToMatrix(new long[][]{
                            {11, 12, 13, 14},
                            {50, 60, 70, 80},
                            {9, 8, 7, 6},
                            {1, 2, 1, 2}
                    }),
                    longValuesToMatrix(new long[][]{
                            {218, 246, 272, 300},
                            {476, 552, 604, 680},
                            {1711, 1982, 2243, 2514},
                            {3522, 4114, 4684, 5276}
                    })
            ),
            Arguments.of(
                    new SquareMatrix( // SquareMatrix mit sehr großen Zahlen
                            IntStream.range(0, 4).mapToObj(i ->
                                    IntStream.range(0, 4).mapToObj(j ->
                                            BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TEN)
                                    ).toArray(BigInteger[]::new)
                            ).toArray(BigInteger[][]::new)
                    ),
                    new SquareMatrix(
                            IntStream.range(0, 4).mapToObj(i ->
                                    IntStream.range(0, 4).mapToObj(j ->
                                            BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TEN)
                                    ).toArray(BigInteger[]::new)
                            ).toArray(BigInteger[][]::new)
                    ),
                    new SquareMatrix(
                            IntStream.range(0, 4).mapToObj(i ->
                                    IntStream.range(0, 4).mapToObj(j ->
                                            BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TEN).pow(2).multiply(BigInteger.valueOf(4))
                                    ).toArray(BigInteger[]::new)
                            ).toArray(BigInteger[][]::new)
                    )
            )
            // Hier kannst du weitere Test-Matrizen hinzufügen
    );
  }
  
  // https://github.com/LadnerJonas/PGdP-Tests-WS21-22/issues/45
  @Test
  void testMultithreadingAddition() {
    resetComputeThreadCounter(SquareMatrixAdd.class);
    SquareMatrix matrixA = SquareMatrix.generateRandomMatrix(4);
    SquareMatrix matrixB = SquareMatrix.generateRandomMatrix(4);
    
    SquareMatrixAdd.addSequential(matrixA, matrixB);
    assertEquals(0, getComputeThreadCounter(SquareMatrixAdd.class));
    
    SquareMatrixAdd.addParallel(matrixA, matrixB, 2);
    assertEquals(4, getComputeThreadCounter(SquareMatrixAdd.class));
  }
  
  // https://github.com/LadnerJonas/PGdP-Tests-WS21-22/issues/45
  @Test
  void testMultithreadingMultiplication() {
    resetComputeThreadCounter(SquareMatrixMul.class);
    SquareMatrix matrixA = longValuesToMatrix(new long[][]{
            {2, 3, 5, 1},
            {3, 7, 9, 12},
            {51, 22, 5, 5},
            {3, 62, 42, 11}
    });
    SquareMatrix matrixB = longValuesToMatrix(new long[][]{
            {2, 3, 5, 1},
            {3, 7, 9, 12},
            {51, 22, 5, 5},
            {3, 62, 42, 11}
    });
    
    SquareMatrixMul.mulSequential(matrixA, matrixB);
    assertEquals(0, getComputeThreadCounter(SquareMatrixMul.class));
    
    SquareMatrixMul.mulParallel(matrixA, matrixB, 2);
    assertEquals(8, getComputeThreadCounter(SquareMatrixMul.class));
  }
  
  // https://github.com/LadnerJonas/PGdP-Tests-WS21-22/issues/45
  @Test
  void testMinDim() {
    SquareMatrix matrixA = SquareMatrix.generateRandomMatrix(4);
    SquareMatrix matrixB = SquareMatrix.generateRandomMatrix(4);
    
    resetComputeThreadCounter(SquareMatrixAdd.class);
    SquareMatrixAdd.addParallel(matrixA, matrixB, 0); // minDim 0 ist kleiner als MIN_DIM in SquareMatrixAdd
    assertEquals(4, getComputeThreadCounter(SquareMatrixAdd.class), "Fehler bei minDim in addParallel"); // Test, dass die Matrix nur einmal geviertelt wird.
    
    resetComputeThreadCounter(SquareMatrixMul.class);
    SquareMatrixMul.mulParallel(matrixA, matrixB, 0); // minDim 0 ist kleiner als MIN_DIM in SquareMatrixMul
    assertEquals(8, getComputeThreadCounter(SquareMatrixMul.class), "Fehler bei minDim in mulParallel");
  }
  
  @Test
  void testExceptionsAddParallel() {
    SquareMatrix matrix = SquareMatrix.generateRandomMatrix(4);
    SquareMatrix matrixDifferenDimension = SquareMatrix.generateRandomMatrix(8);
    
    Exception exANull = assertThrows(IllegalArgumentException.class, () -> SquareMatrixAdd.addParallel(null, matrix));
    assertEquals("A can not be null", exANull.getMessage());
    
    Exception exBNull = assertThrows(IllegalArgumentException.class, () -> SquareMatrixAdd.addParallel(matrix, null));
    assertEquals("B can not be null", exBNull.getMessage());
    
    Exception exDim = assertThrows(IllegalArgumentException.class, () -> SquareMatrixAdd.addParallel(matrix, matrixDifferenDimension));
    assertEquals("A and B have different dimensions", exDim.getMessage());
  }
  
  @Test
  void testExceptionsMulSequential() {
    SquareMatrix matrix = SquareMatrix.generateRandomMatrix(4);
    SquareMatrix matrixDifferenDimension = SquareMatrix.generateRandomMatrix(8);
    
    Exception exANull = assertThrows(IllegalArgumentException.class, () -> SquareMatrixMul.mulSequential(null, matrix));
    assertEquals("A can not be null", exANull.getMessage());
    
    Exception exBNull = assertThrows(IllegalArgumentException.class, () -> SquareMatrixMul.mulSequential(matrix, null));
    assertEquals("B can not be null", exBNull.getMessage());
    
    Exception exDim = assertThrows(IllegalArgumentException.class, () -> SquareMatrixMul.mulSequential(matrix, matrixDifferenDimension));
    assertEquals("A and B have different dimensions", exDim.getMessage());
  }
  
  @Test
  void testExceptionsMulParallel() {
    SquareMatrix matrix = SquareMatrix.generateRandomMatrix(4);
    SquareMatrix matrixDifferenDimension = SquareMatrix.generateRandomMatrix(8);
    
    Exception exANull = assertThrows(IllegalArgumentException.class, () -> SquareMatrixMul.mulParallel(null, matrix));
    assertEquals("A can not be null", exANull.getMessage());
    
    Exception exBNull = assertThrows(IllegalArgumentException.class, () -> SquareMatrixMul.mulParallel(matrix, null));
    assertEquals("B can not be null", exBNull.getMessage());
    
    Exception exDim = assertThrows(IllegalArgumentException.class, () -> SquareMatrixMul.mulParallel(matrix, matrixDifferenDimension));
    assertEquals("A and B have different dimensions", exDim.getMessage());
  }
  
  private static SquareMatrix longValuesToMatrix(long[][] values) {
    BigInteger[][] bigIntegerValues = Arrays.stream(values)
            .map(row -> Arrays.stream(row).mapToObj(BigInteger::valueOf).toArray(BigInteger[]::new))
            .toArray(BigInteger[][]::new);
    return new SquareMatrix(bigIntegerValues);
  }
  
  private static int getComputeThreadCounter(Class clazz) {
    try {
      Field computeThreadCounterField = clazz.getDeclaredClasses()[0].getDeclaredField("computeThreadCounter");
      computeThreadCounterField.setAccessible(true);
      AtomicInteger counter = (AtomicInteger) computeThreadCounterField.get(null);
      return counter.get();
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
      fail("Fehler: Hast du die computeThreadCounter-Variable verändert?");
    }
    return -1;
  }
  
  private static void resetComputeThreadCounter(Class clazz) {
    try {
      Field computeThreadCounterField = clazz.getDeclaredClasses()[0].getDeclaredField("computeThreadCounter");
      computeThreadCounterField.setAccessible(true);
      AtomicInteger counter = (AtomicInteger) computeThreadCounterField.get(null);
      counter.set(0);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
      fail("Fehler: Hast du die computeThreadCounter-Variable verändert?");
    }
  }
  
  private static SquareMatrix cloneMatrix(SquareMatrix matrix) {
    SquareMatrix clone = new SquareMatrix(matrix.getDimension());
    for (int i = 1; i <= matrix.getDimension(); i++) {
      for (int j = 1; j <= matrix.getDimension(); j++) {
        BigInteger val = matrix.get(i, j);
        clone.set(i, j, val);
      }
    }
    return clone;
  }
}
