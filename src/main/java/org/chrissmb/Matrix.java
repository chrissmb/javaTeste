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
}