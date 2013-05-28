package com.jff.engine3d.model.java.components;

import com.jff.engine3d.model.PaintEdge;
import com.jff.engine3d.model.PaintPolygon;
import com.jff.engine3d.model.PaintVertex;
import com.jff.engine3d.model.utils.draw.IEngineCanvas;
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


    private List<PaintVertex> vertexes = new ArrayList<PaintVertex>();
    private List<PaintEdge> edges = new ArrayList<PaintEdge>();
    private List<PaintPolygon> polygons = new ArrayList<PaintPolygon>();
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

        for (PaintVertex vertex : vertexes) {
            com.jff.engine3d.model.utils.draw.Color color = vertex.color;
            changeForegroundColor(gc, color);

            gc.drawPoint(vertex.x, vertex.y);
        }

    }

    private void changeForegroundColor(GC gc, com.jff.engine3d.model.utils.draw.Color color) {
        if (previousColor.equals(color)) {
            return;
        } else {

            this.previousColor = color;

            Device device = Display.getCurrent();
            Color swtColor = new Color(device, color.red, color.green, color.blue);
            gc.setForeground(swtColor);
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
        for (PaintPolygon polygon : polygons) {

            com.jff.engine3d.model.utils.draw.Color color = polygon.color;
            changeForegroundColor(gc, color);

            PaintVertex[] vertexes = polygon.vertexes;

            int[] pointArray = new int[vertexes.length * 2];

            for (int i = 0; i < vertexes.length; i++) {
                PaintVertex vertex = vertexes[i];
                pointArray[i] = vertex.x;
                pointArray[i + 1] = vertex.y;
            }
            gc.drawPolygon(pointArray);
        }

    }


    @Override
    public void setPaintVertexes(List<PaintVertex> vertexes) {
        this.vertexes = vertexes;
    }

    @Override
    public void setPaintEdges(List<PaintEdge> edges) {
        this.edges = edges;
    }

    @Override
    public void setPaintPolygons(List<PaintPolygon> polygons) {
        this.polygons = polygons;
    }

    public void redraw() {
        super.redraw();
    }


}
