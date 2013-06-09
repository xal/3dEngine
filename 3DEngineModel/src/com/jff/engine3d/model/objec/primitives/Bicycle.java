package com.jff.engine3d.model.objec.primitives;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.entities.RotationCoordinates;
import com.jff.engine3d.model.entities.Triangle;
import com.jff.engine3d.model.math.TriangulateUtils;
import com.jff.engine3d.model.object.params.BicycleParams;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.primitives.Box;
import com.jff.engine3d.model.primitives.Cylinder;
import com.jff.engine3d.model.primitives.Tor;

import java.util.ArrayList;
import java.util.List;

public class Bicycle extends AbstractObject {
    private BicycleParams params;

    private Tor backWheel;
    private Tor frontWheel;

    private Cylinder backWheelBush;
    private Cylinder frontWheelBush;

    private Box leftPedal;
    private Box rightPedal;

    private Cylinder middlePedalBush;
    private Cylinder leftPedalBush;
    private Cylinder rightPedalBush;

    private Cylinder leftBackWheelPedalBush;
    private Cylinder rightBackWheelPedalBush;


//    private Cylinder leftBackWheelSeatBush = new Cylinder(new Point3D(0, 0, 0), 10, 10);
//
//    private Cylinder rightBackWheelSeatBush = new Cylinder(new Point3D(0, 0, 0), 10, 10);
//    private Cylinder pedalHelmBush = new Cylinder(new Point3D(0, 0, 0), 10, 10);


    private Cylinder seatHelmBush;


    private Cylinder pedalSeatBush;

    private Cylinder helm;
    private Cylinder helmBush;

    private Cylinder leftFrontWheelHelmBush;

    private Cylinder middleFrontWheelHelmBush;

    private Cylinder rightFrontWheelHelmBush;


    private Cylinder helmFrontWheelBush;

    private Box seat;

    private int backWheelIndexOffset;
    private int backWheelBushIndexOffset;
    private int frontWheelIndexOffset;
    private int frontWheelBushIndexOffset;
    private int leftPedalIndexOffset;
    private int rightPedalIndexOffset;
    private int rightPedalBushIndexOffset;
    private int middlePedalBushIndexOffset;
    private int leftPedalBushIndexOffset;
    private int seatHelmBushIndexOffset;
    private int pedalHelmBushIndexOffset;
    private int pedalSeatBushIndexOffset;
    private int helmIndexOffset;
    private int helmBushIndexOffset;
    private int seatIndexOffset;
    private int leftBackWheelPedalBushIndexOffset;
    private int leftBackWheelSeatBushIndexOffset;
    private int leftFrontWheelHelmBushIndexOffset;
    private int rightBackWheelPedalBushIndexOffset;
    private int rightBackWheelSeatBushIndexOffset;
    private int rightFrontWheelHelmBushIndexOffset;
    private int middleFrontWheelHelmBushIndexOffset;
    private int helmFrontWheelBushIndexOffset;


