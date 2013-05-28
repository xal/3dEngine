package com.jff.engine3d.model;

import com.jff.engine3d.model.utils.draw.Point3D;

import java.util.List;

public class ParallelCameraProjection extends AbstractCameraProjection {
    public ParallelCameraProjection(CameraSettings cameraSettings) {
        super(ProjectionType.PARALLER, cameraSettings);
    }

    @Override
    public Point3D convertToCameraCoordinateSystem(Point3D point3D) {
        Point3D point3DConverted = super.convertToCameraCoordinateSystem(point3D);


        return point3DConverted;
    }

    @Override
    public float distanceFromCameraToPoint(Point3D point3D) {
        float distance;

        distance = (float) Math.abs(cameraSettings.fromCoordinates.z - point3D.z);

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
