package model;

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
