package com.jff.engine3d.model.camera;

import com.jff.engine3d.model.entities.*;
import com.jff.engine3d.model.math.GeometryUtils;
import com.jff.engine3d.model.scene.SceneObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Camera implements Serializable {

    private transient Comparator<SceneObject> CENTER_POINT_SCENE_OBJECT_COMPARATOR = new Comparator<SceneObject>() {


        @Override
        public int compare(SceneObject object, SceneObject object2) {

            Point3D firstPoint3D = object.getCenter();
            Point3D secondPoint3D = object.getCenter();

            firstPoint3D = convertToCameraCoordinateSystem(firstPoint3D);
            secondPoint3D = convertToCameraCoordinateSystem(secondPoint3D);

            float firstDistance = distanceFromCameraToPoint(firstPoint3D);
            float secondDistance = distanceFromCameraToPoint(secondPoint3D);

            if (firstDistance < secondDistance) {
                return -1;
            } else if (firstDistance == secondDistance) {
                return 0;
            } else {
                return 1;
            }
        }
    };

    private static final Point3D DEFAULT_FROM_COORDINATES = new Point3D(0, 0, -1000);
    private static final Point3D DEFAULT_TO_COORDINATES = new Point3D(0, 0, 100);
    private static final CameraRotateType DEFAULT_ROTATE_TYPE = CameraRotateType.TO;
    private static final RotationCoordinates DEFAULT_ROTATION_COORDINATES = new RotationCoordinates(0, 0, 0);
    private static final ProjectionType DEFAULT_PROJECTION_TYPE = ProjectionType.PARALLEL;
    private static final int DEFAULT_FOCAL_LENGTH = 50;
    private static final int DEFAULT_HORIZONTAL_LENGTH = 2000;

    private CameraSettings cameraSettings;
    private transient AbstractCameraProjection projection;
    private ProjectionType projectionType;


    public Camera() {

        cameraSettings = new CameraSettings(DEFAULT_TO_COORDINATES, DEFAULT_FROM_COORDINATES,
                DEFAULT_ROTATE_TYPE, DEFAULT_ROTATION_COORDINATES, DEFAULT_FOCAL_LENGTH);
        setProjectionType(DEFAULT_PROJECTION_TYPE);
        cameraSettings.setHorizontalLength(DEFAULT_HORIZONTAL_LENGTH);
    }


    private float distanceFromCameraToPoint(Point3D point3D) {
        float distance;

        distance = projection.distanceFromCameraToPoint(point3D);


        return distance;
    }

    private Point3D convertToCameraCoordinateSystem(Point3D point3D) {

        Point3D cameraCoordinateSystemPoint = projection.convertToCameraCoordinateSystem(point3D);

        return cameraCoordinateSystemPoint;
    }


    public void sortObjectsByDistance(List<SceneObject> objects) {


        Comparator<SceneObject> centerPointComparator = CENTER_POINT_SCENE_OBJECT_COMPARATOR;

        Collections.sort(objects, centerPointComparator);


    }

    public void sortTrianglesByDistanceFromCamera(List<Triangle> objectTriangles, final List<Point3D> objectVerticesInCameraCoordinates) {


        Comparator<Triangle> triangleDistanceFromCameraComparator = new Comparator<Triangle>() {
            @Override
            public int compare(Triangle triangle, Triangle triangle2) {

                Point3D centroid1 = getTriangleCentroid(triangle, objectVerticesInCameraCoordinates);
                Point3D centroid2 = getTriangleCentroid(triangle2, objectVerticesInCameraCoordinates);

                float firstDistance = distanceFromCameraToPoint(centroid1);
                float secondDistance = distanceFromCameraToPoint(centroid2);


                if (firstDistance < secondDistance) {
                    return -1;
                } else if (firstDistance == secondDistance) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };

        Collections.sort(objectTriangles, triangleDistanceFromCameraComparator);

    }

    private Point3D getTriangleCentroid(Triangle triangle, List<Point3D> objectVerticesInCameraCoordinates) {
        Point3D firstPoint = objectVerticesInCameraCoordinates.get(triangle.firstVertexIndex);
        Point3D secondPoint = objectVerticesInCameraCoordinates.get(triangle.secondVertexIndex);
        Point3D thirdPoint = objectVerticesInCameraCoordinates.get(triangle.thirdVertexIndex);

        Point3D centroid = GeometryUtils.getTriangleCentroid(firstPoint, secondPoint, thirdPoint);

        return centroid;
    }


    public List<PaintPoint2D> translateFrom3Dto2D(List<Point3D> objectVertexes) {

        List<PaintPoint2D> paintPoint2Ds = new ArrayList<PaintPoint2D>();


        for (Point3D objectVertex : objectVertexes) {
            PaintPoint2D paintPoint2D = Point3dTo2d(objectVertex);
            paintPoint2Ds.add(paintPoint2D);
        }


        return paintPoint2Ds;

    }

    private PaintPoint2D Point3dTo2d(Point3D objectVertex) {
        return new PaintPoint2D(objectVertex.x, objectVertex.y);
    }

    public List<PaintTriangle> translateTrianglesFrom3Dto2D(List<Triangle> faceTriangles, List<PaintPoint2D> objectPaintVertices) {

        List<PaintTriangle> polygons = new ArrayList<PaintTriangle>();

        for (Triangle triangle : faceTriangles) {

            PaintPoint2D firstPoint = objectPaintVertices.get(triangle.firstVertexIndex);
            PaintPoint2D secondPoint = objectPaintVertices.get(triangle.secondVertexIndex);
            PaintPoint2D thirdPoint = objectPaintVertices.get(triangle.thirdVertexIndex);

            PaintTriangle paintTriangle = new PaintTriangle(firstPoint, secondPoint, thirdPoint);
            polygons.add(paintTriangle);
        }


        return polygons;

    }


    public void setToCoordinates(Point3D toCoordinates) {
        this.cameraSettings.setToCoordinates(toCoordinates);
    }


    public void setFromCoordinates(Point3D fromCoordinates) {
        this.cameraSettings.setFromCoordinates(fromCoordinates);
    }

    public void setRotation(CameraRotateType rotateType, RotationCoordinates rotationCoordinates) {
        this.cameraSettings.setRotateType(rotateType);
        this.cameraSettings.setRotationCoordinates(rotationCoordinates);
    }

    public void setProjectionType(ProjectionType projectionType) {
        this.projection = AbstractCameraProjection.createProjection(projectionType, cameraSettings);

        this.projectionType = projectionType;
    }

    public List<Point3D> translateToCameraCoordinates(List<Point3D> objectVertexes) {


        List<Point3D> pointsInCameraCoordinatesSystem = new ArrayList<Point3D>();

        for (Point3D objectVertex : objectVertexes) {

            Point3D pointInCameraCoordinateSystem = convertToCameraCoordinateSystem(objectVertex);

            pointsInCameraCoordinatesSystem.add(pointInCameraCoordinateSystem);

        }


        return pointsInCameraCoordinatesSystem;

    }

    public List<SceneObject> chooseObjectsInCamera(List<SceneObject> objects) {


        List<SceneObject> objectsInCamera = new ArrayList<SceneObject>();

        for (SceneObject object : objects) {
            if (projection.isObjectInCamera(object)) {
                objectsInCamera.add(object);
            }
        }


        return objectsInCamera;

    }

    public void setCameraFocalLength(int focalLength) {
        cameraSettings.setFocalLength(focalLength);
    }

    public PaintPoint2D translateFrom3Dto2D(Point3D point3d) {
        Point3D newPoint3D = projection.convertToCameraCoordinateSystem(point3d);

        return Point3dTo2d(newPoint3D);
    }

    public CameraSettings getCameraSettings() {
        return cameraSettings;
    }

    public AbstractCameraProjection getProjection() {
        return projection;
    }

    public void changeCameraBounds(int width, int height) {
        cameraSettings.changeCameraBounds(width, height);
    }

    public void fireSettingsChanged() {
        setProjectionType(this.projectionType);
        cameraSettings.fireSettingsChanged();
    }

    public void setCameraHorizontalLength(int horizontalLength) {
        cameraSettings.setHorizontalLength(horizontalLength);
    }
}
