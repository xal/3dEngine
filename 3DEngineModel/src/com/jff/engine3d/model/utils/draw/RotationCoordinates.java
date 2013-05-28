package com.jff.engine3d.model.utils.draw;

public class RotationCoordinates {

    private double xz;
    private double yz;
    private double xy;

    public RotationCoordinates(double xz, double yz, double xy) {
        this.xz = xz;
        this.yz = yz;
        this.xy = xy;
    }

    public double getXz() {
        return xz;
    }

    public void setXz(double xz) {
        this.xz = xz;
    }

    public double getYz() {
        return yz;
    }

    public void setYz(double yz) {
        this.yz = yz;
    }

    public double getXy() {
        return xy;
    }

    public void setXy(double xy) {
        this.xy = xy;
    }

    public RotationCoordinates clone() {
        return new RotationCoordinates(xz, yz, xy);
    }

    public double getXAngleInRadians() {
        return Math.toRadians(xz);
    }

    public double getYAngleInRadians() {
        return Math.toRadians(yz);
    }
}
