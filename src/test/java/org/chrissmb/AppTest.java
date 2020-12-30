package org.chrissmb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testSum() {
        Matrix m1 = new Matrix(new Double[][] {{2d, 3d}, {4d, 5d}});
        Matrix m2 = new Matrix(new Double[][] {{1d, 2d}, {3d, 4d}});
        Matrix expected = new Matrix(new Double[][] {{3d, 5d}, {7d, 9d}});
        Matrix actual = m1.sum(m2);
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiply() {
        Matrix m1 = new Matrix(new Double[][] {{2d, 3d}, {4d, 5d}});
        Matrix m2 = new Matrix(new Double[][] {{1d, 2d}, {3d, 4d}});
        Matrix expected = new Matrix(new Double[][] {{11d, 16d}, {19d, 28d}});
        Matrix actual = m1.multiply(m2);
        assertEquals(expected, actual);
    }
}
