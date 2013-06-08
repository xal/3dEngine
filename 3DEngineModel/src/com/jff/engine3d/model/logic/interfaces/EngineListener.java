package com.jff.engine3d.model.logic.interfaces;

import com.jff.engine3d.model.scene.SceneObject;

import java.util.List;

public interface EngineListener {

    public void onSelectedObjectsChanged(List<SceneObject> objects);

    public void onCurrentSelectedObjectChanged(SceneObject object);
}
