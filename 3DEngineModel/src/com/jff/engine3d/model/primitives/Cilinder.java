package com.jff.engine3d.model.primitives;


import com.jff.engine3d.model.utils.draw.Constants;
import com.jff.engine3d.model.utils.draw.Coordinates;
import com.jff.engine3d.model.utils.draw.Point3D;

public class Cilinder extends AbstractObject {

    protected static final int BASE_LOWER_CENTER_INDEX = 0;
    protected static final int BASE_UPPER_CENTER_INDEX = 1;
    protected static final int FIRST_LOWER_BASE_POINT = 2;
    protected static final int FIRST_UPPER_BASE_POINT = Constants.QUALITY + FIRST_LOWER_BASE_POINT;

    protected double upperBaseRadius;
    protected double lowerBaseRadius;
    protected double cilinderHeight;

    public Cilinder(Coordinates coordinates, double upperBaseRadius, double lowerBaseRadius, double cilinderHeight) {
        super(coordinates);

        this.upperBaseRadius = upperBaseRadius;
        this.lowerBaseRadius = lowerBaseRadius;
        this.cilinderHeight = cilinderHeight;

        int maxSize = (int) Math.max(lowerBaseRadius * 2, cilinderHeight) + Constants.BACKGROUND_PADDING;
        this.viewWidth = maxSize;
        this.viewHeight = maxSize;

        initialize();
    }

    @Override
    public void initializeVertices() {

        //base lower center vertice
        vertices.add(new Point3D(center.getX(), center.getY(), center.getZ() - cilinderHeight / 2, 1));
        //base upper center vertice
        vertices.add(new Point3D(center.getX(), center.getY(), center.getZ() + cilinderHeight / 2, 1));

        double angle = 0;
        double x;
        double y;
        double z;

        //first lower base point vertice
        vertices.add(new Point3D(center.getX() + lowerBaseRadius, center.getY(), center.getZ() - cilinderHeight / 2, 1));

        //add vertices for lower base
        for (int i = 0; i < Constants.QUALITY - 1; i++) {

            angle += 360.0 / Constants.QUALITY;

            x = center.getX() + lowerBaseRadius * Math.cos(Math.toRadians(angle));
            y = center.getY() + lowerBaseRadius * Math.sin(Math.toRadians(angle));
            z = vertices.get(BASE_LOWER_CENTER_INDEX).z;

            vertices.add(new Point3D(x, y, z, 1));
        }

        angle = 0;
        //first upper base point vertice
        vertices.add(new Point3D(center.getX() + upperBaseRadius, center.getY(), center.getZ() + cilinderHeight / 2, 1));

        //add vertices for upper base
        for (int i = 0; i < Constants.QUALITY - 1; i++) {

            angle += 360.0 / Constants.QUALITY;

            x = center.getX() + upperBaseRadius * Math.cos(Math.toRadians(angle));
            y = center.getY() + upperBaseRadius * Math.sin(Math.toRadians(angle));
            z = vertices.get(BASE_UPPER_CENTER_INDEX).z;

            vertices.add(new Point3D(x, y, z, 1));
        }
    }

    @Override
    public void initializeFaces() {

        faces = new int[Constants.QUALITY * 4][3];

        for (int i = 0; i < faces.length; i++) {
            int index = i + 2;
            //faces of lower base
            if (i < Constants.QUALITY) {
                faces[i][0] = BASE_LOWER_CENTER_INDEX;
                faces[i][1] = index;
                faces[i][2] = (i != Constants.QUALITY - 1) ? index + 1 : FIRST_LOWER_BASE_POINT;
            }
            //faces of upper base
            else if (i < Constants.QUALITY * 2) {
                faces[i][0] = BASE_UPPER_CENTER_INDEX;
                faces[i][1] = index;
                faces[i][2] = (i != Constants.QUALITY * 2 - 1) ? index + 1 : FIRST_UPPER_BASE_POINT;
            }
            //faces of body from lower base
            else if (i < Constants.QUALITY * 3) {

                faces[i][0] = index - Constants.QUALITY;
                faces[i][1] = index - Constants.QUALITY * 2;
                faces[i][2] = (i != Constants.QUALITY * 3 - 1) ? index - Constants.QUALITY * 2 + 1 :
                        FIRST_LOWER_BASE_POINT;

            }
            //faces of body from upper base
            else {
                faces[i][0] = index - Constants.QUALITY * 2;
                faces[i][1] = (i != Constants.QUALITY * 4 - 1) ? index - Constants.QUALITY * 2 + 1 :
                        FIRST_UPPER_BASE_POINT;
                faces[i][2] = (i != Constants.QUALITY * 4 - 1) ? index - Constants.QUALITY * 3 + 1 :
                        FIRST_LOWER_BASE_POINT;
            }
        }
    }

    @Override
    public void scale(Coordinates offset) {
        super.scale(offset);

        this.lowerBaseRadius *= offset.getX();
        this.upperBaseRadius *= offset.getX();
        this.cilinderHeight *= offset.getY();
    }
}
