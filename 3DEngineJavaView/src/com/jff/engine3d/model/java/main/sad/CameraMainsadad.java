package com.jff.engine3d.model.java.main.sad;

import com.jff.engine3d.model.Point3D;
import com.jff.engine3d.model.java.main.Matrix;
import com.jff.engine3d.model.java.main.Vector;

public class CameraMainsadad {

    public static void main(String args[]) {
        System.out.println("go");

        Point3D eye = new Point3D(1, 0, 0);
        Point3D direction = new Point3D(0, 0, 0);
        Point3D up = new Point3D(0, 1, 0);

        com.jff.engine3d.model.java.main.Matrix mR = new com.jff.engine3d.model.java.main.Matrix(4);
        com.jff.engine3d.model.java.main.Matrix mT = new com.jff.engine3d.model.java.main.Matrix(4);
        com.jff.engine3d.model.java.main.Matrix m = new com.jff.engine3d.model.java.main.Matrix(4);


        Vector vEye = convertPoint3d(eye);
        Vector vUp = convertPoint3d(up);

        double[] z = {direction.x - eye.x, direction.y - eye.y, direction.z - eye.z, 1};
        Vector vZ = new Vector(z);
        Vector vX = new Vector(crossproduct(vZ.vector, vUp.vector));
        Vector vY = new Vector(crossproduct(vX.vector, vZ.vector));

        vX.normalize();
        vZ.normalize();
        vY.normalize();

        System.out.println(vX);
        System.out.println("vR");
        System.out.println(vZ);
        System.out.println("vDirection");
        System.out.println(vY);
        System.out.println("uR");

        mR.matrix[0][0] = vX.vector[0];
        mR.matrix[0][1] = vX.vector[1];
        mR.matrix[0][2] = vX.vector[2];
        mR.matrix[0][3] = 0;

        mR.matrix[1][0] = vY.vector[0];
        mR.matrix[1][1] = vY.vector[1];
        mR.matrix[1][2] = vY.vector[2];
        mR.matrix[1][3] = 0;

        mR.matrix[2][0] = -vZ.vector[0];
        mR.matrix[2][1] = -vZ.vector[1];
        mR.matrix[2][2] = -vZ.vector[2];
        mR.matrix[2][3] = 0;

        mR.matrix[3][0] = 0;
        mR.matrix[3][1] = 0;
        mR.matrix[3][2] = 0;
        mR.matrix[3][3] = 1;


        mT.matrix[0][0] = 1;
        mT.matrix[0][1] = 0;
        mT.matrix[0][2] = 0;
        mT.matrix[0][3] = -eye.x;

        mT.matrix[1][0] = 0;
        mT.matrix[1][1] = 1;
        mT.matrix[1][2] = 0;
        mT.matrix[1][3] = -eye.y;

        mT.matrix[2][0] = 0;
        mT.matrix[2][1] = 0;
        mT.matrix[2][2] = 1;
        mT.matrix[2][3] = -eye.z;

        mT.matrix[3][0] = 0;
        mT.matrix[3][1] = 0;
        mT.matrix[3][2] = 0;
        mT.matrix[3][3] = 1;

//        mR = mR.transpose();
//        mT = mT.transpose();

        System.out.println(mR);
        System.out.println("mR");
        System.out.println(mT);
        System.out.println("mT");
        System.out.println(m);
        System.out.println("m");
        m = new Matrix(multiply(mR.matrix, mT.matrix));


        Point3D start = new Point3D(0, 0, 0);

        Vector startVector = convertPoint3d(start);
        System.out.println();
        System.out.println(startVector);
//        System.out.println(new Vector(multiply(mT.matrix, startVector.vector)));
//        System.out.println(new Vector(multiply(mR.matrix, startVector.vector)));
//        System.out.println(new Vector(multiply(m.matrix, startVector.vector)));

        System.out.println(new Vector(multiply(startVector.vector, mT.matrix)));
        System.out.println(new Vector(multiply(startVector.vector, mR.matrix)));
        System.out.println(new Vector(multiply(startVector.vector, m.matrix)));
        System.out.println();
        double[] multiply = multiply(startVector.vector, mT.matrix);
        double[] multiply1 = multiply(multiply, mR.matrix);
        System.out.println(new Vector(multiply1));

    }

    public static Vector convertPoint3d(Point3D point3D) {

        Vector vector = new Vector(4);

        vector.vector[0] = point3D.x;
        vector.vector[1] = point3D.y;
        vector.vector[2] = point3D.z;
        vector.vector[3] = 1;

        return vector;
    }

    public static double[] crossproduct(double v1[], double v2[]) {
        double c[] = new double[3];
        double d1, d2, d3;
        int i;
        d1 = (v1[1] * v2[2]) - (v1[1] * v2[2]);
        d2 = (v1[2] * v2[0]) - (v1[0] * v2[2]);
        d3 = (v1[0] * v2[1]) - (v1[1] * v2[0]);
        c[0] = d1;
        c[1] = d2;
        c[2] = d3;
        return (c);
    }


    // return x^T y
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }


    // return C = A * B
    public static double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = A[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += (A[i][k] * B[k][j]);
        return C;
    }

    // matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] A, double[] x) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += (A[i][j] * x[j]);
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static double[] multiply(double[] x, double[][] A) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += (A[i][j] * x[i]);
        return y;
    }
}
