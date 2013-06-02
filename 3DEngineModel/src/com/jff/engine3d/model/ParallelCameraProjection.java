package com.jff.engine3d.model;

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

        Point3D fromCoordinates = cameraSettings.getRealFromCoordinates();
        distance = (float) Math.abs(fromCoordinates.z - point3D.z);

        return distance;
    }

    @Override
    public boolean isObjectInCamera(SceneObject object) {
        return true;
    }

}
