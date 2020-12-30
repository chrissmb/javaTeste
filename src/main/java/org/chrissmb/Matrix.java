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

    public Double[][] getData() {
        return data;
    }

    public Matrix map(ApplyMatrix fn) {
        Matrix m = new Matrix(getRowLength(), getColLength());
        for (int i = 0; i < m.getRowLength(); i++) {
            for (int j = 0; j < m.getColLength(); j++) {
                m.set(i, j, fn.apply(get(i, j), i, j));
            }
        }
        return m;
    }

    public Matrix sum(Matrix matrix) {
        if (getColLength() != matrix.getColLength() || getRowLength() != matrix.getRowLength()) {
            throw new RuntimeException("Matrizes com largura ou tamanho diferente conforme regra de soma de matriz.");
        }
        return map((v, row, col) -> v + matrix.get(row, col));
    }

    public Matrix multiply(Matrix matrix) {
        if (getRowLength() != matrix.getColLength() && getColLength() != matrix.getRowLength()) {
            throw new RuntimeException("Matrizes com largura ou tamanho diferente conforme regra de multiplicação de matriz.");
        }

        Matrix result = new Matrix(data.length, data[0].length);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                double soma = 0;
                for (int k = 0; k < data[j].length; k++) {
                    soma += (get(i, k) * matrix.get(k, j));
                }
                result.set(i, j, soma);
            }
        }

        return map((v, row, col) -> result.get(row, col));
    }

    public Matrix randomize() {
        return map((v, row, col) -> Math.random());
    }

    public Matrix sigmoid() {
        return map((v, row, col) -> 1 / (1 + Math.exp(-v)));
    }

    public Matrix dsigmoid() {
        return map((v, row, col) -> v * (1 - v));
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