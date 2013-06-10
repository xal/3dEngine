package com.jff.engine3d.model.entities;

public class Point3D {
    public double x;
    public double y;
    public double z;


    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public Point3D() {


    }

    public Point3D(Point3D point3D) {
        this.x = point3D.x;
        this.y = point3D.y;
        this.z = point3D.z;
    }

//    @Override
//    public String toString() {
//        return "Point3D{" +
//                "x=" + String.format("%.1f",x) +
//                ", y=" + String.format("%.1f",y) +
//                ", z=" + String.format("%.1f",z) +
//                '}';
//    }


    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                "} " + super.toString();
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

    /**
     * [(cos(a),-sin(a),0),
     * (sin(a),cos(a) ,0),
     * (0     ,0      ,1)]
     *
     * @param theta
     * @return
     */
    public Point3D rotateZ(double theta) {

        Point3D point = this;

        theta = Math.toRadians(theta);
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        double x = point.getX() * cos + point.getY() * -sin;
        double y = point.getX() * sin + point.getY() * cos;
        double z = point.getZ();

        point.x = x;
        point.y = y;
        point.z = z;

        return point;
    }

    /**
     * [(cos(a) ,0      ,sin(a)),
     * (0      ,1      ,0),
     * (-sin(a),0      ,cos(a))]
     *
     * @param theta
     * @return
     */
    public Point3D rotateY(double theta) {

        Point3D point = this;

        theta = Math.toRadians(theta);
        double x = point.getX() * Math.cos(theta) + point.getZ() * Math.sin(theta);
        double y = point.getY();
        double z = point.getX() * -Math.sin(theta) + point.getZ() * Math.cos(theta);


        point.x = x;
        point.y = y;
        point.z = z;

        return point;
    }


    /**
     * [(1      ,0      ,0),
     * (0      ,cos(a) ,-sin(a)),
     * (0      ,sin(a) ,cos(a))]
     *
     * @param theta
     * @return
     */
    public Point3D rotateX(double theta) {

        Point3D point = this;
        theta = Math.toRadians(theta);
        double x = point.getX();
        double y = point.getY() * Math.cos(theta) + point.getZ() * -Math.sin(theta);
        double z = point.getY() * Math.sin(theta) + point.getZ() * Math.cos(theta);

        point.x = x;
        point.y = y;
        point.z = z;

        return point;
    }
}
