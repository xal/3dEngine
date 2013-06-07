package com.jff.engine3d.model;

import com.jff.engine3d.model.object.AbstractObjectParams;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.primitives.FrustumCone;

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


    public void createObject(Point3D point3D, AbstractObjectParams objectParams) {

        //AbstractObject object = new Box(point3D, 100, 100, 100);
//        AbstractObject object = new Cylinder(new Point3D(100, 100, 100),  30, 60, 40);
//        SceneObject sceneObject = new SceneObject(object);
//        scene.addObject(sceneObject);
//
//        AbstractObject object1 = new Cylinder(new Point3D(200, 100, 100), 30, 30, 40);
//        SceneObject sceneObject1 = new SceneObject(object1);
//        scene.addObject(sceneObject1);

        AbstractObject object = new FrustumCone(new Point3D(100, 100, 100), 30, 60, 40);
        SceneObject sceneObject = new SceneObject(object);
        scene.addObject(sceneObject);


//        AbstractObject object2 = new Cylinder(new Point3D(300, 100, 100), 30, 40, 40);
//        SceneObject sceneObject2 = new SceneObject(object2);
//        scene.addObject(sceneObject2);


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

    public void setCoordinatesForObject(Point3D point3D, SceneObject object) {
        scene.setCoordinatesForObject(point3D, object);
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

    public void init(IEngineCanvas canvas, IEngineView view) {
        scene.initializeView(canvas, view);
    }

    public void setCameraFocalLength(int focalLength) {
        scene.setCameraFocalLength(focalLength);

    }
}
