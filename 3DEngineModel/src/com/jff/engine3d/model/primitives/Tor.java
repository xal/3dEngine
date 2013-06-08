package com.jff.engine3d.model.primitives;

import com.jff.engine3d.model.Point3D;
import com.jff.engine3d.model.TriangulateUtils;

public class Tor extends AbstractObject {

    private int radiusInner;
    private int radiusOuter;

    public Tor(Point3D point3D, int radiusInner, int radiusOuter) {
        super(point3D);
        this.radiusInner = radiusInner;
        this.radiusOuter = radiusOuter;
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

        borderRadius = radiusInner + radiusOuter;


        return borderRadius;

    }

    @Override
    public String toString() {
        return "Tor{" +
                "radiusInner=" + radiusInner +
                ", radiusOuter=" + radiusOuter +
                "} " + super.toString();
    }
}
