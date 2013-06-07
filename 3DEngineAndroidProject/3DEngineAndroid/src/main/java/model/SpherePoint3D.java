package model;

public class SpherePoint3D {
    public double r;
    public double theta;
    public double phi;

    public static SpherePoint3D fromCartesian(Point3D point3D) {
        SpherePoint3D spherePoint3D = new SpherePoint3D();

        spherePoint3D.r = Math.sqrt(Math.pow(point3D.x, 2) + Math.pow(point3D.y, 2) + Math.pow(point3D.z, 2));
        if (spherePoint3D.r == 0) {
            spherePoint3D.phi = 0;
            spherePoint3D.theta = 0;
        } else {

            spherePoint3D.phi = Math.acos(point3D.z / spherePoint3D.r);
            spherePoint3D.theta = Math.atan2(point3D.y, point3D.x);
        }

        return spherePoint3D;
    }

    public static Point3D toCartesian(SpherePoint3D spherePoint3D) {
        Point3D point3D = new Point3D();


        point3D.x = spherePoint3D.r * Math.sin(spherePoint3D.theta) * Math.cos(spherePoint3D.phi);
        point3D.y = spherePoint3D.r * Math.sin(spherePoint3D.theta) * Math.sin(spherePoint3D.phi);
        point3D.z = spherePoint3D.r * Math.cos(spherePoint3D.theta);

        return point3D;
    }

    @Override
    public String toString() {
        return "SpherePoint3D{" +
                "r=" + r +
                ", theta=" + theta +
                ", phi=" + phi +
                '}';
    }


}
