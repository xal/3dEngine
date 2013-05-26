package com.jff.engine3d.model.primitives;

import android.graphics.Color;
import com.custom_3DEnjine.Constants;
import com.custom_3DEnjine.drawing_utils.Coordinates;

import java.util.ArrayList;

public class Roller extends Primitive {

    private double verticalCilinderRaduis;
    private double verticalCilinderHeight;
    private double horisontalConeLowerRadius;
    private double horisontalConeUpperRadius;
    private double horisontalConeHeight;
    private double shuttersRadius;
    private double shuttersHeight;
    private double rackWidth;
    private double rackLength;
    private double rackHeight;
    private int wheelsCount;

    private HalfFrustCone horisontalCone;
	private Cilinder verticalCilinder;
    private ArrayList<Cilinder> shutters;
    private Parallelepiped rack;
    private ArrayList<Cilinder> wheels;

    /**
     * center which is not calculated with calculateCenter method
     */
    public Coordinates oldCenter;

    public Roller(Coordinates coordinates, double horisontalConeHeight, double horisontalConeLowerRadius,
                  double horisontalConeUpperRadius, double verticalCilinderHeight, double verticalCilinderRaduis,
                  double shuttersRadius, double shuttersHeight,
                  double rackWidth, double rackLength, double rackHeight,
                  int wheelsCount) {
        super(coordinates);
        this.horisontalConeHeight = horisontalConeHeight;
        this.horisontalConeLowerRadius = horisontalConeLowerRadius;
        this.horisontalConeUpperRadius = horisontalConeUpperRadius;
        this.verticalCilinderHeight = verticalCilinderHeight;
        this.verticalCilinderRaduis = verticalCilinderRaduis;
        this.shuttersRadius = shuttersRadius;
        this.shuttersHeight = shuttersHeight;
        this.rackWidth = rackWidth;
        this.rackLength = rackLength;
        this.rackHeight = rackHeight;
        this.wheelsCount = wheelsCount;

        initialize();
    }

    @Override
    public void initialize() {
        setUpRoller();
        super.initialize();
        setFacesColors();
    }

    public void setUpRoller() {
        setUpHorisontalCone();
        setUpVerticalCilinder();
        setUpShutters();
        setUpRack();
        setUpWheels(wheelsCount);

        this.viewWidth = (int) (horisontalConeHeight + verticalCilinderRaduis * 2 + Constants.BACKGROUND_PADDING);
        this.viewHeight = (int) (verticalCilinderHeight + shuttersHeight + rackHeight +
                wheels.get(0).cilinderHeight + Constants.BACKGROUND_PADDING);

        oldCenter = center.clone();
        center = calculateCenter();
    }

    public void setUpHorisontalCone() {
        Coordinates horisontalConeCenter = new Coordinates(center.getX() - verticalCilinderRaduis,
                center.getY() + verticalCilinderHeight / 2, center.getZ());

        this.horisontalCone = new HalfFrustCone(horisontalConeCenter, horisontalConeUpperRadius,
                horisontalConeLowerRadius, horisontalConeHeight);

        this.horisontalCone.rotate(new Coordinates(0, -90, 180), horisontalConeCenter);
    }

    public void setUpVerticalCilinder() {
        Coordinates verticalCilinderCenter = new Coordinates(center.getX(),
                center.getY(), center.getZ());

        this.verticalCilinder = new Cilinder(verticalCilinderCenter, verticalCilinderRaduis,
                verticalCilinderRaduis, verticalCilinderHeight);

        this.verticalCilinder.rotate(new Coordinates(90, 0, 0), verticalCilinderCenter);
    }

    public void setUpShutters() {
        shutters = new ArrayList<Cilinder>();

        Coordinates shutterCenter1 = new Coordinates(horisontalCone.center.getX(),
                center.getY() + verticalCilinderHeight / 2 + shuttersHeight / 2, center.getZ());
        Coordinates shutterCenter2 = new Coordinates(verticalCilinder.center.getX(),
                center.getY() + verticalCilinderHeight / 2 + shuttersHeight / 2, center.getZ());

        shutters.add(new Cilinder(shutterCenter1, shuttersRadius, shuttersRadius, shuttersHeight));
        shutters.add(new Cilinder(shutterCenter2, shuttersRadius, shuttersRadius, shuttersHeight));

        for (Cilinder cilinder : shutters) {
            cilinder.rotate(new Coordinates(90, 0, 0), cilinder.center);
        }
    }

    public void setUpRack() {
        Coordinates rackCenter = new Coordinates(horisontalCone.center.getX() + verticalCilinderRaduis / 2,
                shutters.get(0).center.getY() + shutters.get(0).cilinderHeight, center.getZ());

        this.rack = new Parallelepiped(rackCenter, rackWidth, rackLength, rackHeight);
    }

