package org.chrissmb;

public class Matrix {

    private Double[][] data;

    public Matrix(int row, int col) {
        data = new Double[row][col];
    }

    public double get(int row, int col) {
        if (data[row][col] == null) {
            return 0d;
        }
        return data[row][col];
    }
}