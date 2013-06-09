package com.jff.engine3d.model.object.params;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.primitives.AbstractObject;

import java.io.Serializable;

public abstract class AbstractObjectParams implements Serializable {

    public abstract boolean verifyParams();

    public abstract AbstractObject createObject(Point3D center);
}
