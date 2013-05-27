package com.jff.engine3d.model;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private Camera camera;

    private List<SceneObject> objects;

    public void removeObject(SceneObject sceneObject) {
        objects.remove(sceneObject);
    }

    public boolean addObject(SceneObject sceneObject) {
        return objects.add(sceneObject);
    }

    private List<EngineListener> engineListeners = new ArrayList<EngineListener>();


    public boolean addEngineListener(EngineListener engineListener) {
        return engineListeners.add(engineListener);
    }

    public boolean removeEngineListener(EngineListener o) {
        return engineListeners.remove(o);
    }
}
