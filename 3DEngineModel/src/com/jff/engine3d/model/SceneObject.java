package com.jff.engine3d.model;

import com.jff.engine3d.model.primitives.AbstractObject;

import java.io.Serializable;

public class SceneObject implements Serializable {

    private AbstractObject object;

    private boolean selected;

    public SceneObject(AbstractObject object) {
        this.object = object;
    }

    public String getDescription() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SceneObject)) return false;

        SceneObject that = (SceneObject) o;

        if (object != null ? !object.equals(that.object) : that.object != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return object != null ? object.hashCode() : 0;
    }
}
