package com.jff.engine3d.model.java.main;

import com.jff.engine3d.model.Engine;
import com.jff.engine3d.model.java.FaceDrawerSWT;
import com.jff.engine3d.model.utils.draw.AbstractFaceDrawer;

public class EngineSWT extends Engine {
    @Override
    public AbstractFaceDrawer createFaceDrawer() {
        return new FaceDrawerSWT();
    }
}
