package com.jff.engine3d.model.objec.primitives;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.object.params.BicycleParams;
import com.jff.engine3d.model.primitives.AbstractObject;

public class Bicycle extends AbstractObject {
    private BicycleParams params;

    public Bicycle(Point3D center, BicycleParams bicycleParams) {
        super(center);
        this.params = bicycleParams;
    }

    @Override
    protected void computeVertices() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void computeTriangles() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double computeBorderSphereRadius() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
