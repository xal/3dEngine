package com.jff.engine3d.model;

import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.utils.draw.Point3D;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

import java.io.Serializable;
import java.util.List;

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

    public List<Triangle> getTriangles() {
        return object.getTriangles();
    }

    public List<Point3D> getVertices() {
        return object.getVertices();
    }

    public boolean isSelected() {
        return selected;
    }

    public void moveToNewCoordinates(Point3D point3D) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void rotate(RotationCoordinates rotationCoordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void scale(float scale) {
    }

    public Point3D getCenter() {
        return object.getCenter();
    }
}
