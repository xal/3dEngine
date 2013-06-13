package com.jff.engine3d.model.object.params;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.objec.primitives.Fumigator;
import com.jff.engine3d.model.primitives.AbstractObject;

public class FumigatorParams extends AbstractObjectParams {

    public int diameterSocketCylinder;
    public int heightSocketCylinder;

    public int widthSocketBox;
    public int lengthSocketBox;
    public int heightSocketBox;

    public int widthBaseBox;
    public int lengthBaseBox;
    public int heightBaseBox;


    public int diameterLowerCylinder;
    public int heightLowerCylinder;

    public int diameterMiddleCylinder;
    public int heightMiddleCylinder;


    public FumigatorParams(int diameterSocketCylinder, int heightSocketCylinder, int widthSocketBox, int lengthSocketBox, int heightSocketBox, int widthBaseBox, int lengthBaseBox, int heightBaseBox, int diameterLowerCylinder, int heightLowerCylinder, int diameterMiddleCylinder, int heightMiddleCylinder) {
        this.diameterSocketCylinder = diameterSocketCylinder;
        this.heightSocketCylinder = heightSocketCylinder;
        this.widthSocketBox = widthSocketBox;
        this.lengthSocketBox = lengthSocketBox;
        this.heightSocketBox = heightSocketBox;
        this.widthBaseBox = widthBaseBox;
        this.lengthBaseBox = lengthBaseBox;
        this.heightBaseBox = heightBaseBox;
        this.diameterLowerCylinder = diameterLowerCylinder;
        this.heightLowerCylinder = heightLowerCylinder;
        this.diameterMiddleCylinder = diameterMiddleCylinder;
        this.heightMiddleCylinder = heightMiddleCylinder;
    }

    @Override
    public boolean verifyParams() {
        boolean result = true;

        if (diameterLowerCylinder < diameterMiddleCylinder) {
            throw new IllegalArgumentException("diameterLowerCylinder < diameterMiddleCylinder");
        }


        return result;
    }

    @Override
    public AbstractObject createObject(Point3D center) {
        verifyParams();
        return new Fumigator(center, this);
    }
}
