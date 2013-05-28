package com.jff.engine3d.model;

import com.jff.engine3d.model.utils.draw.Point3D;

import java.util.List;

public class PerspectiveCameraProjection extends AbstractCameraProjection {


    public PerspectiveCameraProjection(CameraSettings cameraSettings) {
        super(ProjectionType.PERSPECTIVE, cameraSettings);
    }

    @Override
    public float distanceFromCameraToPoint(Point3D point3D) {
        float distance;

        distance = cameraSettings.fromCoordinates.distanceTo(point3D);

        return distance;
    }

    @Override
    public boolean isObjectInCamera(SceneObject object) {
        return true;
    }

    @Override
    public List<Triangle> chooseFaceTriangles(List<Triangle> objectTriangles, List<Point3D> objectVertices) {
        return objectTriangles;
    }
}
