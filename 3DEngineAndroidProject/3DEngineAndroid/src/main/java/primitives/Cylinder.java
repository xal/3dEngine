package primitives;

import model.Point3D;
import model.TriangulateUtils;

public class Cylinder extends AbstractObject {


    protected int upperRadius;
    protected int lowerRadius;
    protected int height;

    public Cylinder(Point3D point3D, int upperRadius, int lowerRadius, int height) {
        super(point3D);
        this.upperRadius = upperRadius;
        this.lowerRadius = lowerRadius;
        this.height = height;

        fireParametersChanged();
    }

    public int getUpperRadius() {
        return upperRadius;
    }

    public int getLowerRadius() {
        return lowerRadius;
    }

    public int getHeight() {
        return height;
    }

    @Override
    protected void computeVertices() {
        vertexes = TriangulateUtils.createVertices(this);

    }

    @Override
    protected void computeTriangles() {
        triangles = TriangulateUtils.triangulate(this, vertexes);
    }


}
