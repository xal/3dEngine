package com.jff.engine3d.model;

import java.util.List;

public interface IEngineCanvas {

    public void setPaintVertices(List<PaintPoint2D> vertexes);

    public void setPaintEdges(List<PaintEdge> edges);

    public void setPaintPolygons(List<PaintTriangle> polygons);

    public void redraw();

    void clear();
}
