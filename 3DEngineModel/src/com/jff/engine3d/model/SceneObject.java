package com.jff.engine3d.model;

import com.jff.engine3d.model.primitives.Primitive;

public class SceneObject {

    private Primitive primitive;

    private boolean selected;

    public String getDescription() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SceneObject)) return false;

        SceneObject that = (SceneObject) o;

        if (primitive != null ? !primitive.equals(that.primitive) : that.primitive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return primitive != null ? primitive.hashCode() : 0;
    }
}
