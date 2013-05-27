package com.jff.engine3d.model.utils.draw;

import com.jff.engine3d.model.primitives.AbstractObject;

public interface IEngineCanvas {
    void addObject(AbstractObject object);

    void removeObject(AbstractObject object);
}
