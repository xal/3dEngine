package com.jff.engine3d.model.utils.draw;

import com.jff.engine3d.model.PaintEdge;
import com.jff.engine3d.model.PaintPolygon;
import com.jff.engine3d.model.PaintVertex;

import java.util.List;

public interface IEngineCanvas {

    public void setPaintVertexes(List<PaintVertex> vertexes);

    public void setPaintEdges(List<PaintEdge> edges);

    public void setPaintPolygons(List<PaintPolygon> polygons);

    public void redraw();
}
