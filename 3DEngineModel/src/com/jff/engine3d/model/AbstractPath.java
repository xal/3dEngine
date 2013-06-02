package com.jff.engine3d.model;

public abstract class AbstractPath {

    protected AbstractPath() {

    }

    public abstract void moveTo(float x, float y);

    public abstract void lineTo(float x, float y);

    public abstract void close();
}
