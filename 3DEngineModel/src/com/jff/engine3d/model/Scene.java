package com.jff.engine3d.model;

import com.jff.engine3d.model.utils.draw.Coordinates;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scene implements Serializable {

    private Camera camera;

    private List<SceneObject> objects = new ArrayList<SceneObject>();
    private transient List<EngineListener> engineListeners = new ArrayList<EngineListener>();

    public void removeObject(SceneObject sceneObject) {
        objects.remove(sceneObject);
    }

    public boolean addObject(SceneObject sceneObject) {
        return objects.add(sceneObject);
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
    }

    public void init(Scene oldScene) {
        this.engineListeners.addAll(oldScene.engineListeners);
    }

    public void setViewType(ViewType viewType) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void startPanorama() {


    }


    public void setCameraFromCoordinates(Coordinates coordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setCameraToCoordinates(Coordinates coordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setCameraRotation(CameraRotateType rotateType, RotationCoordinates rotationCoordinates) {
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

    public void setProjectionType(ProjectionType projectionType) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
