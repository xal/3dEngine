package com.jff.engine3d.model;

import com.jff.engine3d.model.primitives.AbstractObject;

public class PerspectiveCameraProjection extends AbstractCameraProjection {


    public PerspectiveCameraProjection(CameraSettings cameraSettings) {
        super(ProjectionType.PERSPECTIVE, cameraSettings);
    }

    @Override
    public Point3D convertToCameraCoordinateSystem(Point3D point3D) {
        Point3D converted = super.convertToCameraCoordinateSystem(point3D);


        double focalLength = cameraSettings.getFocalLength();
        double ratio = focalLength / converted.z;

        double x = converted.x * ratio;
        double y = converted.y * ratio;
        double z = converted.z;

        converted.x = x;
        converted.y = y;
        converted.z = z;

        return converted;
    }

    @Override
    public float distanceFromCameraToPoint(Point3D point3D) {
        float distance;

        Point3D cameraCoordinates = new Point3D(0, 0, 0);

        distance = cameraCoordinates.distanceTo(point3D);

        return distance;
    }

    @Override
    public boolean isObjectInCamera(SceneObject object) {

        Point3D center = object.getCenter();

        Point3D inCamera = convertToCameraCoordinateSystem(center);

        AbstractObject geometryObject = object.getGeometryObject();

        double borderSphereRadius = geometryObject.getBorderSphereRadius();
        int focalLength = cameraSettings.getFocalLength();
        double readDistance = inCamera.z - borderSphereRadius;
        boolean inCameraView = readDistance >= focalLength;

        if (inCameraView == true) {

            double ratio = focalLength / (inCamera.z + borderSphereRadius);

            int width = cameraSettings.getCameraWidth();
            int height = cameraSettings.getCameraHeight();

            double realWidth = width / ratio;
            double realHeight = height / ratio;

            if (inCamera.x + borderSphereRadius > realWidth / 2) {
                inCameraView = false;
            }
            if (inCamera.y + borderSphereRadius > realHeight / 2) {
                inCameraView = false;
            }
        }

        return inCameraView;
    }


}
