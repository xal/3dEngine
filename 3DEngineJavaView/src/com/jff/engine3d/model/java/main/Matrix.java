package com.jff.engine3d.model.java.main;

import java.util.Arrays;

public class Matrix {

    public double[][] matrix;

    public Matrix(int size) {

        matrix = new double[size][size];

    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix() {
        this(4);
    }

    @Override
    public String toString() {
        String result = "Matrix{";

        result += "\n";
        for (double[] doubles : matrix) {
            result += Arrays.toString(doubles);
            result += "\n";
        }


        result += '}';

        return result;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        int size = matrix.length;
        Matrix A = new Matrix(size);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                A.matrix[j][i] = this.matrix[i][j];
        return A;
    }
}
