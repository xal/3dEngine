package com.jff.engine3d.model.primitives;


import com.jff.engine3d.model.IDUtils;
import com.jff.engine3d.model.Triangle;
import com.jff.engine3d.model.utils.draw.Point3D;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractObject implements Serializable {


    private int id;
    private Point3D centerPoint3D;
    private RotationCoordinates rotationCoordinates;
    private float scale;

    protected List<Point3D> vertices;
    protected List<Triangle> triangles;


    public AbstractObject(Point3D point3D) {
        this();
        this.centerPoint3D = point3D;


    }

    private AbstractObject() {
        id = IDUtils.generateID();

    }


    public Point3D getCenter() {
        return centerPoint3D;
    }

    public List<Point3D> getVertices() {
        return vertices;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    protected void fireParametersChanged() {
        computeVertices();
        computeTriangles();
    }

    protected abstract void computeVertices();

    protected abstract void computeTriangles();
}
