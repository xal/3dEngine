package com.jff.engine3d.model;

import com.jff.engine3d.model.utils.draw.Point3D;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scene implements Serializable {

    private IEngineCanvas engineCanvas;

    private Camera camera;
    private ViewType viewType;


    private List<SceneObject> objects = new ArrayList<SceneObject>();
    private SceneObject currentSelectedObject;
    private transient List<EngineListener> engineListeners = new ArrayList<EngineListener>();


    public Scene() {
        camera = new Camera();

    }

    public void removeObject(SceneObject sceneObject) {
        objects.remove(sceneObject);
        fireSceneChanged();
    }

    private void fireSceneChanged() {

        engineCanvas.clear();

        List<PaintPoint2D> vertices = new ArrayList<PaintPoint2D>();
        List<PaintEdge> edges = new ArrayList<PaintEdge>();
        List<PaintTriangle> polygons = new ArrayList<PaintTriangle>();

        List<SceneObject> objectsInCamera = camera.chooseObjectsInCamera(objects);

        camera.sortObjectsByDistance(objectsInCamera);

        for (SceneObject object : objectsInCamera) {

            List<Triangle> objectTriangles = object.getTriangles();
            List<Point3D> objectVertices = object.getVertices();

            List<Point3D> objectVerticesInCameraCoordinates = camera.translateToCameraCoordinates(objectVertices);

            camera.sortTrianglesByDistanceFromCamera(objectTriangles, objectVerticesInCameraCoordinates);

            List<Triangle> faceTriangles = camera.chooseFaceTriangles(objectTriangles, objectVerticesInCameraCoordinates);

            List<PaintPoint2D> objectPaintVertices = camera.translateFrom3Dto2D(objectVerticesInCameraCoordinates);


            List<PaintTriangle> objectPaintTriangles = camera.translateTrianglesFrom3Dto2D(faceTriangles, objectPaintVertices);

            vertices.addAll(objectPaintVertices);
            polygons.addAll(objectPaintTriangles);
        }


        engineCanvas.setPaintVertices(vertices);
        engineCanvas.setPaintEdges(edges);
        engineCanvas.setPaintPolygons(polygons);

        engineCanvas.redraw();
    }

    public boolean addObject(SceneObject sceneObject) {
        boolean add = objects.add(sceneObject);
        fireSceneChanged();
        return add;
    }


    public boolean addEngineListener(EngineListener engineListener) {
        return engineListeners.add(engineListener);
    }

    public boolean removeEngineListener(EngineListener o) {
        return engineListeners.remove(o);
    }

    public void clean() {
        objects.clear();
        engineListeners.clear();
        fireSceneChanged();
    }

    public void init(Scene oldScene) {
        this.engineListeners.addAll(oldScene.engineListeners);
        this.engineCanvas = oldScene.engineCanvas;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;

        fireSceneChanged();
    }

    public void startPanorama() {


    }


    public void setCameraFromCoordinates(Point3D point3D) {
        camera.setFromCoordinates(point3D);
        fireSceneChanged();
    }

    public void setCameraToCoordinates(Point3D point3D) {
        camera.setToCoordinates(point3D);
        fireSceneChanged();
    }

    public void setCameraRotation(CameraRotateType rotateType, RotationCoordinates rotationCoordinates) {
        camera.setRotation(rotateType, rotationCoordinates);
        fireSceneChanged();
    }

    public List<SceneObject> getSelectedObjects() {
        List<SceneObject> selectedObjects = new ArrayList<SceneObject>();
        for (SceneObject object : objects) {
            if (object.isSelected()) {
                selectedObjects.add(object);
            }
        }

        return selectedObjects;

    }

    public SceneObject getCurrentSelectedObject() {
        if (currentSelectedObject == null) {
            List<SceneObject> selectedObjects = getSelectedObjects();
            if (selectedObjects.size() > 0) {
                currentSelectedObject = selectedObjects.get(0);
            }
        }

        return currentSelectedObject;
    }

    public void setCoordinatesForObject(Point3D point3D, SceneObject object) {
        object.moveToNewCoordinates(point3D);
        fireSceneChanged();
    }

    public void setRotationForObject(RotationCoordinates rotationCoordinates, SceneObject object) {
        object.rotate(rotationCoordinates);
        fireSceneChanged();
    }

    public void setScaleForObject(float scale, SceneObject object) {
        object.scale(scale);
        fireSceneChanged();
    }

    public void setProjectionType(ProjectionType projectionType) {
        camera.setProjectionType(projectionType);
        fireSceneChanged();
    }


    public void initializeCanvas(IEngineCanvas canvas) {
        this.engineCanvas = canvas;
    }
}