    public void setUpWheels(int count) {
        wheels = new ArrayList<Cilinder>();

        double wheelHeight = rackHeight - 10;
        double wheelRadius = rackWidth / (count * 2);

        for (int i = 0; i < count; i++) {
            double x = (i == 0) ? rack.center.getX() - rackWidth / 2 + wheelRadius :
                                  wheels.get(i - 1).center.getX() + wheelRadius * 2;
            Coordinates wheelCenter = new Coordinates(x, rack.center.getY() + rackHeight / 2, center.getZ());
            Cilinder wheel = new Cilinder(wheelCenter, wheelRadius, wheelRadius, wheelHeight);
            wheels.add(wheel);
        }
    }

    @Override
	public void initializeVertices() {
		this.vertices.addAll(horisontalCone.vertices);
		this.vertices.addAll(verticalCilinder.vertices);

        for (Cilinder cilinder : shutters) {
            this.vertices.addAll(cilinder.vertices);
        }
        this.vertices.addAll(rack.vertices);

        for (Cilinder wheel : wheels) {
            this.vertices.addAll(wheel.vertices);
        }
	}

	@Override
	public void initializeFaces() {
		this.faces = new int[horisontalCone.faces.length +
                verticalCilinder.faces.length +
                shutters.get(0).faces.length * shutters.size() +
                rack.faces.length +
                wheels.get(0).faces.length * wheels.size()][3];

        int[] offset = initializePrimitiveFaces(horisontalCone, 0, 0);

        offset = initializePrimitiveFaces(verticalCilinder, offset[0], offset[1]);

        for (Cilinder shutter : shutters)
            offset = initializePrimitiveFaces(shutter, offset[0], offset[1]);

        offset = initializePrimitiveFaces(rack, offset[0], offset[1]);

        for (Cilinder wheel : wheels)
            offset = initializePrimitiveFaces(wheel, offset[0], offset[1]);
	}

    public int[] initializePrimitiveFaces(Primitive primitive, int facesOffset, int verticesOffset) {
        for (int i = 0; i < primitive.faces.length; i++)
            for (int j = 0; j < primitive.faces[i].length; j++)
                this.faces[i + facesOffset][j] = primitive.faces[i][j] + verticesOffset;

        return new int[] {facesOffset + primitive.faces.length, verticesOffset + primitive.vertices.size()};
    }

    private void setFacesColors() {
        int offset = horisontalCone.faces.length;
        setFacesColor(Color.rgb(218, 113, 29), 0 , offset);

        offset += verticalCilinder.faces.length;
        setFacesColor(Color.rgb(218, 113, 29), offset - verticalCilinder.faces.length, offset);

        offset += shutters.get(0).faces.length * shutters.size();
        setFacesColor(Color.rgb(65, 70, 74), offset - shutters.get(0).faces.length * shutters.size(), offset);

        offset += rack.faces.length;
        setFacesColor(Color.rgb(156, 162, 165), offset - rack.faces.length, offset);

        offset += wheels.get(0).faces.length * wheels.size();
        setFacesColor(Color.rgb(243, 215, 126), offset - wheels.get(0).faces.length * wheels.size(), offset);
    }

    public Coordinates calculateCenter() {
        double x1 = rack.center.getX() + rackWidth / 2;
        double y1 = wheels.get(0).center.getY() + wheels.get(0).upperBaseRadius;

        double x2 = horisontalCone.center.getX() - horisontalConeHeight / 2;
        double y2 = verticalCilinder.center.getX() - verticalCilinderHeight / 2;

        double x = (x1 * 2 + x2 * 2) / 4.0;
        double y = (y1 * 2 + y2 * 2) / 4.0;

        return new Coordinates(x, y, this.center.getZ());
    }

    @Override
    public void scale(Coordinates offset) {
        super.scale(offset);

        this.horisontalConeHeight *= offset.getX();
        this.horisontalConeUpperRadius *= offset.getY();
        this.horisontalConeLowerRadius *= offset.getY();
        this.verticalCilinderRaduis *= offset.getX();
        this.verticalCilinderHeight *= offset.getY();
        this.shuttersRadius *= offset.getX();
        this.shuttersHeight *= offset.getY();
        this.rackWidth *= offset.getX();
        this.rackLength *= offset.getY();
        this.rackHeight *= offset.getZ();
    }

    public int getWheelsCount() {
        return wheelsCount;
    }

    public void setWheelsCount(int wheelsCount) {
        this.wheelsCount = wheelsCount;
    }
}
