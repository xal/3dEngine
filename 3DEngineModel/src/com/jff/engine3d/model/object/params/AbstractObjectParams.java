package com.jff.engine3d.model.object.params;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.primitives.AbstractObject;

public abstract class AbstractObjectParams {

    public abstract boolean verifyParams();

    public abstract AbstractObject createObject(Point3D center);
}
