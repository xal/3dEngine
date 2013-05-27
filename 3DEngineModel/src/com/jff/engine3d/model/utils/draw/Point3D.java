package com.jff.engine3d.model.utils.draw;

public class Point3D {

    public double x;
    public double y;
    public double z;
    public double h;

    public Point3D() {
        x = y = z = h = 0;
    }

    public Point3D(double x, double y, double z, double h) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.h = h;
    }

    public Point3D move(Coordinates offset) {

        double[][] matrix =
                {
                        {1, 0, 0, offset.getX()},
                        {0, 1, 0, offset.getY()},
                        {0, 0, 1, offset.getZ()},
                        {0, 0, 0, 1}
                };

        return multiply(matrix);
    }

    public Point3D scale(Coordinates offset, Coordinates center) {

        double[][] matrix =
                {
                        {offset.getX(), 0, 0, 0},
                        {0, offset.getY(), 0, 0},
                        {0, 0, offset.getZ(), 0},
                        {0, 0, 0, 1}
                };

        return moveAroundPoint(center, matrix);
    }

    public Point3D rotateX(double angle, Coordinates center) {
        double rad, cos, sin;

        rad = angle * Math.PI / 180;
        cos = Math.cos(rad);
        sin = Math.sin(rad);

        double[][] matrix =
                {
                        {1, 0, 0, 0},
                        {0, cos, sin, 0},
                        {0, -sin, cos, 0},
                        {0, 0, 0, 1}
                };

        return moveAroundPoint(center, matrix);
    }

    public Point3D rotateY(double angle, Coordinates center) {
        double rad, cos, sin;

        rad = angle * Math.PI / 180;
        cos = Math.cos(rad);
        sin = Math.sin(rad);

        double[][] matrix =
                {
                        {cos, 0, -sin, 0},
                        {0, 1, 0, 0},
                        {sin, 0, cos, 0},
                        {0, 0, 0, 1}
                };

        return moveAroundPoint(center, matrix);
    }

    public Point3D rotateZ(double angle, Coordinates center) {
        double rad, cos, sin;

        rad = angle * Math.PI / 180;
        cos = Math.cos(rad);
        sin = Math.sin(rad);

        double[][] matrix =
                {
                        {cos, sin, 0, 0},
                        {-sin, cos, 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1}
                };

        return moveAroundPoint(center, matrix);
    }

    private Point3D moveAroundPoint(Coordinates center,
                                    double[][] matrix) {

        Point3D rotated = move(new Coordinates(-center.getX(),
                -center.getY(),
                -center.getZ()));

        rotated = rotated.multiply(matrix);
        rotated = rotated.move(center);
        return rotated;
    }

    private Point3D multiply(double[][] matrix) {

        double[] vector = {this.x, this.y, this.z, this.h};
        double[] result = new double[4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return new Point3D(result[0], result[1], result[2], result[3]);
    }
}
