package com.jff.engine3d.model.camera;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.scene.SceneObject;

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
        int focalLength = cameraSettings.getFocalLength();
        double readDistance = inCamera.z - borderSphereRadius;
        boolean inCameraView = readDistance >= focalLength;


        int horizontalLength = cameraSettings.getHorizontalLength();
        inCameraView &= (inCamera.z + borderSphereRadius) <= horizontalLength;

        if (inCameraView == true) {

            int width = cameraSettings.getCameraWidth();
            int height = cameraSettings.getCameraHeight();
            if (inCamera.x + borderSphereRadius > width / 2) {
                inCameraView = false;
            }
            if (inCamera.y + borderSphereRadius > height / 2) {
                inCameraView = false;
            }
        }
        return inCameraView;

    }

}
