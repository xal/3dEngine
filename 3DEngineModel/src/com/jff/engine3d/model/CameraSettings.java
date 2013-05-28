package com.jff.engine3d.model;

import com.jff.engine3d.model.utils.draw.Point3D;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

public class CameraSettings {

    public Point3D toCoordinates;
    public Point3D fromCoordinates;
    public CameraRotateType rotateType;
    public RotationCoordinates rotationCoordinates;

    public CameraSettings(Point3D toCoordinates, Point3D fromCoordinates,
                          CameraRotateType rotateType, RotationCoordinates rotationCoordinates) {
        this.toCoordinates = toCoordinates;
        this.fromCoordinates = fromCoordinates;
        this.rotateType = rotateType;
        this.rotationCoordinates = rotationCoordinates;
    }
}
