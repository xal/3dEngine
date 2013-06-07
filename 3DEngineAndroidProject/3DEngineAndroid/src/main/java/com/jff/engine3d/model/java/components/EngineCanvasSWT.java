package com.jff.engine3d.model.java.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import model.Camera;
import model.CameraSettings;
import model.IEngineCanvas;
import model.PaintPoint2D;
import model.PaintTriangle;
import model.Point3D;
import model.Scene;

public class EngineCanvasSWT extends View implements IEngineCanvas {

    Paint paint = new Paint();

    private List<PaintTriangle> polygons = new ArrayList<PaintTriangle>();
    private Color previousColor;
    private PaintPoint2D centerCoordinates = new PaintPoint2D(0, 0);
    private PaintPoint2D xAxisCoordinates = new PaintPoint2D(0, 0);
    private PaintPoint2D yAxisCoordinates = new PaintPoint2D(0, 0);
    private PaintPoint2D zAxisCoordinates = new PaintPoint2D(0, 0);
    private Point3D cameraFrom;
    private Point3D cameraTo;

    public EngineCanvasSWT(Context context) {
        super(context);
    }

    public EngineCanvasSWT(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EngineCanvasSWT(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);



        drawAxes(canvas);

        drawCameraCoordinates(canvas);

        drawPolygons(canvas);

    }


    private void drawCameraCoordinates(Canvas gc) {

        if (cameraFrom != null && cameraTo != null) {

            String fromString = "From ";

            fromString += cameraFrom.x + " ";
            fromString += cameraFrom.y + " ";
            fromString += cameraFrom.z + " ";
            String toString = "TO ";
            toString += cameraTo.x + " ";
            toString += cameraTo.y + " ";
            toString += cameraTo.z + " ";


            gc.drawText(fromString, 5, 15,paint);
            gc.drawText(toString, 5, 35,paint);
        }
    }

    private void drawAxes(Canvas gc) {

        PaintPoint2D newCenter = convertToCanvasCoordinates(centerCoordinates);

        PaintPoint2D newXAxysCoordinate = convertToCanvasCoordinates(xAxisCoordinates);
        PaintPoint2D newYAxysCoordinate = convertToCanvasCoordinates(yAxisCoordinates);
        PaintPoint2D newZAxysCoordinate = convertToCanvasCoordinates(zAxisCoordinates);


        int centerX = newCenter.x;
        int centerY = newCenter.y;


        gc.drawLine(newCenter.x, newCenter.y, newXAxysCoordinate.x, newXAxysCoordinate.y, paint);
        gc.drawLine(newCenter.x, newCenter.y, newYAxysCoordinate.x, newYAxysCoordinate.y, paint);
        gc.drawLine(newCenter.x, newCenter.y, newZAxysCoordinate.x, newZAxysCoordinate.y, paint);


        gc.drawText("X", newXAxysCoordinate.x, newXAxysCoordinate.y, paint);
        gc.drawText("Y", newYAxysCoordinate.x, newYAxysCoordinate.y, paint);
        gc.drawText("Z", newZAxysCoordinate.x, newZAxysCoordinate.y, paint);

    }


    private void changeForegroundColor(Canvas gc, Color color) {

    }


    private void drawPolygons(Canvas gc) {


        List<PaintTriangle> paintPolygons = convertToCanvasCoordinates(polygons);
        for (PaintTriangle polygon : paintPolygons) {




            int[] pointArray = new int[6];

            int counter = 0;
            pointArray[counter++] = polygon.firstPoint.x;
            pointArray[counter++] = polygon.firstPoint.y;
            pointArray[counter++] = polygon.secondPoint.x;
            pointArray[counter++] = polygon.secondPoint.y;
            pointArray[counter++] = polygon.thirdPoint.x;
            pointArray[counter++] = polygon.thirdPoint.y;
//
//            RectF oval;
//            oval = new RectF(polygon.firstPoint.x, polygon.firstPoint.y, 5, 5);
//            gc.drawOval(oval, paint);
//            oval = new RectF(polygon.secondPoint.x, polygon.secondPoint.y, 5, 5);
//            gc.drawOval(oval, paint);
//            oval = new RectF(polygon.thirdPoint.x, polygon.thirdPoint.y, 5, 5);
//            gc.drawOval(oval, paint);


            Path path =new Path();
            path.moveTo(polygon.firstPoint.x, polygon.firstPoint.y);
            path.lineTo(polygon.secondPoint.x, polygon.secondPoint.y);
            path.lineTo(polygon.thirdPoint.x, polygon.thirdPoint.y);
            path.lineTo(polygon.firstPoint.x, polygon.firstPoint.y);

            gc.drawPath(path, paint);

        }

    }

    private List<PaintTriangle> convertToCanvasCoordinates(List<PaintTriangle> polygons) {


        List<PaintTriangle> paintPolygons = new ArrayList<PaintTriangle>();

        for (PaintTriangle polygon : polygons) {
            PaintPoint2D firstPoint = convertToCanvasCoordinates(polygon.firstPoint);
            PaintPoint2D secondPoint = convertToCanvasCoordinates(polygon.secondPoint);
            PaintPoint2D thirdPoint = convertToCanvasCoordinates(polygon.thirdPoint);
            PaintTriangle paintTriangle = new PaintTriangle(firstPoint, secondPoint, thirdPoint);

            paintPolygons.add(paintTriangle);
        }

        return paintPolygons;
    }

    private PaintPoint2D convertToCanvasCoordinates(PaintPoint2D oldPoint) {
        double x = oldPoint.x;
        double y = oldPoint.y;

        x += this.getWidth()/ 2;
        y += this.getHeight()/ 2;

        PaintPoint2D newPoint = new PaintPoint2D(x, y);

        return newPoint;
    }


    @Override
    public void setPaintPolygons(List<PaintTriangle> polygons) {
        this.polygons = polygons;
    }

    public void redraw() {
        this.invalidate();
    }

    @Override
    public void clear() {


        polygons.clear();
    }

    @Override
    public void setCenterCoordinates(PaintPoint2D paintPoint2D) {
        this.centerCoordinates = paintPoint2D;

    }

    @Override
    public void setXAxisCoordinates(PaintPoint2D xAxisCoordinates) {
        this.xAxisCoordinates = xAxisCoordinates;
    }

    @Override
    public void setYAxisCoordinates(PaintPoint2D yAxisCoordinates) {
        this.yAxisCoordinates = yAxisCoordinates;
    }

    @Override
    public void setZAxisCoordinates(PaintPoint2D zAxisCoordinates) {
        this.zAxisCoordinates = zAxisCoordinates;
    }


    @Override
    public void sceneChanged(Scene scene) {
        Camera camera = scene.getCamera();
        CameraSettings settings = camera.getCameraSettings();

        this.cameraFrom = settings.getRealFromCoordinates();
        this.cameraTo = settings.getRealToCoordinates();
    }
}
