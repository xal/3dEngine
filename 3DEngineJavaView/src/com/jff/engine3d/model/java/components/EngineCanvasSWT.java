package com.jff.engine3d.model.java.components;

import com.jff.engine3d.model.IEngineCanvas;
import com.jff.engine3d.model.PaintEdge;
import com.jff.engine3d.model.PaintPoint2D;
import com.jff.engine3d.model.PaintTriangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import java.util.ArrayList;
import java.util.List;

public class EngineCanvasSWT extends Canvas implements IEngineCanvas {


    private List<PaintPoint2D> vertexes = new ArrayList<PaintPoint2D>();
    private List<PaintEdge> edges = new ArrayList<PaintEdge>();
    private List<PaintTriangle> polygons = new ArrayList<PaintTriangle>();
    private com.jff.engine3d.model.utils.draw.Color previousColor;

    public EngineCanvasSWT(Composite parent) {
        super(parent, SWT.NONE);

        onCreate();
    }

    private void onCreate() {

        Display display = Display.getCurrent();
        Color backgroundColor = display.getSystemColor(SWT.COLOR_WHITE);

        setBackground(backgroundColor);

        this.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent paintEvent) {

                GC gc = paintEvent.gc;
                drawVertexes(gc);
                drawEdges(gc);
                drawPolygons(gc);
            }
        });
    }

    private void drawVertexes(GC gc) {

        for (PaintPoint2D vertex : vertexes) {
            com.jff.engine3d.model.utils.draw.Color color = vertex.color;
            changeForegroundColor(gc, color);

            gc.drawPoint(vertex.x, vertex.y);
        }

    }

    private void changeForegroundColor(GC gc, com.jff.engine3d.model.utils.draw.Color color) {
        color = com.jff.engine3d.model.utils.draw.Color.BLACK;
        if (previousColor != null && previousColor.equals(color)) {
            return;
        } else {

            this.previousColor = color;

            Device device = Display.getCurrent();
            Color swtColor = new Color(device, color.red, color.green, color.blue);
            gc.setForeground(swtColor);
            gc.setBackground(new Color(device, 100, 100, 100));
        }
    }

    private void drawEdges(GC gc) {

        for (PaintEdge edge : edges) {

            com.jff.engine3d.model.utils.draw.Color color = edge.color;
            changeForegroundColor(gc, color);

            gc.drawLine(edge.x1, edge.y1, edge.x2, edge.y2);

        }

    }

    private void drawPolygons(GC gc) {
        for (PaintTriangle polygon : polygons) {

            com.jff.engine3d.model.utils.draw.Color color = polygon.color;
            changeForegroundColor(gc, color);


            int[] pointArray = new int[8];

            int counter = 0;
            pointArray[counter++] = polygon.firstPoint.x;
            pointArray[counter++] = polygon.firstPoint.y;
            pointArray[counter++] = polygon.secondPoint.x;
            pointArray[counter++] = polygon.secondPoint.y;
            pointArray[counter++] = polygon.thirdPoint.x;
            pointArray[counter++] = polygon.thirdPoint.y;


            gc.fillPolygon(pointArray);
        }

    }


    @Override
    public void setPaintVertices(List<PaintPoint2D> vertexes) {
        this.vertexes = vertexes;
    }

    @Override
    public void setPaintEdges(List<PaintEdge> edges) {
        this.edges = edges;
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
        vertexes.clear();
        edges.clear();
        polygons.clear();
    }


}
