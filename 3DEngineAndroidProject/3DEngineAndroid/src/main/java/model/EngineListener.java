package model;

import java.util.List;

public interface EngineListener {

    public void onSelectedObjectsChanged(List<SceneObject> objects);

    public void onCurrentSelectedObjectChanged(SceneObject object);
}
