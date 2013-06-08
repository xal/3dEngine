package com.jff.engine3d.model.object.params;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.objec.primitives.Bicycle;
import com.jff.engine3d.model.primitives.AbstractObject;

public class BicycleParams extends AbstractObjectParams {

    public int widthWheel;
    public int diameterWheel;

    public int widthBush;
    public int diameterBush;

    public int widthHelm;
    public int diameterHelm;

    public int widthSeat;
    public int heightSeat;

    public int widthPedal;
    public int heightPedal;


    public BicycleParams(int widthWheel, int diameterWheel, int widthBush, int diameterBush,
                         int widthHelm, int diameterHelm, int widthSeat, int heightSeat,
                         int widthPedal, int heightPedal) {
        this.widthWheel = widthWheel;
        this.diameterWheel = diameterWheel;
        this.widthBush = widthBush;
        this.diameterBush = diameterBush;
        this.widthHelm = widthHelm;
        this.diameterHelm = diameterHelm;
        this.widthSeat = widthSeat;
        this.heightSeat = heightSeat;
        this.widthPedal = widthPedal;
        this.heightPedal = heightPedal;
    }

    @Override
    public boolean verifyParams() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AbstractObject createObject(Point3D center) {
        verifyParams();
        return new Bicycle(center, this);
    }
}
