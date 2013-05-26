package com.jff.engine3d.view.primitives;


import com.jff.engine3d.view.utils.draw.Constants;
import com.jff.engine3d.view.utils.draw.Coordinates;
import com.jff.engine3d.view.utils.draw.Point3D;

public class HalfFrustCone extends Cilinder {

    protected static final int FIRST_UPPER_BASE_POINT = Constants.QUALITY / 2 + FIRST_LOWER_BASE_POINT + 1;

    public HalfFrustCone(Coordinates coordinates, double upperBaseRadius, double lowerBaseRadius, double cilinderHeight) {
        super(coordinates, upperBaseRadius, lowerBaseRadius, cilinderHeight);
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
        for (int i = 0; i < (Constants.QUALITY) / 2; i++) {

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
        for (int i = 0; i < (Constants.QUALITY) / 2; i++) {

            angle += 360.0 / Constants.QUALITY;

            x = center.getX() + upperBaseRadius * Math.cos(Math.toRadians(angle));
            y = center.getY() + upperBaseRadius * Math.sin(Math.toRadians(angle));
            z = vertices.get(BASE_UPPER_CENTER_INDEX).z;

            vertices.add(new Point3D(x, y, z, 1));
        }
    }

    @Override
    public void initializeFaces() {
        faces = new int[Constants.QUALITY * 2 + 4][3];

        for (int i = 0; i < faces.length; i++) {
            int index = i + 2;
            //faces of lower base
            if (i <= Constants.QUALITY / 2) {
                faces[i][0] = BASE_LOWER_CENTER_INDEX;
                faces[i][1] = index;
                faces[i][2] = (i != Constants.QUALITY / 2) ? index + 1 : FIRST_LOWER_BASE_POINT;
            }
            //faces of upper base
            else if (i <= Constants.QUALITY + 1) {
                faces[i][0] = BASE_UPPER_CENTER_INDEX;
                faces[i][1] = index;
                faces[i][2] = (i != Constants.QUALITY + 1) ? index + 1 : FIRST_UPPER_BASE_POINT;
            }
            //faces of body from lower base
            else if (i <= Constants.QUALITY * 3 / 2 + 2) {

                faces[i][0] = index - Constants.QUALITY / 2 - 1;
                faces[i][1] = index - Constants.QUALITY - 2;
                faces[i][2] = (i != Constants.QUALITY * 3 / 2 + 2) ? index - Constants.QUALITY - 1 :
                        FIRST_LOWER_BASE_POINT;

            }
            //faces of body from upper base
            else {
                faces[i][0] = (i != Constants.QUALITY * 2 + 3) ? index - Constants.QUALITY * 3 / 2 - 2 :
                        FIRST_LOWER_BASE_POINT;
                faces[i][1] = index - Constants.QUALITY - 2;
                faces[i][2] = (i != Constants.QUALITY * 2 + 3) ? index - Constants.QUALITY - 1 :
                        FIRST_UPPER_BASE_POINT;
            }
        }
    }
}
