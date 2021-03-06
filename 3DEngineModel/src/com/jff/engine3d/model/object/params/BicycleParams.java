package com.jff.engine3d.model.object.params;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.objec.primitives.Bicycle;
import com.jff.engine3d.model.primitives.AbstractObject;

public class BicycleParams extends AbstractObjectParams {

    public int widthWheel;
    public int diameterWheel;


    public int widthHelm;

    public int widthSeat;
    public int heightSeat;
    public int lengthSeat;

    public int widthPedal;
    public int heightPedal;
    public int lengthPedal;

    public int diameterFrame;

    public int pedalSeatBush;
    public int middlePedalBushHeight;
    public int leftPedalBushHeight;
    public int rightPedalBushHeight;
    public int wheelBushHeight;
    public int widthHelmBush;
    public int seatHelmBushHeight;


    public BicycleParams(int widthWheel, int diameterWheel, int lengthSeat, int diameterFrame,
                         int widthHelm, int lengthPedal, int widthSeat, int heightSeat,
                         int widthPedal, int heightPedal) {
        this.widthWheel = widthWheel;
        this.diameterWheel = diameterWheel;
        this.lengthSeat = lengthSeat;
        this.diameterFrame = diameterFrame;
        this.widthHelm = widthHelm;
        this.widthSeat = widthSeat;
        this.heightSeat = heightSeat;

        this.lengthPedal = lengthPedal;
        this.widthPedal = widthPedal;
        this.heightPedal = heightPedal;

        pedalSeatBush = 200;
        middlePedalBushHeight = 80;
        leftPedalBushHeight = 20;
        rightPedalBushHeight = 20;
        wheelBushHeight = middlePedalBushHeight;
        widthHelmBush = 20;
        seatHelmBushHeight = 200;

    }

    @Override
    public boolean verifyParams() {

        String error = "Not valid parameters. ";
        if (diameterWheel <= widthWheel) {

            throw new IllegalArgumentException(error + "diameterWheel <= widthWheel");
        }
        if (diameterWheel <= diameterFrame) {
            throw new IllegalArgumentException(error + "diameterWheel <= diameterFrame");
        }

        if (diameterWheel > 150) {
            throw new IllegalArgumentException(error + "diameterWheel > 150");
        }
        if (diameterWheel < 20) {
            throw new IllegalArgumentException(error + "diameterWheel < 20");
        }
        if (widthWheel < 10) {
            throw new IllegalArgumentException(error + "widthWheel <10");
        }

        return true;
    }

    @Override
    public AbstractObject createObject(Point3D center) {
        verifyParams();
        return new Bicycle(center, this);
    }
}
