package com.jff.engine3d.model.utils.draw;

public abstract class AbstractFaceDrawer {


    private static int COUNT = 0;

    private Color color;
    protected AbstractPath path;
    private Paint paint;

    public AbstractFaceDrawer() {

        this.color = (COUNT % 2 != 0) ? Color.BLACK : Color.BLUE;

        this.paint = new Paint();

        paint.setColor(color);
        paint.setStyle(Paint.PaintStyle.FILL);

        COUNT++;
    }

    public abstract void setPath(Point3D[] t, int[] face);

    public AbstractPath getPath() {
        return path;
    }


    public void setPath(AbstractPath path) {
        this.path = path;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }


}
