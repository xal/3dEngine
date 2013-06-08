package com.jff.engine3d.model.scene;

import com.jff.engine3d.model.camera.Camera;
import com.jff.engine3d.model.camera.CameraRotateType;
import com.jff.engine3d.model.camera.ProjectionType;
import com.jff.engine3d.model.entities.*;
import com.jff.engine3d.model.logic.exceptions.CollisionDetectedException;
import com.jff.engine3d.model.logic.interfaces.EngineListener;
import com.jff.engine3d.model.logic.interfaces.IEngineCanvas;
import com.jff.engine3d.model.logic.interfaces.IEngineView;
import com.jff.engine3d.model.primitives.AbstractObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scene implements Serializable {

    private transient IEngineCanvas engineCanvas;
    private transient IEngineView engineView;

    private Camera camera;
    private ViewType viewType;


    private List<SceneObject> objects = new ArrayList<SceneObject>();
    private transient SceneObject currentSelectedObject;
    private transient List<EngineListener> engineListeners = new ArrayList<EngineListener>();


    public Scene() {
        camera = new Camera();

    }

    public void removeObject(SceneObject sceneObject) {
        objects.remove(sceneObject);
        fireSceneChanged();
    }

    public void fireSceneChanged() {

        engineCanvas.clear();

        List<PaintPoint2D> vertices = new ArrayList<PaintPoint2D>();

        List<PaintTriangle> polygons = new ArrayList<PaintTriangle>();

        List<SceneObject> objectsInCamera = camera.chooseObjectsInCamera(objects);

        camera.sortObjectsByDistance(objectsInCamera);

        for (SceneObject object : objectsInCamera) {

            List<Triangle> objectTriangles = object.getTriangles();
            List<Point3D> objectVertices = object.getVertices();

            List<Point3D> objectVerticesInCameraCoordinates = camera.translateToCameraCoordinates(objectVertices);

            camera.sortTrianglesByDistanceFromCamera(objectTriangles, objectVerticesInCameraCoordinates);

            List<Triangle> faceTriangles = objectTriangles;

            List<PaintPoint2D> objectPaintVertices = camera.translateFrom3Dto2D(objectVerticesInCameraCoordinates);


            List<PaintTriangle> objectPaintTriangles = camera.translateTrianglesFrom3Dto2D(faceTriangles, objectPaintVertices);

            vertices.addAll(objectPaintVertices);
            polygons.addAll(objectPaintTriangles);
        }


        engineCanvas.setPaintPolygons(polygons);

        drawAxes();
        engineCanvas.redraw();
        engineView.sceneChanged(this);
        engineCanvas.sceneChanged(this);
    }

    private void drawAxes() {

        int length = 100;

        Point3D centerPoint3d = new Point3D(0, 0, 0);
        Point3D xAxysPoint3d = new Point3D(length, 0, 0);
        Point3D yAxysPoint3d = new Point3D(0, length, 0);
        Point3D zAxysPoint3d = new Point3D(0, 0, length);


        PaintPoint2D centerPoint2D = camera.translateFrom3Dto2D(centerPoint3d);
        PaintPoint2D xPoint2D = camera.translateFrom3Dto2D(xAxysPoint3d);
        PaintPoint2D yPoint2D = camera.translateFrom3Dto2D(yAxysPoint3d);
        PaintPoint2D zPoint2D = camera.translateFrom3Dto2D(zAxysPoint3d);

        engineCanvas.setCenterCoordinates(centerPoint2D);

        engineCanvas.setXAxisCoordinates(xPoint2D);
        engineCanvas.setYAxisCoordinates(yPoint2D);
        engineCanvas.setZAxisCoordinates(zPoint2D);
    }

    public void addObject(SceneObject object) throws CollisionDetectedException {


        AbstractObject geometryObject = object.getGeometryObject();


        SceneObject sceneObject = checkForCollision(object);
        if (sceneObject != null) {


            throw new CollisionDetectedException(object.getGeometryObject(), sceneObject.getGeometryObject());
        } else {
            objects.add(object);
            fireSceneChanged();
        }


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
        this.engineView = oldScene.engineView;

        for (SceneObject object : objects) {
            object.getGeometryObject().fireParametersChanged();
        }


        camera.fireSettingsChanged();


    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;

        fireSceneChanged();
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
                currentSelectedObject.setSelected(true);
            }
        }

        return currentSelectedObject;
    }

    public void setCoordinatesForObject(Point3D point3D, SceneObject object) throws CollisionDetectedException {


        AbstractObject geometryObject = object.getGeometryObject();


        Point3D oldMove = geometryObject.getMovePoint3D();
        object.moveToNewCoordinates(point3D);
        SceneObject sceneObject = checkForCollision(object);
        if (sceneObject != null) {

            geometryObject.move(oldMove);
            throw new CollisionDetectedException(object.getGeometryObject(), sceneObject.getGeometryObject());
        } else {

            fireSceneChanged();
        }


    }

    public void setRotationForObject(RotationCoordinates rotationCoordinates, SceneObject object) {

        object.rotate(rotationCoordinates);
        fireSceneChanged();
    }

    public void setScaleForObject(float scale, SceneObject object) throws CollisionDetectedException {

        AbstractObject geometryObject = object.getGeometryObject();

        float oldScale = geometryObject.getScale();
        object.scale(scale);
        SceneObject sceneObject = checkForCollision(object);
        if (sceneObject != null) {

            geometryObject.setScale(oldScale);
            throw new CollisionDetectedException(object.getGeometryObject(), sceneObject.getGeometryObject());
        } else {

            fireSceneChanged();
        }
    }

    private SceneObject checkForCollision(SceneObject object) {
        SceneObject objectWithCollision = null;

        Point3D myObjectCenter = object.getCenter();
        double myObjectBorderRadius = object.getGeometryObject().getBorderSphereRadius();

        for (SceneObject sceneObject : objects) {
            if (sceneObject != object) {
                Point3D currentObjectCenter = sceneObject.getCenter();
                double currentObjectBorderRadius = sceneObject.getGeometryObject().getBorderSphereRadius();


                float distance = myObjectCenter.distanceTo(currentObjectCenter);

                if (distance < myObjectBorderRadius + currentObjectBorderRadius) {
                    objectWithCollision = sceneObject;
                    break;
                }
            }
        }


        return objectWithCollision;
    }

    public void setProjectionType(ProjectionType projectionType) {
        camera.setProjectionType(projectionType);
        fireSceneChanged();
    }


    public void initializeView(IEngineCanvas canvas, IEngineView view) {
        this.engineCanvas = canvas;
        this.engineView = view;
    }

    public void setCameraFocalLength(int focalLength) {
        camera.setCameraFocalLength(focalLength);
        fireSceneChanged();
    }

    public Camera getCamera() {
        return camera;
    }

    public void changeCameraBounds(int width, int height) {
        camera.changeCameraBounds(width, height);
        fireSceneChanged();
    }

    public void setCurrentSelectedObject(SceneObject currentSelectedObject) {
        this.currentSelectedObject.setSelected(false);
        this.currentSelectedObject = currentSelectedObject;
        this.currentSelectedObject.setSelected(true);
        fireSceneChanged();
    }
}
