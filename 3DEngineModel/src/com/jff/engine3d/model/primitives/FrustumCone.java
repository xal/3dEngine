package com.jff.engine3d.model.primitives;


import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.math.TriangulateUtils;

public class FrustumCone extends Cylinder {


    public FrustumCone(Point3D coordinates, int upperBaseRadius, int lowerBaseRadius, int cilinderHeight) {
        super(coordinates, upperBaseRadius, lowerBaseRadius, cilinderHeight);
    }

    public FrustumCone() {
        super();
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