    public Bicycle(Point3D center, BicycleParams bicycleParams) {
        super(center);
        this.params = bicycleParams;


        Point3D seatCenter = new Point3D(center.x, center.y + bicycleParams.heightSeat / 2 + bicycleParams.pedalSeatBush / 2, center.z);
        Point3D pedalSeatBushCenter = new Point3D(center.x, center.y, center.z);

        Point3D middlePedalBushCenter = new Point3D(center.x, center.y - bicycleParams.pedalSeatBush / 2, center.z);


        Point3D leftPedalBushCenter =
                new Point3D(middlePedalBushCenter.x, middlePedalBushCenter.y - bicycleParams.leftPedalBushHeight / 2,
                        middlePedalBushCenter.z - bicycleParams.middlePedalBushHeight / 2);
        Point3D rightPedalBushCenter = new Point3D(middlePedalBushCenter.x, middlePedalBushCenter.y + bicycleParams.rightPedalBushHeight / 2,
                middlePedalBushCenter.z + bicycleParams.middlePedalBushHeight / 2);

        Point3D leftPedalCenter = new Point3D(leftPedalBushCenter.x,
                leftPedalBushCenter.y - bicycleParams.leftPedalBushHeight / 2 - bicycleParams.heightPedal / 2,
                leftPedalBushCenter.z);
        Point3D rightPedalCenter = new Point3D(rightPedalBushCenter.x,
                rightPedalBushCenter.y + bicycleParams.rightPedalBushHeight / 2 + bicycleParams.heightPedal / 2,
                rightPedalBushCenter.z);

        Point3D seatHelmBushCenter = new Point3D(center.x + bicycleParams.seatHelmBushHeight / 2,
                center.y + bicycleParams.pedalSeatBush / 2 - bicycleParams.heightPedal * 2, center.z);

        Point3D helmBushCenter = new Point3D(seatHelmBushCenter.x + bicycleParams.seatHelmBushHeight / 2,
                seatHelmBushCenter.y + bicycleParams.widthHelmBush / 2, seatHelmBushCenter.z);

        Point3D helmCenter = new Point3D(helmBushCenter.x,
                helmBushCenter.y + bicycleParams.widthHelmBush / 2, helmBushCenter.z);

        Point3D frontWheelCenter = new Point3D(center.x + bicycleParams.seatHelmBushHeight,
                center.y - bicycleParams.pedalSeatBush / 2, center.z);
        Point3D backWheelCenter = new Point3D(center.x - bicycleParams.seatHelmBushHeight,
                center.y - bicycleParams.pedalSeatBush / 2, center.z);

        Point3D frontWheelBushCenter = frontWheelCenter;
        Point3D backWheelBushCenter = backWheelCenter;

        middlePedalBush = new Cylinder(middlePedalBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.middlePedalBushHeight);


        leftPedalBush = new Cylinder(leftPedalBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.leftPedalBushHeight);
        leftPedalBush.rotate(new RotationCoordinates(0, 90, 90));
        rightPedalBush = new Cylinder(rightPedalBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.rightPedalBushHeight);
        rightPedalBush.rotate(new RotationCoordinates(0, 90, 90));

        leftPedal = new Box(leftPedalCenter, bicycleParams.widthPedal, bicycleParams.lengthPedal, bicycleParams.heightPedal);
        leftPedal.rotate(new RotationCoordinates(0, 90, 90));

        rightPedal = new Box(rightPedalCenter, bicycleParams.widthPedal, bicycleParams.lengthPedal, bicycleParams.heightPedal);
        rightPedal.rotate(new RotationCoordinates(0, 90, 90));

        pedalSeatBush = new Cylinder(pedalSeatBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.pedalSeatBush);

        pedalSeatBush.rotate(new RotationCoordinates(0, 90, 90));


        seat = new Box(seatCenter, bicycleParams.widthSeat, bicycleParams.lengthSeat, bicycleParams.heightSeat);
        seat.rotate(new RotationCoordinates(0, 90, 90));

        frontWheel = new Tor(frontWheelCenter, bicycleParams.diameterWheel / 2, bicycleParams.widthWheel / 2);
        backWheel = new Tor(backWheelCenter, bicycleParams.diameterWheel / 2, bicycleParams.widthWheel / 2);

        frontWheelBush = new Cylinder(frontWheelBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.wheelBushHeight);

        backWheelBush = new Cylinder(backWheelBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.wheelBushHeight);


        helm = new Cylinder(helmCenter, bicycleParams.diameterFrame / 2, bicycleParams.widthHelm);

        helmBush = new Cylinder(helmBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.widthHelmBush);
        helmBush.rotate(new RotationCoordinates(0, 90, 90));

        seatHelmBush = new Cylinder(seatHelmBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.seatHelmBushHeight);
        seatHelmBush.rotate(new RotationCoordinates(90, 90, 90));

        int helmFrontWheelBushHeight = bicycleParams.widthHelmBush;
        Point3D helmFrontWheelBushCenter = new Point3D(helmBushCenter);
        helmFrontWheelBushCenter.y -= helmFrontWheelBushHeight;

        Point3D frontWheelUpperBushCenter = new Point3D(helmFrontWheelBushCenter);
        frontWheelUpperBushCenter.y -= helmFrontWheelBushHeight / 2 + bicycleParams.diameterFrame / 2;

        double frontBushHeight = Math.abs(frontWheelBushCenter.y + frontWheelUpperBushCenter.y);

        Point3D leftFrontWheelHelmBushCenter = new Point3D(frontWheelUpperBushCenter);
        Point3D rightFrontWheelHelmBushCenter = new Point3D(frontWheelUpperBushCenter);
        leftFrontWheelHelmBushCenter.z += bicycleParams.wheelBushHeight / 2;
        rightFrontWheelHelmBushCenter.z -= bicycleParams.wheelBushHeight / 2;
        leftFrontWheelHelmBushCenter.y -= frontBushHeight / 2;
        rightFrontWheelHelmBushCenter.y -= frontBushHeight / 2;


        leftFrontWheelHelmBush = new Cylinder(leftFrontWheelHelmBushCenter, bicycleParams.diameterFrame / 2, frontBushHeight);
        rightFrontWheelHelmBush = new Cylinder(rightFrontWheelHelmBushCenter, bicycleParams.diameterFrame / 2, frontBushHeight);
        leftFrontWheelHelmBush.rotate(new RotationCoordinates(0, 90, 90));
        rightFrontWheelHelmBush.rotate(new RotationCoordinates(0, 90, 90));

        middleFrontWheelHelmBush = new Cylinder(frontWheelUpperBushCenter, bicycleParams.diameterFrame / 2, bicycleParams.wheelBushHeight);


        helmFrontWheelBush = new Cylinder(helmFrontWheelBushCenter, bicycleParams.diameterFrame / 2, helmFrontWheelBushHeight);
        helmFrontWheelBush.rotate(new RotationCoordinates(0, 90, 90));


        int backWheelPedalBushHeight = (int) Math.abs(backWheelBushCenter.x - middlePedalBushCenter.x);
        Point3D leftBackWheelPedalBushCenter = new Point3D(middlePedalBushCenter);
        leftBackWheelPedalBushCenter.x -= backWheelPedalBushHeight / 2;
        leftBackWheelPedalBushCenter.z -= bicycleParams.middlePedalBushHeight / 2;

        Point3D rightBackWheelPedalBushCenter = new Point3D(middlePedalBushCenter);
        rightBackWheelPedalBushCenter.x -= backWheelPedalBushHeight / 2;
        rightBackWheelPedalBushCenter.z += bicycleParams.middlePedalBushHeight / 2;

        leftBackWheelPedalBush = new Cylinder(leftBackWheelPedalBushCenter, bicycleParams.diameterFrame / 2, backWheelPedalBushHeight);
        rightBackWheelPedalBush = new Cylinder(rightBackWheelPedalBushCenter, bicycleParams.diameterFrame / 2, backWheelPedalBushHeight);
        leftBackWheelPedalBush.rotate(new RotationCoordinates(90, 0, 90));
        rightBackWheelPedalBush.rotate(new RotationCoordinates(90, 0, 90));


        this.rotate(new RotationCoordinates(180, 0, 90));
        fireParametersChanged();

    }

