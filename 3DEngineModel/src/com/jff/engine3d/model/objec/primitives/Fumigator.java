package com.jff.engine3d.model.objec.primitives;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.entities.RotationCoordinates;
import com.jff.engine3d.model.entities.Triangle;
import com.jff.engine3d.model.math.TriangulateUtils;
import com.jff.engine3d.model.object.params.FumigatorParams;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.primitives.Box;
import com.jff.engine3d.model.primitives.Cylinder;

import java.util.ArrayList;
import java.util.List;

public class Fumigator extends AbstractObject {

    private FumigatorParams params;

    private Box baseBox;
    private Box socketBox;
    private Cylinder socketCylinder1;
    private Cylinder socketCylinder2;
    private Cylinder middleCylinder;
    private Cylinder lowerCylinder;


    private int boxIndexOffset;
    private int socketBoxIndexOffset;
    private int socketCylinder1IndexOffset;
    private int socketCylinder2IndexOffset;
    private int middleCylinderIndexOffset;
    private int lowerCylinderIndexOffset;

    public Fumigator(Point3D center, FumigatorParams myParams) {
        super(center);
        params = myParams;

        Point3D currentCenter;

        currentCenter = new Point3D(center);
        currentCenter.z -= myParams.heightMiddleCylinder / 2;
        middleCylinder = new Cylinder(currentCenter, params.diameterMiddleCylinder, params.heightMiddleCylinder);

        currentCenter = new Point3D(center);
        currentCenter.z -= myParams.heightMiddleCylinder + myParams.heightLowerCylinder / 2;
        lowerCylinder = new Cylinder(currentCenter, params.diameterLowerCylinder, params.heightLowerCylinder);


        currentCenter = new Point3D(center);
        currentCenter.z += myParams.heightBaseBox / 2;
        baseBox = new Box(currentCenter, myParams.widthBaseBox, myParams.lengthBaseBox, myParams.heightBaseBox);


        currentCenter = new Point3D(center);
        currentCenter.z += myParams.heightBaseBox / 2;
        currentCenter.x += myParams.widthBaseBox / 2 + myParams.widthSocketBox / 2;
        socketBox = new Box(currentCenter, myParams.widthSocketBox, myParams.lengthSocketBox, myParams.heightSocketBox);


        currentCenter = new Point3D(center);
        currentCenter.z += myParams.heightBaseBox / 2;
        currentCenter.x += myParams.widthBaseBox / 2 + myParams.widthSocketBox + myParams.heightSocketCylinder / 2;
        currentCenter.y += myParams.lengthSocketBox / 3;
        socketCylinder1 = new Cylinder(currentCenter, params.diameterSocketCylinder, params.heightSocketCylinder);

        socketCylinder1.rotate(new RotationCoordinates(90, 0, 90));


        currentCenter = new Point3D(center);
        currentCenter.z += myParams.heightBaseBox / 2;
        currentCenter.x += myParams.widthBaseBox / 2 + myParams.widthSocketBox + myParams.heightSocketCylinder / 2;
        currentCenter.y -= myParams.lengthSocketBox / 3;
        socketCylinder2 = new Cylinder(currentCenter, params.diameterSocketCylinder, params.heightSocketCylinder);
        socketCylinder2.rotate(new RotationCoordinates(90, 0, 90));


        fireParametersChanged();
    }

    @Override
    protected void computeVertices() {
        List<Point3D> allVertexes = new ArrayList<Point3D>();


        boxIndexOffset = allVertexes.size();
        allVertexes.addAll(TriangulateUtils.createVertices(baseBox));
        socketBoxIndexOffset = allVertexes.size();
        allVertexes.addAll(TriangulateUtils.createVertices(socketBox));
        middleCylinderIndexOffset = allVertexes.size();
        allVertexes.addAll(TriangulateUtils.createVertices(middleCylinder));
        lowerCylinderIndexOffset = allVertexes.size();
        allVertexes.addAll(TriangulateUtils.createVertices(lowerCylinder));
        socketCylinder1IndexOffset = allVertexes.size();
        allVertexes.addAll(socketCylinder1.getVertexes());
        socketCylinder2IndexOffset = allVertexes.size();
        allVertexes.addAll(socketCylinder2.getVertexes());

        this.vertexes = allVertexes;
    }

    @Override
    protected void computeTriangles() {

        List<Triangle> allTriangles = new ArrayList<Triangle>();

        allTriangles.addAll(TriangulateUtils.triangulate(baseBox, vertexes, boxIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(socketBox, vertexes, socketBoxIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(middleCylinder, vertexes, middleCylinderIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(lowerCylinder, vertexes, lowerCylinderIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(socketCylinder1, vertexes, socketCylinder1IndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(socketCylinder2, vertexes, socketCylinder2IndexOffset));


        triangles = allTriangles;
    }

    @Override
    protected double computeBorderSphereRadius() {

        int height = 0;
        height += params.heightBaseBox;
        height += params.heightSocketBox;
        height += params.heightLowerCylinder;
        height += params.heightMiddleCylinder;

        int baseWidth = params.widthBaseBox;
        int baseLength = params.lengthBaseBox;
        baseWidth += params.widthSocketBox;
        baseWidth += params.heightSocketCylinder;

        double borderSphereRadius = height;

        borderSphereRadius = Math.max(borderSphereRadius, baseLength);
        borderSphereRadius = Math.max(borderSphereRadius, baseWidth);

        borderSphereRadius /= 2;

        return borderSphereRadius;

    }

    @Override
    public String toString() {
        return "Fumigator{" +

                "Current center = "
                + getCenter() +
                "Rotate = " + getRotationCoordinates() +
                "Scale = " + getScale() +
                '}';
    }
}
