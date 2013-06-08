package com.jff.engine3d.model.entities;

public class PaintTriangle extends AbstractPaintObject {
    public final PaintPoint2D firstPoint;
    public final PaintPoint2D secondPoint;
    public final PaintPoint2D thirdPoint;

    public PaintTriangle(PaintPoint2D firstPoint, PaintPoint2D secondPoint, PaintPoint2D thirdPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
    }
}
