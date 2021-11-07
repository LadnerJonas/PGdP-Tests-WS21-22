package test;

import org.junit.Test;
import org.junit.Assert;
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
        Assert.assertEquals("Hello World" + System.lineSeparator() + "Süßer Pingu!", outputStreamCaptor.toString()
                .trim());
        System.setOut(standardOut);
    }
}