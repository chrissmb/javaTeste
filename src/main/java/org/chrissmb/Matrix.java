package org.chrissmb;

import java.util.function.Function;

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

    public void set(int row, int col, double v) {
        data[row][col] = v;
    }

    public int getRowLength() {
        return data.length;
    }

    public int getColLength() {
        return data[0].length;
    }

    public void map(ApplyMatrix fn) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = fn.apply(get(i, j), i, j);
            }
        }
    }

    public void sum(Matrix matrix) {
        map((v, row, col) -> v + matrix.get(row, col));
    }

    public void multiply(Matrix matrix) {
        Matrix result = new Matrix(data.length, data[0].length);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                for (int k = 0; j < data[j].length; k++) {
                    result.set(i, j, get(i, k) * matrix.get(k, i));
                }
            }
        }

        map((v, row, col) -> result.get(row, col));
    }


}