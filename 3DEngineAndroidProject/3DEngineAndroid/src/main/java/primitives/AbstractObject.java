package primitives;


import java.io.Serializable;
import java.util.List;

import model.IDUtils;
import model.Point3D;
import model.RotationCoordinates;
import model.Triangle;

public abstract class AbstractObject implements Serializable {


    private static final Point3D DEFAULT_MOVE = new Point3D(0, 0, 0);
    private static final RotationCoordinates DEFAULT_ROTATION = new RotationCoordinates(0, 0, 0);
    private static final float DEFAULT_SCALE = 1;

    private int id;
    private Point3D centerPoint3D;

    private Point3D movePoint3D;
    private RotationCoordinates rotationCoordinates;
    private float scale;

    protected List<Point3D> vertexes;
    protected List<Triangle> triangles;


    public AbstractObject(Point3D point3D) {
        this();
        this.centerPoint3D = point3D;


    }

    private AbstractObject() {
        id = IDUtils.generateID();

        movePoint3D = DEFAULT_MOVE;
        rotationCoordinates = DEFAULT_ROTATION;
        scale = DEFAULT_SCALE;
    }


    public Point3D getCenter() {
        return centerPoint3D;
    }

    public List<Point3D> getVertexes() {
        return vertexes;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    protected void fireParametersChanged() {
        computeVertices();
        computeTriangles();

        applyTransform();
    }

    private void applyTransform() {
        applyScale();
        applyRotate();
        applyMove();
    }

    private void applyScale() {
        if (scale != 1) {

            for (Point3D vertex : vertexes) {

                vertex.x -= centerPoint3D.x;
                vertex.y -= centerPoint3D.y;
                vertex.z -= centerPoint3D.z;

                vertex.x *= scale;
                vertex.y *= scale;
                vertex.z *= scale;

                vertex.x += centerPoint3D.x;
                vertex.y += centerPoint3D.y;
                vertex.z += centerPoint3D.z;

            }
        }
    }

    private void applyRotate() {
        for (Point3D vertex : vertexes) {


            vertex.x -= centerPoint3D.x;
            vertex.y -= centerPoint3D.y;
            vertex.z -= centerPoint3D.z;

            double xAngle = rotationCoordinates.getX();
            double yAngle = rotationCoordinates.getY();
            double zAngle = rotationCoordinates.getZ();

            rotateX(vertex, xAngle);
            rotateY(vertex, yAngle);
            rotateZ(vertex, zAngle);


            vertex.x += centerPoint3D.x;
            vertex.y += centerPoint3D.y;
            vertex.z += centerPoint3D.z;


        }
    }

    private void applyMove() {
        for (Point3D vertex : vertexes) {
            vertex.x += movePoint3D.x;
            vertex.y += movePoint3D.y;
            vertex.z += movePoint3D.z;
        }


    }

    protected abstract void computeVertices();

    protected abstract void computeTriangles();

    public void scale(float scale) {
        this.scale = scale;
        fireParametersChanged();
    }

    public void rotate(RotationCoordinates rotationCoordinates) {
        this.rotationCoordinates = rotationCoordinates;
        fireParametersChanged();
    }

    public void move(Point3D point3D) {
        this.movePoint3D = point3D;
        fireParametersChanged();
    }

    /**
     * [(cos(a),-sin(a),0),
     * (sin(a),cos(a) ,0),
     * (0     ,0      ,1)]
     *
     * @param point
     * @param theta
     * @return
     */
    public Point3D rotateZ(Point3D point, double theta) {

        theta = Math.toRadians(theta);
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        double x = point.getX() * cos + point.getY() * -sin;
        double y = point.getX() * sin + point.getY() * cos;
        double z = point.getZ();

        point.x = x;
        point.y = y;
        point.z = z;

        return point;
    }

    /**
     * [(cos(a) ,0      ,sin(a)),
     * (0      ,1      ,0),
     * (-sin(a),0      ,cos(a))]
     *
     * @param point
     * @param theta
     * @return
     */
    public Point3D rotateY(Point3D point, double theta) {

        theta = Math.toRadians(theta);
        double x = point.getX() * Math.cos(theta) + point.getZ() * Math.sin(theta);
        double y = point.getY();
        double z = point.getX() * -Math.sin(theta) + point.getZ() * Math.cos(theta);


        point.x = x;
        point.y = y;
        point.z = z;

        return point;
    }


    /**
     * [(1      ,0      ,0),
     * (0      ,cos(a) ,-sin(a)),
     * (0      ,sin(a) ,cos(a))]
     *
     * @param point
     * @param theta
     * @return
     */
    public Point3D rotateX(Point3D point, double theta) {

        theta = Math.toRadians(theta);
        double x = point.getX();
        double y = point.getY() * Math.cos(theta) + point.getZ() * -Math.sin(theta);
        double z = point.getY() * Math.sin(theta) + point.getZ() * Math.cos(theta);

        point.x = x;
        point.y = y;
        point.z = z;

        return point;
    }

    public Point3D getMovePoint3D() {
        return movePoint3D;
    }

    public RotationCoordinates getRotationCoordinates() {
        return rotationCoordinates;
    }

    public float getScale() {
        return scale;
    }
}
