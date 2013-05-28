package com.jff.engine3d.model;

import com.jff.engine3d.model.utils.draw.Point3D;

import java.util.List;

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

            case PARALLER:
                projection = new ParallelCameraProjection(cameraSettings);
                break;
            case PERSPECTIVE:
                projection = new PerspectiveCameraProjection(cameraSettings);
                break;
        }

        return projection;
    }

    public Point3D convertToCameraCoordinateSystem(Point3D point3D) {


        return point3D;
    }

    public abstract float distanceFromCameraToPoint(Point3D point3D);

    public abstract boolean isObjectInCamera(SceneObject object);

    public abstract List<Triangle> chooseFaceTriangles(List<Triangle> objectTriangles, List<Point3D> objectVertices);
}
