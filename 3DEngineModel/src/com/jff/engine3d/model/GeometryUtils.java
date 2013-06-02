package com.jff.engine3d.model;

public class GeometryUtils {

    public static Point3D getTriangleCentroid(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {


        double x1 = firstPoint.getX();
        double y1 = firstPoint.getY();
        double z1 = firstPoint.getZ();
        double x2 = secondPoint.getX();
        double y2 = secondPoint.getY();
        double z2 = secondPoint.getZ();
        double x3 = thirdPoint.getX();
        double y3 = thirdPoint.getY();
        double z3 = thirdPoint.getZ();

        double centroidX = (x1 + x2 + x3) / 3;
        double centroidY = (y1 + y2 + y3) / 3;
        double centroidZ = (z1 + z2 + z3) / 3;

        Point3D centroid = new Point3D(centroidX, centroidY, centroidZ);

        return centroid;
    }

}
