package com.jff.engine3d.model.utils.draw;

import com.jff.engine3d.model.primitives.Primitive;

public interface IEngineCanvas {
    void addObject(Primitive object);

    void removeObject(Primitive object);
}
