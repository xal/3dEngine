package com.jff.engine3d.model.primitives;


import com.jff.engine3d.model.IDUtils;
import com.jff.engine3d.model.Triangle;
import com.jff.engine3d.model.utils.draw.Point3D;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractObject implements Serializable {


    private static final Point3D DEFAULT_MOVE = new Point3D(0, 0, 0);
    private static final RotationCoordinates DEFAULT_ROTATION = new RotationCoordinates(0, 0, 0);
    private static final float DEFAULT_SCALE = 1;
    private int id;
    private Point3D centerPoint3D;

    private Point3D movePoint3D;
    private RotationCoordinates rotationCoordinates;
    private float scale;

    protected List<Point3D> vertexes;
    protected List<Triangle> triangles;


    public AbstractObject(Point3D point3D) {
        this();
        this.centerPoint3D = point3D;


    }

    private AbstractObject() {
        id = IDUtils.generateID();

        movePoint3D = DEFAULT_MOVE;
        rotationCoordinates = DEFAULT_ROTATION;
        scale = DEFAULT_SCALE;
    }


    public Point3D getCenter() {
        return centerPoint3D;
    }

    public List<Point3D> getVertexes() {
        return vertexes;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    protected void fireParametersChanged() {
        computeVertices();
        computeTriangles();

        applyTransform();
    }

    private void applyTransform() {
        applyMove();
        applyRotate();
        applyScale();
    }

    private void applyScale() {
        if (scale != 1) {

        }
    }

    private void applyRotate() {
        for (Point3D vertex : vertexes) {

            double x = vertex.x;
            double y = vertex.y;
            double z = vertex.z;

            x -= centerPoint3D.x;
            y -= centerPoint3D.y;
            z -= centerPoint3D.z;

            double xAngle = rotationCoordinates.getXAngleInRadians();
            double yAngle = rotationCoordinates.getYAngleInRadians();

            x = Math.cos(yAngle) * x + Math.sin(yAngle) * Math.sin(xAngle) * y
                    - Math.sin(yAngle) * Math.cos(xAngle) * z;

            y = 0 + Math.cos(xAngle) * y + Math.sin(xAngle) * z;

            z = Math.sin(yAngle) * x + Math.cos(yAngle) * -Math.sin(xAngle)
                    * y + Math.cos(yAngle) * Math.cos(xAngle) * z;

            x += centerPoint3D.x;
            y += centerPoint3D.y;
            z += centerPoint3D.z;


            vertex.x = x;
            vertex.y = y;
            vertex.z = z;
        }
    }

    private void applyMove() {
        for (Point3D vertex : vertexes) {
            vertex.x += movePoint3D.x;
            vertex.y += movePoint3D.y;
            vertex.z += movePoint3D.z;
        }


    }

    protected abstract void computeVertices();

    protected abstract void computeTriangles();

    public void scale(float scale) {
        this.scale = scale;
        fireParametersChanged();
    }

    public void rotate(RotationCoordinates rotationCoordinates) {
        this.rotationCoordinates = rotationCoordinates;
        fireParametersChanged();
    }

    public void move(Point3D point3D) {
        this.movePoint3D = point3D;
        fireParametersChanged();
    }
}
