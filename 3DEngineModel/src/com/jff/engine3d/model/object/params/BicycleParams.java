package com.jff.engine3d.model.object.params;

public class BicycleParams extends AbstractObjectParams {

    private int widthWheel;
    private int diameterWheel;

    private int widthBush;
    private int diameterBush;

    private int widthHelm;
    private int diameterHelm;

    private int widthSeat;
    private int heightSeat;

    private int widthPedal;
    private int heightPedal;


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
    public boolean verifyParams() throws Exception {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
