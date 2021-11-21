package test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pgdp.pingumath.NumberConverter.intToPinguNum;
import static pgdp.pingumath.NumberConverter.pinguNumToInt;

public class TestNumberConverter {

    @Test
    void testIntToPinguNegative() {
        String s = intToPinguNum(-2);
        assertEquals("N.D.", s);

    }

    private static Stream<Arguments> provideParametersIntToNum() {
        return Stream.of(
                Arguments.of("In", 0),
                Arguments.of("Gu", 1),
                Arguments.of("Pin", 2),
                Arguments.of("Pinguin", 21),
                Arguments.of("Gugupin", 14),
                Arguments.of("Gupinpinguin", 156),
                Arguments.of("Gupinpinguininingupinin", 37923)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersIntToNum")
        //checking multiple values for IntToPingu
    void testIntToPingu(String expectedPinguNum, int decimalNum) {
        String s = intToPinguNum(decimalNum);
        assertEquals(expectedPinguNum, s);

    }

    private static Stream<Arguments> provideParametersNumToInt() {
        return Stream.of(
                Arguments.of("In", 0),
                Arguments.of("Gu", 1),
                Arguments.of("Pin", 2),
                Arguments.of("Pinguin", 21),
                Arguments.of("Gugupin", 14),
                Arguments.of("Gupinpinguin", 156),
                Arguments.of("Gupinpinguininingupinin", 37923),
                Arguments.of("Ininpin", 2) // see https://zulip.in.tum.de/#narrow/stream/822-PGdP-W04H01--.20PinguMath/topic/F.C3.BChrende.200/near/331120
        );

    }

    //Checking multiple invalid values
    @ParameterizedTest
    @ValueSource(strings = {"123", "pinguin", "Piniguin", "abc", ""})
    void testNumToIntInvalid(String string) {
        int val = pinguNumToInt(string);
        assertEquals(-1, val);

    }

    //Testing multiple values for PinguToInt
    @ParameterizedTest
    @MethodSource("provideParametersNumToInt")
    void testNumToIntValid(String num, int expected_val) {
        int val = pinguNumToInt(num);
        assertEquals(expected_val, val);

    }
}






