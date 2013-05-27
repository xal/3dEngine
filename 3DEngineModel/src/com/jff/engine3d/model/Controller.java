package com.jff.engine3d.model;

import com.jff.engine3d.model.object.AbstractObjectParams;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.primitives.Parallelepiped;
import com.jff.engine3d.model.utils.draw.Coordinates;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

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


    public void createObject(Coordinates coordinates, AbstractObjectParams objectParams) {

        AbstractObject object = new Parallelepiped(coordinates, 100, 100, 100);

        SceneObject sceneObject = new SceneObject(object);

        scene.addObject(sceneObject);

    }

    public void deleteObject(SceneObject object) {

        scene.removeObject(object);

    }

    public void setViewType(ViewType viewType) {
        scene.setViewType(viewType);
    }

    public void startPanorama() {
        scene.startPanorama();
    }

    public void setProjectionType(ProjectionType projectionType) {
        scene.setProjectionType(projectionType);
    }

    public void setCameraFromCoordinates(Coordinates coordinates) {
        scene.setCameraFromCoordinates(coordinates);
    }

    public void setCameraToCoordinates(Coordinates coordinates) {
        scene.setCameraToCoordinates(coordinates);
    }

    public void setCameraRotation(CameraRotateType rotateType, RotationCoordinates rotationCoordinates) {
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

    public void setCoordinatesForObject(Coordinates coordinates, SceneObject object) {
        scene.setCoordinatesForObject(coordinates, object);
    }

    public void setRotationForObject(RotationCoordinates rotationCoordinates, SceneObject object) {
        scene.setRotationForObject(rotationCoordinates, object);
    }

    public void setScaleForObject(float scale, SceneObject object) {
        scene.setScaleForObject(scale, object);
    }

    public void loadScene(String pathToFile) {

        try {
            Scene newScene = storageHelper.loadScene(pathToFile);
            Scene oldScene = scene;

            newScene.init(oldScene);
            oldScene.clean();

            scene = newScene;
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
}
