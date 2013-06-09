package com.jff.engine3d.model.primitives;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.math.TriangulateUtils;

public class Tor extends AbstractObject {

    private int radiusCircle;
    private int radiusThickness;

    public Tor() {
        super();
    }

    public Tor(Point3D point3D, int radiusCircle, int radiusThickness) {
        super(point3D);
        this.radiusCircle = radiusCircle;
        this.radiusThickness = radiusThickness;

        fireParametersChanged();
    }

    @Override
    protected void computeVertices() {
        vertexes = TriangulateUtils.createVertices(this);

    }

    @Override
    protected void computeTriangles() {
        triangles = TriangulateUtils.triangulate(this, vertexes);
    }

    @Override
    protected double computeBorderSphereRadius() {

        double borderRadius;

        borderRadius = radiusCircle + radiusThickness;

        borderRadius /= 2;

        return borderRadius;

    }

    @Override
    public String toString() {
        return "Tor{" +
                "radiusCircle=" + radiusCircle +
                ", radiusThickness=" + radiusThickness +
                "} " + super.toString();
    }

    public int getCircleRadius() {
        return radiusCircle;
    }

    public int getThicknessRadius() {
        return radiusThickness;
    }
}
