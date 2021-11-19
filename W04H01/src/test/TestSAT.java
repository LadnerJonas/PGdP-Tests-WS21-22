package test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pgdp.pingumath.SAT.*;

public class TestSAT {

    private static Stream<Arguments> provideParametersIsPow() {
        return Stream.of(
                Arguments.of(3, 8, true),
                Arguments.of(4, 8, false),
                Arguments.of(0, 1, true),
                Arguments.of(8, 100000000, true),
                Arguments.of(10, 1024, true),
                Arguments.of(-5, 2, false),
                Arguments.of(3, -8, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersIsPow")
        //checking multiple values for IsPow
    void testIsPow(int i, long n, boolean expected) {
        long startTime = System.currentTimeMillis();

        boolean actual = isPow(i, n);

        long endTime = System.currentTimeMillis();
        // Check if method is faster than 900ms --> artemis server is slow, thus, 900 ms instead of 1 s
        if (endTime > startTime + 900L) {
            System.out.println("Your method takes too long...");
            assertEquals(true, false);
        }

        assertEquals(expected, actual);

    }

    private static Stream<Arguments> provideParametersIsCentralBin() {
        return Stream.of(
                Arguments.of(0, false),
                Arguments.of(1, true),
                Arguments.of(2, true),
                Arguments.of(924, true),
                Arguments.of(252, true),
                Arguments.of(925, false),
                Arguments.of(48620, true),
                Arguments.of(-3, false),
                Arguments.of(100000000000000000L, false),
                Arguments.of(7648690600760440L, true)/*, this test case is above 1e17
                Arguments.of(118264581564861424L, true)*/
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersIsCentralBin")
        //checking multiple values for IsCentralBin
    void testIsCentralBin(long n, boolean expected) {
        long startTime = System.currentTimeMillis();

        boolean actual = isCentralBin(n);

        long endTime = System.currentTimeMillis();
        // Check if method is faster than 900ms --> artemis server is slow, thus, 900 ms instead of 1 s
        if (endTime > startTime + 900L) {
            System.out.println("Your method takes too long...");
            assertEquals(true, false);
        }
        assertEquals(expected, actual);

    }

    private static Stream<Arguments> provideParametersIsJacobsthal() {
        return Stream.of(
                Arguments.of(-3, false),
                Arguments.of(0, true),
                Arguments.of(1, true),
                Arguments.of(2, false),
                Arguments.of(3, true),
                Arguments.of(171, true),
                Arguments.of(192153584101141163L, true),
                Arguments.of(192153584101141164L, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersIsJacobsthal")
        //checking multiple values for IsJacobsthal
    void testIsJacobsthal(long n, boolean expected) {
        long startTime = System.currentTimeMillis();

        boolean actual = isJacobsthal(n);

        long endTime = System.currentTimeMillis();
        // Check if method is faster than 900ms --> artemis server is slow, thus, 900 ms instead of 1 s
        if (endTime > startTime + 900L) {
            System.out.println("Your method takes too long...");
            assertEquals(true, false);
        }

        assertEquals(expected, actual);

    }

    private static Stream<Arguments> provideParametersIsLucasLike() {
        return Stream.of(
                Arguments.of(0, 0, 0, -3, 10, false),
                Arguments.of(0, 0, 0, 0, 0, true),
                Arguments.of(0, 0, 0, 0, 50, false),
                Arguments.of(0, 0, 2, 3, 0, true),
                Arguments.of(2, 3, 0, 0, 0, true),
                Arguments.of(0, 1, 2, 1, 2, false),
                Arguments.of(0, 1, 2, 1, 11, true),
                Arguments.of(0, 1, 1, 1, 13, true),
                Arguments.of(0, 1, 2, 1, 171, true),
                Arguments.of(0, 1, 2, 1, 1501199875790165L, true)/*,
                Arguments.of(0, 1, 2, 1, 192153584101141163L, true),// this test case is above 1e15
                //Arguments.of(0, 1, 2, 1, 192153584101141164L, false) // this test case is above 1e15
                */
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersIsLucasLike")
        //checking multiple values for IsLucasLike
    void testIsLucasLike(long x0, long x1, int a, int b, long n, boolean expected) {
        long startTime = System.currentTimeMillis();

        boolean actual = isLucasLikeSequence(x0, x1, a, b, n);

        long endTime = System.currentTimeMillis();
        // Check if method is faster than 900ms --> artemis server is slow, thus, 900 ms instead of 1 s
        if (endTime > startTime + 900L) {
            System.out.println("Your method takes too long...");
            assertEquals(true, false);
        }

        assertEquals(expected, actual);

    }
}