    @Override
    protected void computeVertices() {
        List<Point3D> allVertexes = new ArrayList<Point3D>();


        backWheelIndexOffset = allVertexes.size();

        backWheel.fireParametersChanged();
        allVertexes.addAll(backWheel.getVertexes());
        backWheelBushIndexOffset = allVertexes.size();
        backWheelBush.fireParametersChanged();
        allVertexes.addAll(backWheelBush.getVertexes());
        frontWheelIndexOffset = allVertexes.size();
        frontWheel.fireParametersChanged();
        allVertexes.addAll(frontWheel.getVertexes());
        frontWheelBushIndexOffset = allVertexes.size();
        frontWheelBush.fireParametersChanged();
        allVertexes.addAll(frontWheelBush.getVertexes());
        leftPedalIndexOffset = allVertexes.size();
        leftPedal.fireParametersChanged();
        allVertexes.addAll(leftPedal.getVertexes());
        rightPedalIndexOffset = allVertexes.size();
        rightPedal.fireParametersChanged();
        allVertexes.addAll(rightPedal.getVertexes());
        rightPedalBushIndexOffset = allVertexes.size();

        rightPedalBush.fireParametersChanged();
        allVertexes.addAll(rightPedalBush.getVertexes());
        middlePedalBushIndexOffset = allVertexes.size();
        middlePedalBush.fireParametersChanged();
        allVertexes.addAll(middlePedalBush.getVertexes());
        leftPedalBushIndexOffset = allVertexes.size();
        leftPedalBush.fireParametersChanged();
        allVertexes.addAll(leftPedalBush.getVertexes());
        seatHelmBushIndexOffset = allVertexes.size();

        seatHelmBush.fireParametersChanged();
        allVertexes.addAll(seatHelmBush.getVertexes());
//        pedalHelmBushIndexOffset = allVertexes.size();
//        pedalHelmBush.fireParametersChanged();
//        allVertexes.addAll(pedalHelmBush.getVertexes());
        pedalSeatBushIndexOffset = allVertexes.size();

        pedalSeatBush.fireParametersChanged();
        allVertexes.addAll(pedalSeatBush.getVertexes());
        helmIndexOffset = allVertexes.size();

        helm.fireParametersChanged();
        allVertexes.addAll(helm.getVertexes());
        helmBushIndexOffset = allVertexes.size();
        helmBush.fireParametersChanged();
        allVertexes.addAll(helmBush.getVertexes());
        seatIndexOffset = allVertexes.size();
        seat.fireParametersChanged();
        allVertexes.addAll(seat.getVertexes());
        leftBackWheelPedalBushIndexOffset = allVertexes.size();

        leftBackWheelPedalBush.fireParametersChanged();
        allVertexes.addAll(leftBackWheelPedalBush.getVertexes());
//        leftBackWheelSeatBushIndexOffset = allVertexes.size();
//        leftBackWheelSeatBush.fireParametersChanged();
//        allVertexes.addAll(leftBackWheelSeatBush.getVertexes());
        leftFrontWheelHelmBushIndexOffset = allVertexes.size();
        leftFrontWheelHelmBush.fireParametersChanged();
        allVertexes.addAll(leftFrontWheelHelmBush.getVertexes());
        rightBackWheelPedalBushIndexOffset = allVertexes.size();


        rightBackWheelPedalBush.fireParametersChanged();
        allVertexes.addAll(rightBackWheelPedalBush.getVertexes());
//        rightBackWheelSeatBushIndexOffset = allVertexes.size();
//        rightBackWheelSeatBush.fireParametersChanged();
//        allVertexes.addAll(rightBackWheelSeatBush.getVertexes());
        rightFrontWheelHelmBushIndexOffset = allVertexes.size();
        rightFrontWheelHelmBush.fireParametersChanged();
        allVertexes.addAll(rightFrontWheelHelmBush.getVertexes());
        middleFrontWheelHelmBushIndexOffset = allVertexes.size();

        middleFrontWheelHelmBush.fireParametersChanged();
        allVertexes.addAll(middleFrontWheelHelmBush.getVertexes());
        helmFrontWheelBushIndexOffset = allVertexes.size();

        helmFrontWheelBush.fireParametersChanged();
        allVertexes.addAll(helmFrontWheelBush.getVertexes());


        vertexes = allVertexes;
    }

