package com.jff.engine3d.model.logic.interfaces;

import com.jff.engine3d.model.entities.PaintPoint2D;
import com.jff.engine3d.model.entities.PaintTriangle;

import java.util.List;

public interface IEngineCanvas extends IEngineView {


    public void setPaintPolygons(List<PaintTriangle> polygons);

    public void redraw();

    void clear();

    void setCenterCoordinates(PaintPoint2D centerCoordinates);

    void setXAxisCoordinates(PaintPoint2D xAxisCoordinates);

    void setYAxisCoordinates(PaintPoint2D yAxisCoordinates);

    void setZAxisCoordinates(PaintPoint2D zAxisCoordinates);
}
