package model;

import java.util.Arrays;

public class Vector {
    public double vector[];

    public Vector(int size) {
        vector = new double[size];
    }

    public Vector(double[] vector) {
        this.vector = vector;
    }


    double length() {
        double X = vector[0];
        double Y = vector[1];
        double Z = vector[2];
        return Math.sqrt(X * X + Y * Y + Z * Z);
    }

    // Normalizes the vector
    public void normalize() {

        double length = length();

        if (length != 0) {
            vector[0] /= length;
            vector[1] /= length;
            vector[2] /= length;

        }


    }

    @Override
    public String toString() {
        return "Vector{" +
                Arrays.toString(vector) +
                '}';
    }
}
