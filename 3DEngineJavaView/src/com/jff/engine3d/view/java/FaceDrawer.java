package com.jff.engine3d.view.java;


import com.jff.engine3d.view.utils.draw.*;

public class FaceDrawer implements IFaceDrawer {

    private static int COUNT = 0;

    private Color color;
    private Path path;
    private Paint paint;

    public FaceDrawer() {

        this.color = (COUNT % 2 != 0) ? Color.BLACK : Color.BLUE;
        this.path = new Path();
        this.paint = new Paint();

        paint.setColor(color);
        paint.setStyle(Paint.PaintStyle.FILL);

        COUNT++;
    }

    public void setPath(Point3D[] t, int[] face) {

        path = new Path();
        path.moveTo((float) t[face[0]].x, (float) t[face[0]].y);
        path.lineTo((float) t[face[1]].x, (float) t[face[1]].y);
        path.lineTo((float) t[face[2]].x, (float) t[face[2]].y);
        path.close();
    }

    public Path getPath() {
        return path;
    }

    @Override
    public void setPath(Path path) {
        this.path = path;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
