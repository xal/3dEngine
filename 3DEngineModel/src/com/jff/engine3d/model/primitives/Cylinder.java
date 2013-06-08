package com.jff.engine3d.model.primitives;

import com.jff.engine3d.model.Point3D;
import com.jff.engine3d.model.TriangulateUtils;

public class Cylinder extends AbstractObject {


    protected int upperRadius;
    protected int lowerRadius;
    protected int height;

    public Cylinder(Point3D point3D, int upperRadius, int lowerRadius, int height) {
        super(point3D);
        this.upperRadius = upperRadius;
        this.lowerRadius = lowerRadius;
        this.height = height;

        fireParametersChanged();
    }

    public Cylinder() {
        super();
    }

    public int getUpperRadius() {
        return upperRadius;
    }

    public int getLowerRadius() {
        return lowerRadius;
    }

    public int getHeight() {
        return height;
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

        double maxRadius = Math.max(upperRadius, lowerRadius);
        borderRadius = Math.sqrt(maxRadius * maxRadius / 4 + height * height / 4);


        return borderRadius;

    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "upperRadius=" + upperRadius +
                ", lowerRadius=" + lowerRadius +
                ", height=" + height +
                "} " + super.toString();
    }
}
