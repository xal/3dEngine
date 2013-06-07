package model;

public abstract class AbstractCameraProjection {


    protected ProjectionType type;
    protected CameraSettings cameraSettings;


    public ProjectionType getType() {
        return type;
    }

    protected AbstractCameraProjection(ProjectionType type, CameraSettings cameraSettings) {
        this.type = type;
        this.cameraSettings = cameraSettings;
    }

    public static AbstractCameraProjection createProjection(ProjectionType projectionType, CameraSettings cameraSettings) {

        AbstractCameraProjection projection = null;
        switch (projectionType) {

            case PARALLEL:
                projection = new ParallelCameraProjection(cameraSettings);
                break;
            case PERSPECTIVE:
                projection = new PerspectiveCameraProjection(cameraSettings);
                break;
        }

        return projection;
    }

    public Point3D convertToCameraCoordinateSystem(Point3D point3D) {

        Point3D newPoint3D = new Point3D(point3D.x, point3D.y, point3D.z);


        Point3D to = cameraSettings.getRealToCoordinates();
        Point3D from = cameraSettings.getRealFromCoordinates();
        Point3D up = new Point3D(1, 1, 1);
        newPoint3D = CameraMain.convert2(from, to, up, point3D);
//
//
//        System.out.println("before move = " + newPoint3D);
//        movePoint(newPoint3D);
//        System.out.println("after move = " + newPoint3D);
//
//        newPoint3D = rotatePoint(newPoint3D);
//
//
//        Point3D toCoordinates = cameraSettings.getRealToCoordinates();
//        Point3D fromCoordinates = cameraSettings.getRealFromCoordinates();
//
//
//        double distance = toCoordinates.distanceTo(fromCoordinates);
//
//        newPoint3D.z += distance;
//
//        System.out.println("after all = " + newPoint3D);
//
//        System.out.println("");


        return newPoint3D;
    }

    private Point3D rotatePoint(Point3D newPoint3D) {


        double phi = cameraSettings.getPhi();
        double theta = cameraSettings.getTheta();

        System.out.println("before = " + newPoint3D);

//        double xAngle = phi;
//        double yAngle = theta;
//
//        double x = newPoint3D.x;
//        double y = newPoint3D.y;
//        double z = newPoint3D.z;
//
//        x = Math.cos(yAngle) * x + Math.sin(yAngle) * Math.sin(xAngle) * y
//                - Math.sin(yAngle) * Math.cos(xAngle) * z;
//
//        y = 0 + Math.cos(xAngle) * y + Math.sin(xAngle) * z;
//
//        z = Math.sin(yAngle) * x + Math.cos(yAngle) * -Math.sin(xAngle)
//                * y + Math.cos(yAngle) * Math.cos(xAngle) * z;
//
//        newPoint3D.x = x;
//        newPoint3D.y = y;
//        newPoint3D.z = z;
//
//        rotateY(newPoint3D, theta);
////        rotateY(newPoint3D, theta);
//        rotateZ(newPoint3D, phi);
////
////        SpherePoint3D spherePoint3D = SpherePoint3D.fromCartesian(newPoint3D);
////        System.out.println("before = " + spherePoint3D);
////
////        spherePoint3D.phi += phi;
////        spherePoint3D.theta += theta;
////        System.out.println("after Rotate = " + spherePoint3D);
////
////
////        Point3D point3D = SpherePoint3D.toCartesian(spherePoint3D);
//          Point3D point3D = newPoint3D;
//        System.out.println("after rotate = " + point3D);

        Point3D point3D = newPoint3D;

        double ex = point3D.x;
        double ey = point3D.y;
        double ez = point3D.z;
//
//        double s1=Math.sin(cam_pos.alpha/360);
//        double s2=Math.sin(cam_pos.beta/360);
//        double s3=Math.sin(cam_pos.gamma/360);
//        double c1=Math.cos(cam_pos.alpha/360);
//        double c2=Math.cos(cam_pos.beta/360);
//        double c3=Math.cos(cam_pos.gamma/360);
        double beta = theta;

        double alpha;
        double gamma;


        alpha = 0;
        gamma = phi;


        double s1 = Math.sin(alpha);
        double s2 = Math.sin(beta);
        double s3 = Math.sin(gamma);
        double c1 = Math.cos(alpha);
        double c2 = Math.cos(beta);
        double c3 = Math.cos(gamma);


        point3D.x = (c2 * c3) * ex + ((-s3) * c2) * ey + (s2) * ez;

        point3D.y = (s1 * s2 * c3 + c1 * s3) * ex + ((-s3) * s1 * s2 + c1 * c3) * ey + ((-s1) * c2) * ez;

        point3D.z = ((-c3) * c1 * s2 + s1 * s3) * ex + (s3 * c1 * c2 + c3 * s1) * ey + (c1 * c2) * ez;
        System.out.println("after rotate = " + point3D);


        return point3D;


    }


    private void movePoint(Point3D newPoint3D) {

        Point3D toCoordinates = cameraSettings.getRealToCoordinates();
        Point3D fromCoordinates = cameraSettings.getRealFromCoordinates();

        newPoint3D.x -= toCoordinates.x;
        newPoint3D.y -= toCoordinates.y;
        newPoint3D.z -= toCoordinates.z;


    }

    public abstract float distanceFromCameraToPoint(Point3D point3D);

    public abstract boolean isObjectInCamera(SceneObject object);

    /**
     * [(cos(a),-sin(a),0),
     * (sin(a),cos(a) ,0),
     * (0     ,0      ,1)]
     *
     * @param point
     * @param theta
     * @return
     */
    public Point3D rotateZ(Point3D point, double theta) {


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
     * @param point
     * @param theta
     * @return
     */
    public Point3D rotateY(Point3D point, double theta) {


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
     * @param point
     * @param theta
     * @return
     */
    public Point3D rotateX(Point3D point, double theta) {


        double x = point.getX();
        double y = point.getY() * Math.cos(theta) + point.getZ() * -Math.sin(theta);
        double z = point.getY() * Math.sin(theta) + point.getZ() * Math.cos(theta);

        point.x = x;
        point.y = y;
        point.z = z;

        return point;
    }


}
