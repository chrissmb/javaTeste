package org.chrissmb;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Matrix matrix = new Matrix(3,3);
        matrix.randomize();
        System.out.println(matrix);
    }
}
