package com.jff.engine3d.model.primitives;

import com.jff.engine3d.model.TriangulateUtils;
import com.jff.engine3d.model.utils.draw.Point3D;

public class Box extends AbstractObject {

    private final int width;
    private final int length;
    private final int height;


    public Box(Point3D center, int width, int length, int height) {
        super(center);

        this.width = width;
        this.length = length;
        this.height = height;

        fireParametersChanged();

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
        vertices = TriangulateUtils.createVertices(this);

    }

    @Override
    protected void computeTriangles() {
        triangles = TriangulateUtils.triangulateBox(this, vertices);
    }
}
