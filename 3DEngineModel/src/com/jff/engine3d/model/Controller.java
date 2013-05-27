package com.jff.engine3d.model;

import com.jff.engine3d.model.object.AbstractObjectParams;
import com.jff.engine3d.model.utils.draw.Coordinates;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

import java.util.Collections;
import java.util.List;

public class Controller {


    private Scene scene;


    public boolean addEngineListener(EngineListener engineListener) {
        return scene.addEngineListener(engineListener);
    }

    public boolean removeEngineListener(EngineListener o) {
        return scene.removeEngineListener(o);
    }

    public Controller(Scene scene) {

        this.scene = scene;
    }


    public void createObject(Coordinates coordinates, AbstractObjectParams objectParams) {

    }

    public void deleteObject(SceneObject object) {

    }

    public void loadScene(String pathToFile) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void saveScene(String pathToFile) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setViewType(ViewType edges) {

    }

    public void startPanorama() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setProjectionType(ProjectionType perspective) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setCameraFromCoordinates(Coordinates coordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setCameraToCoordinates(Coordinates coordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void rotateCamera(CameraRotateType rotateType, RotationCoordinates rotationCoordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public List<SceneObject> getSelectedObjects() {
        return Collections.emptyList();  //To change body of created methods use File | Settings | File Templates.
    }

    public SceneObject getCurrentSelectedObject() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setCoordinatesForObject(Coordinates coordinates, SceneObject object) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setRotationForObject(RotationCoordinates rotationCoordinates, SceneObject object) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setScaleForObject(float scale, SceneObject object) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
