package com.jff.engine3d.model.primitives;

import com.jff.engine3d.model.utils.draw.Constants;
import com.jff.engine3d.model.utils.draw.Coordinates;
import com.jff.engine3d.model.utils.draw.Point3D;

public class Cone extends AbstractObject {

    private static final int BASE_CENTER_INDEX = 0;
    private static final int VERTEX_INDEX = 1;
    private static final int FIRST_BASE_POINT = 2;

    private double baseRadius;
    private int coneHeight;

    public Cone(Coordinates coordinates, double baseRadius, int coneHeight) {
        super(coordinates);
        this.baseRadius = baseRadius;
        this.coneHeight = coneHeight;

        int maxSize = (int) Math.max((baseRadius * 2), coneHeight) + Constants.BACKGROUND_PADDING;
        this.viewWidth = maxSize;
        this.viewHeight = maxSize;

        initialize();
    }

    public Cone() {
        super();
        this.baseRadius = 1;
        this.coneHeight = 2;
        this.viewWidth = 100;
        this.viewHeight = 200;
    }

    @Override
    public void initializeFaces() {

        faces = new int[Constants.QUALITY * 2][3];

        for (int i = 0; i < Constants.QUALITY * 2; i++) {

            int index = i + 2;

            if (i < Constants.QUALITY) {
                //initialize base
                faces[i][0] = BASE_CENTER_INDEX;
                faces[i][1] = index;
                faces[i][2] = (i != Constants.QUALITY - 1) ? index + 1 : FIRST_BASE_POINT;
            } else {
                //initialize body
                faces[i][0] = VERTEX_INDEX;
                faces[i][1] = index - Constants.QUALITY;
                faces[i][2] = (i != Constants.QUALITY * 2 - 1) ? index - Constants.QUALITY + 1 : FIRST_BASE_POINT;
            }
        }
    }

    @Override
    public void initializeVertices() {

        //base center vertice
        vertices.add(new Point3D(center.getX(), center.getY(), center.getZ() - coneHeight / 2, 1));
        //vertex vertice
        vertices.add(new Point3D(center.getX(), center.getY(), center.getZ() + coneHeight / 2, 1));
        //first base point vertice
        vertices.add(new Point3D(center.getX() + baseRadius, center.getY(), center.getZ() - coneHeight / 2, 1));

        double angle = 0;
        double x;
        double y;
        double z;

        for (int i = 0; i < Constants.QUALITY - 1; i++) {

            angle += 360.0 / Constants.QUALITY;

            x = center.getX() + baseRadius * Math.cos(Math.toRadians(angle));
            y = center.getY() + baseRadius * Math.sin(Math.toRadians(angle));
            z = vertices.get(vertices.size() - 1).z;

            vertices.add(new Point3D(x, y, z, 1));
        }
    }

    @Override
    public void scale(Coordinates offset) {
        super.scale(offset);

        this.baseRadius *= offset.getX();
        this.coneHeight *= offset.getY();
    }

    public double getBaseRadius() {
        return baseRadius;
    }

    public void setBaseRadius(int baseRadius) {
        this.baseRadius = baseRadius;
    }

    public int getConusHeight() {
        return coneHeight;
    }

    public void setConusHeight(int conusHeight) {
        this.coneHeight = conusHeight;
    }
}
