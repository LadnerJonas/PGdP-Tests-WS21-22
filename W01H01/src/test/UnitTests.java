package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pgdp.hellopingu.Hello;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UnitTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Test
    public void checkMain() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Hello.main(new String[]{});
        Assertions.assertEquals("Hello World" + System.lineSeparator() + "Süßer Pingu!", outputStreamCaptor.toString()
                .trim());
        System.setOut(standardOut);
    }
}