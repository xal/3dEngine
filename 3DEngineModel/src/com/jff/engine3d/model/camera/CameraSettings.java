package com.jff.engine3d.model.camera;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.entities.RotationCoordinates;
import com.jff.engine3d.model.entities.SpherePoint3D;

public class CameraSettings {

    private Point3D toCoordinates;
    private Point3D fromCoordinates;
    private CameraRotateType rotateType;
    private RotationCoordinates rotationCoordinates;
    private int focalLength;


    public Point3D realToCoordinates;
    public Point3D realFromCoordinates;
    public double theta;
    public double phi;
    private int cameraWidth;
    private int cameraHeight;

    public CameraSettings(Point3D toCoordinates, Point3D fromCoordinates, CameraRotateType rotateType, RotationCoordinates rotationCoordinates, int focalLength) {
        this.toCoordinates = toCoordinates;
        this.fromCoordinates = fromCoordinates;
        this.rotateType = rotateType;
        this.rotationCoordinates = rotationCoordinates;
        this.focalLength = focalLength;

        this.cameraWidth = 100000;
        this.cameraHeight = 100000;

        fireSettingsChanged();
    }

    public void fireSettingsChanged() {


        rotate();


        double x = realFromCoordinates.x - realToCoordinates.x;
        double y = realFromCoordinates.y - realToCoordinates.y;
        double z = realFromCoordinates.z - realToCoordinates.z;

//
//        System.out.println("realFrom " + realFromCoordinates);
//        System.out.println("realTo " + realToCoordinates);

        Point3D point3D = new Point3D(x, y, z);
//
//        System.out.println("calc point " + point3D);

        SpherePoint3D spherePoint3D = SpherePoint3D.fromCartesian(point3D);

//        System.out.println("Sphere " + spherePoint3D);
//        System.out.println("");

        theta = spherePoint3D.theta;
        phi = spherePoint3D.phi;


    }

    private Point3D rotate() {

        System.out.println(rotateType + "" + rotationCoordinates);

        if (rotateType == CameraRotateType.FROM) {
            Point3D rotateCenterPoint = fromCoordinates;
            realToCoordinates = rotate(rotateCenterPoint, toCoordinates, rotationCoordinates);
            realFromCoordinates = fromCoordinates;
        } else {

            Point3D rotateCenterPoint = toCoordinates;
            realFromCoordinates = rotate(rotateCenterPoint, fromCoordinates, rotationCoordinates);
            realToCoordinates = toCoordinates;
        }

        return realToCoordinates;
    }


    private Point3D rotate(Point3D rotateCenterPoint, Point3D rotatingPoint, RotationCoordinates rotationCoordinates) {

        Point3D vertex = new Point3D(rotatingPoint.x, rotatingPoint.y, rotatingPoint.z);

        Point3D centerPoint3D = rotateCenterPoint;
        vertex.x -= centerPoint3D.x;
        vertex.y -= centerPoint3D.y;
        vertex.z -= centerPoint3D.z;

        double xAngle = rotationCoordinates.getX();
        double yAngle = rotationCoordinates.getY();
        double zAngle = rotationCoordinates.getZ();

        vertex = vertex.rotateX(xAngle);
        vertex = vertex.rotateY(yAngle);
        vertex = vertex.rotateZ(zAngle);


        vertex.x += centerPoint3D.x;
        vertex.y += centerPoint3D.y;
        vertex.z += centerPoint3D.z;

        return vertex;
    }

    public Point3D getToCoordinates() {
        return toCoordinates;
    }

    public void setToCoordinates(Point3D toCoordinates) {
        this.toCoordinates = toCoordinates;
        fireSettingsChanged();
    }

    public Point3D getFromCoordinates() {
        return fromCoordinates;
    }

    public void setFromCoordinates(Point3D fromCoordinates) {
        this.fromCoordinates = fromCoordinates;
        fireSettingsChanged();
    }


    public CameraRotateType getRotateType() {
        return rotateType;
    }

    public void setRotateType(CameraRotateType rotateType) {
        this.rotateType = rotateType;
        fireSettingsChanged();
    }

    public RotationCoordinates getRotationCoordinates() {
        return rotationCoordinates;
    }

    public void setRotationCoordinates(RotationCoordinates rotationCoordinates) {
        this.rotationCoordinates = rotationCoordinates;
        fireSettingsChanged();
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
        fireSettingsChanged();
    }


    public Point3D getRealToCoordinates() {
        return realToCoordinates;
    }


    public Point3D getRealFromCoordinates() {
        return realFromCoordinates;
    }


    public double getTheta() {
        return theta;
    }


    public double getPhi() {
        return phi;
    }


    public int getCameraWidth() {
        return cameraWidth;
    }

    public int getCameraHeight() {
        return cameraHeight;
    }

    public void changeCameraBounds(int width, int height) {
        this.cameraWidth = width;
        this.cameraHeight = height;
        fireSettingsChanged();
    }
}
