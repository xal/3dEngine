package com.jff.engine3d.model;

public abstract class AbstractCameraProjection {


    protected ProjectionType type;
    protected CameraSettings cameraSettings;


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


        newPoint3D = rotatePoint(newPoint3D);
        movePoint(newPoint3D);

        return newPoint3D;
    }

    private Point3D rotatePoint(Point3D newPoint3D) {


        SpherePoint3D spherePoint3D = SpherePoint3D.fromCartesian(newPoint3D);

        System.out.println("before = " + newPoint3D);
        System.out.println("before = " + spherePoint3D);

        double phi = cameraSettings.getPhi();
        double theta = cameraSettings.getTheta();
        spherePoint3D.phi += phi;
        spherePoint3D.theta += theta;


        Point3D point3D = SpherePoint3D.toCartesian(spherePoint3D);

        System.out.println("after = " + spherePoint3D);
        System.out.println("after = " + point3D);
        System.out.println("");

        return point3D;


    }


    private void movePoint(Point3D newPoint3D) {

        Point3D toCoordinates = cameraSettings.getRealToCoordinates();
        newPoint3D.x -= toCoordinates.x;
        newPoint3D.y -= toCoordinates.y;
        newPoint3D.z -= toCoordinates.z;
    }

    public abstract float distanceFromCameraToPoint(Point3D point3D);

    public abstract boolean isObjectInCamera(SceneObject object);


}
