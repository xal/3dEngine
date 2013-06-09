package com.jff.engine3d.model.object.params;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.primitives.AbstractObject;

public class TrafficLightParams extends AbstractObjectParams {


    public int diameterPoleConeLower;
    public int heightPoleCone;

    public int diameterPoleConeUpper;

    public int heightPoleCylinderLower;
    public int diameterPoleCylinderLower;

    public int heightPoleCylinderUpper;
    public int diameterPoleCylinderUpper;


    public int heightBase;
    public int widthBase;
    public int lengthBase;

    //огни
    public int diameterLight;


    public int lengthLight;


    public int numberLights;


    public int heightVisor;
    public int widthVisor;
    public int lengthVisor;

    public int numberVisors;

    public TrafficLightParams(int heightPoleCone, int diameterPoleConeLower, int heightPoleCylinderLower, int diameterPoleCylinderLower, int heightPoleCylinderUpper, int diameterPoleCylinderUpper, int heightBase, int widthBase, int lengthBase, int diameterLight, int lengthLight, int numberLights, int heightVisor, int widthVisor, int lengthVisor) {
        this.heightPoleCone = heightPoleCone;
        this.diameterPoleConeUpper = diameterPoleCylinderLower;
        this.diameterPoleConeLower = diameterPoleConeLower;
        this.heightPoleCylinderLower = heightPoleCylinderLower;
        this.diameterPoleCylinderLower = diameterPoleCylinderLower;
        this.heightPoleCylinderUpper = heightPoleCylinderUpper;
        this.diameterPoleCylinderUpper = diameterPoleCylinderUpper;
        this.heightBase = heightBase;
        this.widthBase = widthBase;
        this.lengthBase = lengthBase;
        this.diameterLight = diameterLight;
        this.lengthLight = lengthLight;
        this.numberLights = numberLights;
        this.heightVisor = heightVisor;
        this.widthVisor = widthVisor;
        this.lengthVisor = lengthVisor;
        this.numberVisors = numberLights;
    }

    @Override
    public boolean verifyParams() {
        boolean result = true;

        if (diameterPoleConeLower < diameterPoleCylinderLower) {
            throw new IllegalArgumentException("diameterPoleConeLower < diameterPoleCylinderLower");
        }
        if (diameterPoleCylinderLower < diameterPoleCylinderUpper) {
            throw new IllegalArgumentException("diameterPoleCylinderLower < diameterPoleCylinderUpper");
        }
        if (diameterPoleCylinderUpper > widthBase) {
            throw new IllegalArgumentException("diameterPoleCylinderUpper > widthBase");
        }
        if (diameterPoleCylinderUpper > lengthBase) {
            throw new IllegalArgumentException("diameterPoleCylinderUpper > lengthBase");
        }

        if (diameterLight > widthBase) {
            throw new IllegalArgumentException("diameterLight > widthBase");
        }
        if (diameterLight > lengthBase) {
            throw new IllegalArgumentException("diameterLight  > lengthBase");
        }

        if (widthVisor > widthBase) {
            throw new IllegalArgumentException("widthVisor > widthBase");
        }

        if (heightBase < numberVisors * heightVisor + numberLights * diameterLight) {
            throw new IllegalArgumentException("heightBase < numberVisors * heightVisor + numberLights + diameterLight");
        }

        return result;
    }

    @Override
    public AbstractObject createObject(Point3D center) {
        verifyParams();
        return new com.jff.engine3d.model.objec.primitives.TrafficLight(center, this);
    }
}
