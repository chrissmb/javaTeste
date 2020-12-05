package org.chrissmb;

import java.text.DecimalFormat;

public class Matrix {

    private Double[][] data;

    public Matrix(int row, int col) {
        data = new Double[row][col];
    }

    public Matrix(Double[][] data) {
        this.data = data;
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

    public void setData(Double[][] data) {
        this.data = data;
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
                double soma = 0;
                for (int k = 0; k < data[j].length; k++) {
                    soma += get(i, k) * matrix.get(k, j);
                }
                result.set(i, j, soma);
            }
        }
        /*
        0 0 - 0 0 
        0 1 - 1 0
        
        0 0 - 0 1
        0 1 - 1 1

        1 0 - 0 0
        1 1 - 0 1
        
        1 0 - 1 0
        1 1 - 1 1
        */

        map((v, row, col) -> result.get(row, col));
    }

    public void randomize() {
        map((v, row, col) -> Math.random());
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("000.00");
        String str = "";
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                str += df.format(get(i, j)) + ", ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Matrix)) {
            return false;
        }
        Matrix matrix = (Matrix) obj;
        
        if (matrix.getColLength() != getColLength() && matrix.getRowLength() != getRowLength()) {
            return false;
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (get(i, j) != matrix.get(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }
}