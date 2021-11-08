package test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pgdp.rectangles.Rectangle;
import pgdp.rectangles.Vector2D;

public class UnitTests {

    @Test
    public void TestAreaCalculation() {
        Vector2D v1 = new Vector2D(0, 2);
        Vector2D v2 = new Vector2D(3, 0);

        Rectangle r1 = new Rectangle(v1, v2);

        Assertions.assertEquals(6.0, r1.calculateArea(), 0.000001);
    }

    @Test
    public void TestRectangleShiftByVector() {
        Vector2D v1 = new Vector2D(0, 2);
        Vector2D v2 = new Vector2D(3, 0);

        Rectangle r1 = new Rectangle(v1, v2);

        Vector2D shiftVector1 = new Vector2D(-2, -1.5);

        r1.shiftBy(shiftVector1);

        Assertions.assertEquals("Rectangle spanned by points [-2.0, 0.5] and [1.0, -1.5].", r1.toString());
    }
}
