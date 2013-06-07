package com.jff.engine3d.model.primitives;


import com.jff.engine3d.model.Point3D;
import com.jff.engine3d.model.TriangulateUtils;

public class HalfFrustCone extends Cylinder {


    public HalfFrustCone(Point3D coordinates, int upperBaseRadius, int lowerBaseRadius, int cilinderHeight) {
        super(coordinates, upperBaseRadius, lowerBaseRadius, cilinderHeight);
    }

    @Override
    protected void computeVertices() {
        vertexes = TriangulateUtils.createVertices(this);
    }

    @Override
    protected void computeTriangles() {
        triangles = TriangulateUtils.triangulate(this, vertexes);
    }
}