    @Override
    protected void computeTriangles() {

        List<Triangle> allTriangles = new ArrayList<Triangle>();

        allTriangles.addAll(TriangulateUtils.triangulate(backWheel, vertexes, backWheelIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(backWheelBush, vertexes, backWheelBushIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(frontWheel, vertexes, frontWheelIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(frontWheelBush, vertexes, frontWheelBushIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(leftPedal, vertexes, leftPedalIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(rightPedal, vertexes, rightPedalIndexOffset));

        allTriangles.addAll(TriangulateUtils.triangulate(rightPedalBush, vertexes, rightPedalBushIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(middlePedalBush, vertexes, middlePedalBushIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(leftPedalBush, vertexes, leftPedalBushIndexOffset));

        allTriangles.addAll(TriangulateUtils.triangulate(seatHelmBush, vertexes, seatHelmBushIndexOffset));
//        allTriangles.addAll(TriangulateUtils.triangulate(pedalHelmBush, vertexes, pedalHelmBushIndexOffset));

        allTriangles.addAll(TriangulateUtils.triangulate(pedalSeatBush, vertexes, pedalSeatBushIndexOffset));

        allTriangles.addAll(TriangulateUtils.triangulate(helm, vertexes, helmIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(helmBush, vertexes, helmBushIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(seat, vertexes, seatIndexOffset));

        allTriangles.addAll(TriangulateUtils.triangulate(leftBackWheelPedalBush, vertexes, leftBackWheelPedalBushIndexOffset));
//        allTriangles.addAll(TriangulateUtils.triangulate(leftBackWheelSeatBush, vertexes, leftBackWheelSeatBushIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(leftFrontWheelHelmBush, vertexes, leftFrontWheelHelmBushIndexOffset));


        allTriangles.addAll(TriangulateUtils.triangulate(rightBackWheelPedalBush, vertexes, rightBackWheelPedalBushIndexOffset));
//        allTriangles.addAll(TriangulateUtils.triangulate(rightBackWheelSeatBush, vertexes, rightBackWheelSeatBushIndexOffset));
        allTriangles.addAll(TriangulateUtils.triangulate(rightFrontWheelHelmBush, vertexes, rightFrontWheelHelmBushIndexOffset));

        allTriangles.addAll(TriangulateUtils.triangulate(middleFrontWheelHelmBush, vertexes, middleFrontWheelHelmBushIndexOffset));

        allTriangles.addAll(TriangulateUtils.triangulate(helmFrontWheelBush, vertexes, helmFrontWheelBushIndexOffset));


        this.triangles = allTriangles;
    }

    @Override
    protected double computeBorderSphereRadius() {

        Point3D center = getCenter();

        Point3D backWheelCenter = backWheel.getCenter();
        Point3D frontWheelCenter = frontWheel.getCenter();

        Tor farWheel = center.distanceTo(backWheelCenter) > center.distanceTo(frontWheelCenter) ? backWheel : frontWheel;

        double borderRadius;

        borderRadius = center.distanceTo(farWheel.getCenter());
        borderRadius += farWheel.getBorderSphereRadius();

        return borderRadius;


    }
}
