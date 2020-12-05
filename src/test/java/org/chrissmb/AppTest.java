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
        m1.sum(m2);
        assertEquals(expected, m1);
    }

    @Test
    public void testMultiply() {
        Matrix m1 = new Matrix(new Double[][] {{2d, 3d}, {4d, 5d}});
        Matrix m2 = new Matrix(new Double[][] {{1d, 2d}, {3d, 4d}});
        Matrix expected = new Matrix(new Double[][] {{17d, 22d}, {10d, 13d}});
        m1.multiply(m2);
        assertEquals(expected, m1);
    }
}
