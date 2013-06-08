package com.jff.engine3d.model.java.components;

import com.jff.engine3d.model.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class EngineCanvasSWT extends Canvas implements IEngineCanvas {

    private List<PaintTriangle> polygons = new ArrayList<PaintTriangle>();
    private com.jff.engine3d.model.Color previousColor;
    private PaintPoint2D centerCoordinates = new PaintPoint2D(0, 0);
    private PaintPoint2D xAxisCoordinates = new PaintPoint2D(0, 0);
    private PaintPoint2D yAxisCoordinates = new PaintPoint2D(0, 0);
    private PaintPoint2D zAxisCoordinates = new PaintPoint2D(0, 0);
    private Point3D cameraFrom;
    private Point3D cameraTo;

    public EngineCanvasSWT(Composite parent) {
        super(parent, SWT.NONE);

        onCreate();
    }

    private void onCreate() {

        Display display = Display.getCurrent();
        Color backgroundColor = display.getSystemColor(SWT.COLOR_WHITE);

        setBackground(backgroundColor);


        Listener listener = new Listener() {

            public void handleEvent(Event e) {

                Rectangle rect = EngineCanvasSWT.this.getBounds();

                int width = rect.width;
                int height = rect.height;

                EngineManager engineManager = EngineManager.getInstance();
                Controller controller = engineManager.getController();
                controller.changeCameraBounds(width, height);

            }

        };


        this.addListener(SWT.Resize, listener);


        this.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent paintEvent) {

                GC gc = paintEvent.gc;

                drawAxes(gc);

                drawCameraCoordinates(gc);

                drawPolygons(gc);
            }
        });
    }

    private void drawCameraCoordinates(GC gc) {

        if (cameraFrom != null && cameraTo != null) {

            String fromString = "From ";

            fromString += cameraFrom.x + " ";
            fromString += cameraFrom.y + " ";
            fromString += cameraFrom.z + " ";
            String toString = "TO ";
            toString += cameraTo.x + " ";
            toString += cameraTo.y + " ";
            toString += cameraTo.z + " ";


            gc.drawText(fromString, 5, 5);
            gc.drawText(toString, 5, 25);
        }
    }

    private void drawAxes(GC gc) {

        PaintPoint2D newCenter = convertToCanvasCoordinates(centerCoordinates);

        PaintPoint2D newXAxysCoordinate = convertToCanvasCoordinates(xAxisCoordinates);
        PaintPoint2D newYAxysCoordinate = convertToCanvasCoordinates(yAxisCoordinates);
        PaintPoint2D newZAxysCoordinate = convertToCanvasCoordinates(zAxisCoordinates);


        int centerX = newCenter.x;
        int centerY = newCenter.y;


        gc.drawLine(newCenter.x, newCenter.y, newXAxysCoordinate.x, newXAxysCoordinate.y);
        gc.drawLine(newCenter.x, newCenter.y, newYAxysCoordinate.x, newYAxysCoordinate.y);
        gc.drawLine(newCenter.x, newCenter.y, newZAxysCoordinate.x, newZAxysCoordinate.y);


        gc.drawText("X", newXAxysCoordinate.x, newXAxysCoordinate.y);
        gc.drawText("Y", newYAxysCoordinate.x, newYAxysCoordinate.y);
        gc.drawText("Z", newZAxysCoordinate.x, newZAxysCoordinate.y);

    }


    private void changeForegroundColor(GC gc, com.jff.engine3d.model.Color color) {
        color = com.jff.engine3d.model.Color.BLACK;
//        if (previousColor != null && previousColor.equals(color)) {
//            return;
//        } else {

        this.previousColor = color;

        Device device = Display.getCurrent();
        Color swtColor = new Color(device, color.red, color.green, color.blue);
        gc.setForeground(swtColor);
        gc.setBackground(new Color(device, 100, 100, 100));
//        }
    }


    private void drawPolygons(GC gc) {


        List<PaintTriangle> paintPolygons = convertToCanvasCoordinates(polygons);
        for (PaintTriangle polygon : paintPolygons) {


            com.jff.engine3d.model.Color color = polygon.color;
            changeForegroundColor(gc, color);


            int[] pointArray = new int[6];

            int counter = 0;
            pointArray[counter++] = polygon.firstPoint.x;
            pointArray[counter++] = polygon.firstPoint.y;
            pointArray[counter++] = polygon.secondPoint.x;
            pointArray[counter++] = polygon.secondPoint.y;
            pointArray[counter++] = polygon.thirdPoint.x;
            pointArray[counter++] = polygon.thirdPoint.y;


            Device device = Display.getCurrent();
            gc.setBackground(new Color(device, 200, 100, 50));
            gc.fillOval(polygon.firstPoint.x, polygon.firstPoint.y, 5, 5);
            gc.fillOval(polygon.secondPoint.x, polygon.secondPoint.y, 5, 5);
            gc.fillOval(polygon.thirdPoint.x, polygon.thirdPoint.y, 5, 5);

            gc.setBackground(new Color(device, 100, 100, 100));
//
//
            gc.fillPolygon(pointArray);
            gc.drawPolygon(pointArray);
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

        x += this.getClientArea().width / 2;
        y += this.getClientArea().height / 2;

        PaintPoint2D newPoint = new PaintPoint2D(x, y);

        return newPoint;
    }


    @Override
    public void setPaintPolygons(List<PaintTriangle> polygons) {
        this.polygons = polygons;
    }

    public void redraw() {
        super.redraw();
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
