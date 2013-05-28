package com.jff.engine3d.model;

public class PaintPoint2D extends AbstractPaintObject {

    public int x;
    public int y;

    public PaintPoint2D(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }
}
