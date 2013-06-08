package com.jff.engine3d.model;

import com.jff.engine3d.model.primitives.AbstractObject;

public class ParallelCameraProjection extends AbstractCameraProjection {
    public ParallelCameraProjection(CameraSettings cameraSettings) {
        super(ProjectionType.PARALLEL, cameraSettings);
    }

    @Override
    public Point3D convertToCameraCoordinateSystem(Point3D point3D) {
        Point3D point3DConverted = super.convertToCameraCoordinateSystem(point3D);


        return point3DConverted;
    }

    @Override
    public float distanceFromCameraToPoint(Point3D point3D) {
        float distance;

//        Point3D fromCoordinates = cameraSettings.getRealFromCoordinates();
        Point3D cameraCoordinates = new Point3D(0, 0, 0);
        distance = (float) Math.abs(cameraCoordinates.z - point3D.z);

        return distance;
    }

    @Override
    public boolean isObjectInCamera(SceneObject object) {
        Point3D center = object.getCenter();

        Point3D inCamera = convertToCameraCoordinateSystem(center);

        AbstractObject geometryObject = object.getGeometryObject();


        double borderSphereRadius = geometryObject.getBorderSphereRadius();
        boolean inCameraView = inCamera.z - borderSphereRadius >= 0;

        if (inCameraView == true) {

            int width = cameraSettings.getCameraWidth();
            int height = cameraSettings.getCameraHeight();
            if (center.x + borderSphereRadius > width / 2) {
                inCameraView = false;
            }
            if (center.y + borderSphereRadius > height / 2) {
                inCameraView = false;
            }
        }
        return inCameraView;

    }

}
