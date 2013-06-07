package primitives;

import model.Point3D;
import model.TriangulateUtils;

public class Box extends AbstractObject {

    private final int width;
    private final int length;
    private final int height;


    public Box(Point3D center, int width, int length, int height) {
        super(center);

        this.width = width;
        this.length = length;
        this.height = height;

        fireParametersChanged();

    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
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
