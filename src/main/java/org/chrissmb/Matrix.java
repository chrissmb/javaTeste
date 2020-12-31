package org.chrissmb;

import java.text.DecimalFormat;

public class Matrix {

    private double[][] data;

    public Matrix(int row, int col) {
        data = new double[row][col];
    }

    public Matrix(double[][] data) {
        this.data = data;
    }

    public double get(int row, int col) {
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

    public void setData(double[][] data) {
        this.data = data;
    }

    public double[][] getData() {
        return data;
    }

    public static Matrix fromArray(double[] array) {
        Matrix m = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; i++) {
            m.set(i, 0, array[i]);
        }
        return m;
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

    public Matrix add(Matrix matrix) {
        if (getColLength() != matrix.getColLength() || getRowLength() != matrix.getRowLength()) {
            throw new RuntimeException("Matrizes com largura ou tamanho diferente conforme regra de soma de matriz.");
        }
        return map((v, row, col) -> v + matrix.get(row, col));
    }

    public Matrix add(double d) {
        return map((v, row, col) -> v + d);
    }

    public Matrix subtract(Matrix matrix) {
        if (getColLength() != matrix.getColLength() || getRowLength() != matrix.getRowLength()) {
            throw new RuntimeException("Matrizes com largura ou tamanho diferente conforme regra de subtração de matriz.");
        }
        return map((v, row, col) -> v - matrix.get(row, col));
    }

    public static Matrix transpose(Matrix matrix) {
        Matrix result = new Matrix(matrix.getColLength(), matrix.getRowLength());
        return result.map((v, row, col) -> v + matrix.get(col, row));
    }

    public Matrix multiply(Matrix matrix) {
        if (getColLength() != matrix.getRowLength()) {
            throw new RuntimeException("Matrizes com largura ou tamanho diferente conforme regra de multiplicação de matriz.");
        }

        Matrix result = new Matrix(getRowLength(), matrix.getColLength());

        for (int i = 0; i < result.getRowLength(); i++) {
            for (int j = 0; j < result.getColLength(); j++) {
                double soma = 0;
                for (int k = 0; k < getColLength(); k++) {
                    soma += (get(i, k) * matrix.get(k, j));
                }
                result.set(i, j, soma);
            }
        }

        return result;
    }

    public Matrix multiplyValue(Matrix matrix) {
        return map((v, row, col) -> v * matrix.get(row, col));
    }

    public Matrix multiply(double d) {
        return map((v, row, col) -> v * d);
    }

    public void randomize() {
        Matrix m = map((v, row, col) -> Math.random() * 2 - 1);
        setData(m.getData());
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