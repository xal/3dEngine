package com.jff.engine3d.model.primitives;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.math.TriangulateUtils;

public class Box extends AbstractObject {

    private int width;
    private int length;
    private int height;


    public Box(Point3D center, int width, int length, int height) {
        super(center);

        this.width = width;
        this.length = length;
        this.height = height;

        fireParametersChanged();

    }

    public Box() {
        super();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
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


        borderRadius = Math.sqrt(width + height + length) / 2;

        return borderRadius;

    }

    @Override
    public String toString() {
        return "Box{" +
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                "} " + super.toString();
    }
}
