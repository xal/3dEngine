package com.jff.engine3d.model.objec.primitives;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.entities.RotationCoordinates;
import com.jff.engine3d.model.entities.Triangle;
import com.jff.engine3d.model.math.TriangulateUtils;
import com.jff.engine3d.model.object.params.TrafficLightParams;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.primitives.Box;
import com.jff.engine3d.model.primitives.Cylinder;
import com.jff.engine3d.model.primitives.FrustumCone;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight extends AbstractObject {

    private TrafficLightParams params;

    private Box base;
    private FrustumCone cone;
    private Cylinder upperCylinder;
    private Cylinder lowerCylinder;
    private Box[] visors;
    private Cylinder[] lights;

    private int boxIndexOffset;
    private int upperCylinderIndexOffset;
    private int lowerCylinderIndexOffset;
    private int coneIndexOffset;
    private int visorsIndexOffset;
    private int lightsIndexOffset;
    private int visorsCountIndex;
    private int lightsCountIndex;

    public TrafficLight(Point3D center, TrafficLightParams trafficLightParams) {
        super(center);
        params = trafficLightParams;

        visors = new Box[trafficLightParams.numberVisors];
        lights = new Cylinder[trafficLightParams.numberLights];

        Point3D currentCenter;

        currentCenter = new Point3D(center);
        currentCenter.z += trafficLightParams.heightPoleCylinderUpper / 2;
        upperCylinder = new Cylinder(currentCenter, trafficLightParams.diameterPoleCylinderUpper / 2, trafficLightParams.heightPoleCylinderUpper);

        currentCenter = new Point3D(center);
        currentCenter.z -= trafficLightParams.heightPoleCylinderLower / 2;
        lowerCylinder = new Cylinder(currentCenter, trafficLightParams.diameterPoleCylinderLower / 2, trafficLightParams.heightPoleCylinderLower);

        currentCenter = new Point3D(center);
        currentCenter.z -= trafficLightParams.heightPoleCylinderLower;
        currentCenter.z -= trafficLightParams.heightPoleCone / 2;
        cone = new FrustumCone(currentCenter, trafficLightParams.diameterPoleConeUpper / 2, trafficLightParams.diameterPoleConeLower / 2, trafficLightParams.heightPoleCone);


        currentCenter = new Point3D(center);
        currentCenter.z += trafficLightParams.heightPoleCylinderUpper;
        currentCenter.z += trafficLightParams.heightBase / 2;
        base = new Box(currentCenter, trafficLightParams.widthBase, trafficLightParams.lengthBase, trafficLightParams.heightBase);

        for (int i = 0; i < visors.length; i++) {

            currentCenter = new Point3D(center);
            currentCenter.z += trafficLightParams.heightPoleCylinderUpper;
            currentCenter.z += trafficLightParams.heightBase / 2;

//            currentCenter.x += trafficLightParams.widthBase / 2;
            currentCenter.y += trafficLightParams.lengthBase / 2;
            currentCenter.z -= trafficLightParams.heightBase / 2;


            currentCenter.y += trafficLightParams.lengthVisor / 2;


            currentCenter.z += i * (trafficLightParams.heightVisor + trafficLightParams.diameterLight);
            currentCenter.z += trafficLightParams.diameterLight;
            currentCenter.z += trafficLightParams.heightVisor / 2;

            visors[i] = new Box(currentCenter, trafficLightParams.widthVisor, trafficLightParams.lengthVisor, trafficLightParams.heightVisor);


        }

        for (int i = 0; i < lights.length; i++) {

            currentCenter = new Point3D(center);
            currentCenter.z += trafficLightParams.heightPoleCylinderUpper;
            currentCenter.z += trafficLightParams.heightBase / 2;

//            currentCenter.x += trafficLightParams.widthBase / 2;
            currentCenter.y += trafficLightParams.lengthBase / 2;
            currentCenter.z -= trafficLightParams.heightBase / 2;

            currentCenter.y += trafficLightParams.lengthLight / 2;

            currentCenter.z += i * (trafficLightParams.diameterLight + trafficLightParams.heightVisor);
            currentCenter.z += trafficLightParams.diameterLight / 2;

            lights[i] = new Cylinder(currentCenter, trafficLightParams.diameterLight / 2, trafficLightParams.heightVisor);
            lights[i].rotate(new RotationCoordinates(90, 90, 0));

        }

        fireParametersChanged();
    }

    @Override
    protected void computeVertices() {
        List<Point3D> allVertexes = new ArrayList<Point3D>();


        boxIndexOffset = allVertexes.size();
        allVertexes.addAll(TriangulateUtils.createVertices(base));
        upperCylinderIndexOffset = allVertexes.size();
        allVertexes.addAll(TriangulateUtils.createVertices(upperCylinder));
        lowerCylinderIndexOffset = allVertexes.size();
        allVertexes.addAll(TriangulateUtils.createVertices(lowerCylinder));
        coneIndexOffset = allVertexes.size();
        allVertexes.addAll(TriangulateUtils.createVertices(cone));

        visorsIndexOffset = allVertexes.size();
        for (int i = 0; i < visors.length; i++) {

            allVertexes.addAll(visors[i].getVertexes());

        }
        visorsCountIndex = (allVertexes.size() - visorsIndexOffset) / visors.length;
        lightsIndexOffset = allVertexes.size();
        for (int i = 0; i < lights.length; i++) {
            allVertexes.addAll(lights[i].getVertexes());
        }
        lightsCountIndex = (allVertexes.size() - lightsIndexOffset) / lights.length;
        vertexes = allVertexes;
    }

    @Override
    protected void computeTriangles() {

        List<Triangle> allTriangles = new ArrayList<Triangle>();

        allTriangles.addAll(TriangulateUtils.triangulate(base, vertexes, boxIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(upperCylinder, vertexes, upperCylinderIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(lowerCylinder, vertexes, lowerCylinderIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(cone, vertexes, coneIndexOffset));
        for (int i = 0; i < visors.length; i++) {

            allTriangles.addAll(TriangulateUtils.triangulate(visors[i], vertexes, visorsIndexOffset + visorsCountIndex * i));
        }
        for (int i = 0; i < lights.length; i++) {

            allTriangles.addAll(TriangulateUtils.triangulate(lights[i], vertexes, lightsIndexOffset + lightsCountIndex * i));
        }


        triangles = allTriangles;
    }

    @Override
    protected double computeBorderSphereRadius() {

        int height = 0;
        height += params.heightBase;
        height += params.heightPoleCylinderLower;
        height += params.heightPoleCylinderUpper;
        height += params.heightPoleCone;

        int baseWidth = params.widthBase;
        int baseLength = params.lengthBase;

        double borderSphereRadius = height;

        borderSphereRadius = Math.max(borderSphereRadius, baseLength);
        borderSphereRadius = Math.max(borderSphereRadius, baseWidth);

        borderSphereRadius /= 2;

        return borderSphereRadius;

    }
}
