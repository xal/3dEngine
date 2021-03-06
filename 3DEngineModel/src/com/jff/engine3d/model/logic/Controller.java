package com.jff.engine3d.model.logic;

import com.jff.engine3d.model.camera.CameraRotateType;
import com.jff.engine3d.model.camera.ProjectionType;
import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.entities.RotationCoordinates;
import com.jff.engine3d.model.logic.exceptions.CollisionDetectedException;
import com.jff.engine3d.model.logic.interfaces.EngineListener;
import com.jff.engine3d.model.logic.interfaces.IEngineCanvas;
import com.jff.engine3d.model.logic.interfaces.IEngineView;
import com.jff.engine3d.model.object.params.AbstractObjectParams;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.scene.Scene;
import com.jff.engine3d.model.scene.SceneObject;
import com.jff.engine3d.model.scene.ViewType;
import com.jff.engine3d.model.storage.StorageHelper;

import java.util.List;

public class Controller {

    private final StorageHelper storageHelper;
    private Scene scene;

    public boolean addEngineListener(EngineListener engineListener) {
        return scene.addEngineListener(engineListener);
    }

    public boolean removeEngineListener(EngineListener o) {
        return scene.removeEngineListener(o);
    }

    public Controller(Scene scene) {

        this.scene = scene;
        this.storageHelper = new StorageHelper();
    }

    public void createObject(Point3D point3D, AbstractObjectParams objectParams)
            throws CollisionDetectedException {

        // AbstractObject object = new Box(point3D, 100, 100, 100);
        // AbstractObject object = new Cylinder(new Point3D(100, 100, 100), 30,
        // 60, 40);
        // SceneObject sceneObject = new SceneObject(object);
        // scene.addObject(sceneObject);
        //
        // AbstractObject object1 = new Cylinder(new Point3D(200, 100, 100), 30,
        // 30, 40);
        // SceneObject sceneObject1 = new SceneObject(object1);
        // scene.addObject(sceneObject1);
        //
        try {

            AbstractObject object = objectParams.createObject(point3D);
            SceneObject sceneObject = new SceneObject(object);
            scene.addObject(sceneObject);
            scene.setCurrentSelectedObject(sceneObject);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }

        // AbstractObject object2 = new Tor(point3D, 100, 20);
        // SceneObject sceneObject2 = new SceneObject(object2);
        // scene.addObject(sceneObject2);

    }

    public void createObject(Point3D point3D, SceneObject sceneObject)
            throws CollisionDetectedException {

        scene.addObject(sceneObject);
        scene.setCurrentSelectedObject(sceneObject);

    }

    public void deleteObject(SceneObject object) {

        scene.removeObject(object);

    }

    public void setViewType(ViewType viewType) {
        scene.setViewType(viewType);
    }

    public void startPanorama() {

    }

    public void setProjectionType(ProjectionType projectionType) {
        scene.setProjectionType(projectionType);
    }

    public void setCameraFromCoordinates(Point3D point3D) {
        scene.setCameraFromCoordinates(point3D);
    }

    public void setCameraToCoordinates(Point3D point3D) {
        scene.setCameraToCoordinates(point3D);
    }

    public void setCameraRotation(CameraRotateType rotateType,
                                  RotationCoordinates rotationCoordinates) {
        scene.setCameraRotation(rotateType, rotationCoordinates);
    }

    public List<SceneObject> getSelectedObjects() {
        List<SceneObject> selectedObjects = scene.getSelectedObjects();
        return selectedObjects;
    }

    public SceneObject getCurrentSelectedObject() {
        SceneObject currentSelectedObject = scene.getCurrentSelectedObject();
        return currentSelectedObject;
    }

    public void setMoveCoordinatesForObject(Point3D point3D, SceneObject object)
            throws CollisionDetectedException {
        scene.setCoordinatesForObject(point3D, object);
    }

    public void setRotationForObject(RotationCoordinates rotationCoordinates,
                                     SceneObject object) {
        scene.setRotationForObject(rotationCoordinates, object);
    }

    public void setScaleForObject(float scale, SceneObject object)
            throws CollisionDetectedException {
        scene.setScaleForObject(scale, object);
    }

    public void loadScene(String pathToFile) {

        try {
            Scene newScene = storageHelper.loadScene(pathToFile);
            Scene oldScene = scene;

            newScene.init(oldScene);
            oldScene.clean();

            scene = newScene;

            scene.fireSceneChanged();
            ;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error on load");
        }
    }

    public void saveScene(String pathToFile) {
        try {
            storageHelper.saveScene(scene, pathToFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error on save");
        }
    }

    public void init(IEngineCanvas canvas, IEngineView view) {
        scene.initializeView(canvas, view);
    }

    public void setCameraFocalLength(int focalLength) {
        scene.setCameraFocalLength(focalLength);

    }

    public void changeCameraBounds(int width, int height) {
        scene.changeCameraBounds(width, height);
    }

    public void setCurrentSelectedObject(SceneObject sceneObject) {
        scene.setCurrentSelectedObject(sceneObject);
    }

    public List<SceneObject> getObjects() {
        return scene.getObjects();
    }

    public void setIsAllSelected(boolean allSelected) {
        scene.setIsAllObjectsSelected(allSelected);
    }

    public boolean isAllSelected() {
        return scene.isAllObjectsSelected();
    }

    public void deleteObjects(List<SceneObject> selectedObjects) {
        scene.deleteObjects(selectedObjects);
    }

    public ViewType getViewType() {
        return scene.getViewType();
    }

    public void setCameraHorizontalLength(int horizontalLength) {
        scene.setCameraHorizontalLength(horizontalLength);
    }

    public Scene getScene() {
        return scene;
    }
}
