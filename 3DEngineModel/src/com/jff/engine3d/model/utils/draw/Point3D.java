package com.jff.engine3d.model.utils.draw;

public class Point3D {
    public double x;
    public double y;
    public double z;


    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public Point3D move(Point3D offset) {

        double[][] matrix =
                {
                        {1, 0, 0, offset.getX()},
                        {0, 1, 0, offset.getY()},
                        {0, 0, 1, offset.getZ()},
                        {0, 0, 0, 1}
                };

        return multiply(matrix);
    }

    public Point3D scale(Point3D offset, Point3D center) {

        double[][] matrix =
                {
                        {offset.getX(), 0, 0, 0},
                        {0, offset.getY(), 0, 0},
                        {0, 0, offset.getZ(), 0},
                        {0, 0, 0, 1}
                };

        return moveAroundPoint(center, matrix);
    }

    public Point3D rotateX(double angle, Point3D center) {
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

    public Point3D rotateY(double angle, Point3D center) {
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

    public Point3D rotateZ(double angle, Point3D center) {
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

    private Point3D moveAroundPoint(Point3D center,
                                    double[][] matrix) {

        Point3D rotated = move(new Point3D(-center.getX(),
                -center.getY(),
                -center.getZ()));

        rotated = rotated.multiply(matrix);
        rotated = rotated.move(center);
        return rotated;
    }

    private Point3D multiply(double[][] matrix) {

        double[] vector = {this.x, this.y, this.z};
        double[] result = new double[4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return new Point3D(result[0], result[1], result[2]);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float distanceTo(Point3D point3D) {
        float distance;

        double powX = Math.pow(this.x - point3D.x, 2);
        double powY = Math.pow(this.y - point3D.y, 2);
        double powZ = Math.pow(this.z - point3D.z, 2);
        distance = (float) Math.sqrt(powX + powY + powZ);

        return distance;
    }
}
