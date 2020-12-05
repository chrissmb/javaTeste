package org.chrissmb;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(new Double[][] {{2d, 3d}, {4d, 5d}});
        Matrix m2 = new Matrix(new Double[][] {{1d, 2d}, {3d, 4d}});
        //Matrix expected = new Matrix(new Double[][] {{17d, 22d}, {10d, 13d}});
        m1.multiply(m2);
        System.out.println(m1);
    }
}
