package com.jff.engine3d.model.primitives;


import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.entities.RotationCoordinates;
import com.jff.engine3d.model.entities.Triangle;
import com.jff.engine3d.model.utils.IDUtils;

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

    protected transient List<Point3D> vertexes;
    protected transient List<Triangle> triangles;

    protected transient double borderSphereRadius;


    public AbstractObject(Point3D centerPoint3D) {
        this();
        this.centerPoint3D = centerPoint3D;


    }

    public double getBorderSphereRadius() {
        return borderSphereRadius;
    }

    public AbstractObject() {
        id = IDUtils.generateID();

        movePoint3D = DEFAULT_MOVE;
        rotationCoordinates = DEFAULT_ROTATION;
        scale = DEFAULT_SCALE;
    }


    public Point3D getCenter() {
        Point3D point3D = new Point3D(centerPoint3D);
        point3D.x += movePoint3D.x;
        point3D.y += movePoint3D.y;
        point3D.z += movePoint3D.z;
        return point3D;
    }

    public List<Point3D> getVertexes() {
        return vertexes;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    public void fireParametersChanged() {


        computeVertices();
        computeTriangles();

        borderSphereRadius = computeBorderSphereRadius();

        borderSphereRadius *= scale;

        applyTransform();
    }

    private void applyTransform() {
        applyScale();
        applyRotate();
        applyMove();
    }

    private void applyScale() {
        if (scale != 1) {

            for (Point3D vertex : vertexes) {

                vertex.x -= centerPoint3D.x;
                vertex.y -= centerPoint3D.y;
                vertex.z -= centerPoint3D.z;

                vertex.x *= scale;
                vertex.y *= scale;
                vertex.z *= scale;

                vertex.x += centerPoint3D.x;
                vertex.y += centerPoint3D.y;
                vertex.z += centerPoint3D.z;

            }
        }
    }

    private void applyRotate() {
        for (Point3D vertex : vertexes) {


            vertex.x -= centerPoint3D.x;
            vertex.y -= centerPoint3D.y;
            vertex.z -= centerPoint3D.z;

            double xAngle = rotationCoordinates.getX();
            double yAngle = rotationCoordinates.getY();
            double zAngle = rotationCoordinates.getZ();

            vertex.rotateX(xAngle);
            vertex.rotateY(yAngle);
            vertex.rotateZ(zAngle);


            vertex.x += centerPoint3D.x;
            vertex.y += centerPoint3D.y;
            vertex.z += centerPoint3D.z;


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

    protected abstract double computeBorderSphereRadius();

    public void setScale(float scale) {
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


    public Point3D getMovePoint3D() {
        return movePoint3D;
    }

    public RotationCoordinates getRotationCoordinates() {
        return rotationCoordinates;
    }

    public float getScale() {
        return scale;
    }

    @Override
    public String toString() {
        return "AbstractObject{" +
                "scale=" + scale +
                ", centerPoint3D=" + centerPoint3D +
                ", movePoint3D=" + movePoint3D +
                ", rotationCoordinates=" + rotationCoordinates +
                ", borderSphereRadius=" + borderSphereRadius +
                "} " + super.toString();
    }
}
