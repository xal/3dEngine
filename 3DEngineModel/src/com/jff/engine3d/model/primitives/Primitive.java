package com.jff.engine3d.model.primitives;


import com.jff.engine3d.model.Engine;
import com.jff.engine3d.model.EngineManager;
import com.jff.engine3d.model.utils.draw.*;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Primitive {

    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Primitive primitive = (Primitive) o;

        if (id != primitive.id) return false;
        if (isOnFocus != primitive.isOnFocus) return false;
        if (viewHeight != primitive.viewHeight) return false;
        if (viewWidth != primitive.viewWidth) return false;
        if (!Arrays.equals(backgroundVertices, primitive.backgroundVertices)) return false;
        if (!Arrays.equals(drawers, primitive.drawers)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Vertices of the primitive.
     */
    protected ArrayList<Point3D> vertices;

    /**
     * Define the indices to the vertices of each face of the cube.
     */
    protected int faces[][];

    /**
     * Orientation of the cube around X,Y,Z axises.
     */
    public float ax;
    public float ay;
    public float az;

    /**
     * Position of last users touch
     */
    protected float lastTouchX;
    protected float lastTouchY;

    /**
     * Diagonals of multitouch event
     * difference between them define how to change size of primitive
     */
    protected double previousDiagonal;
    protected double currentDiagonal;

    /**
     * Position of the primitive
     */
    public Coordinates center;

    /**
     * State which said is this primitive enabled with long-click or not
     */
    protected boolean isEnabled = false;

    /**
     * State which said is this primitive focused at this moment or not
     */
    protected boolean isOnFocus = false;

    /**
     * View size
     */
    public int viewWidth;
    public int viewHeight;

    /**
     * Face drawers for each face
     */
    private AbstractFaceDrawer[] drawers;

    /**
     * Vertices for backgroundEnabled face
     */
    private Point3D[] backgroundVertices;

    public AbstractFaceDrawer[] getDrawers() {
        return drawers;
    }

    /**
     * Def constructor
     *
     * @param coordinates Current primitive position coordinates
     */
    public Primitive(Coordinates coordinates) {
        //Set current coordinates
        this.center = coordinates;
    }

    public Primitive() {
        this.center = new Coordinates(0, 0, 0);
    }

    /**
     * initialization of the primitive
     */
    public void initialize() {
        // Initialize geometry.
        initializeGeometry();
    }

    /**
     * Initialize model geometry
     */
    protected void initializeGeometry() {
        vertices = new ArrayList<Point3D>();
        //initialize vertices and faces
        initializeVertices();
        initializeFaces();

        //initialize drawers
        drawers = new AbstractFaceDrawer[faces.length + 2];


        EngineManager engineManager = EngineManager.getInstance();
        Engine engine = engineManager.getEngine();

        for (int i = 0; i < faces.length + 2; i++) {
            drawers[i] = engine.createFaceDrawer();
        }


        //initialize background vertices
        backgroundVertices = new Point3D[]{
                new Point3D(center.getX() + viewWidth / 2, center.getY() + viewHeight / 2, center.getZ(), 1),
                new Point3D(center.getX() - viewWidth / 2, center.getY() + viewHeight / 2, center.getZ(), 1),
                new Point3D(center.getX() + viewWidth / 2, center.getY() - viewHeight / 2, center.getZ(), 1),
                new Point3D(center.getX() - viewWidth / 2, center.getY() - viewHeight / 2, center.getZ(), 1)
        };
        //set background color as disabled color
        setBackgroundColor(Color.TRANSPARENT);
    }

    /**
     * Initialize vertices of primitive
     */
    public abstract void initializeVertices();

    /**
     * Initialize faces of primitive.
     * Based on primitive vertices
     */
    public abstract void initializeFaces();


    /**
     * On long click set primitive enabled
     */
    public void onLongClick() {
        setEnabled(true);
    }

    /**
     * Set touch event on changing primitive position, size, primitive rotating
     */
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            //set flag that model is on touch mode
//            isOnFocus = true;
//            //If touched two fingers calculate diagonal between them
//            if (event.getPointerCount() == 2) {
//                //calculate diagonal between fingers
//                previousDiagonal = Math.sqrt(Math.pow((event.getXz() - event.getXz(1)), 2) +
//                        Math.pow((event.getYz() - event.getYz(1)), 2));
//            }
//            //remember previous touch
//            lastTouchX = event.getXz();
//            lastTouchY = event.getYz();
//        } else {
//            if (event.getAction() == MotionEvent.ACTION_MOVE) {
//
//                //if primitive is not enabled
//                //scale when two fingers moving
//                if (!isEnabled) {
//                    //rotate primitive in other case
//                    float dx = (event.getXz() - lastTouchX) / 30.0f;
//                    float dy = (event.getYz() - lastTouchY) / 30.0f;
//                    //set rotation angles of primitive
//                    ax -= dy;
//                    ay += dx;
//                    //rotate on angle -dy for x, dx for y
//                    rotate(new Coordinates(-dy, dx, 0), center);
//                } else {
//                    if (event.getPointerCount() == 2) {
//                        //calculate diagonal between fingers
//                        currentDiagonal = Math.sqrt(Math.pow((event.getXz() - event.getXz(1)), 2) +
//                                Math.pow((event.getYz() - event.getYz(1)), 2));
//                        //if first time
//                        if (previousDiagonal == 0)
//                            previousDiagonal = currentDiagonal;
//                        //scale primitive in offset
//                        double offset = currentDiagonal / previousDiagonal;
//                        scale(new Coordinates(offset, offset, offset));
//                        //set to second time
//                        previousDiagonal = currentDiagonal;
//
//                    } else {
//                        // move primitive
//                        move(new Coordinates(center.getXz(), center.getYz(), center.getXy()),
//                                new Coordinates(event.getXz(), event.getYz(), center.getXy()));
//                    }
//                }
//            } else {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    //set primitive out of focus
//                    isOnFocus = false;
//                    //skip diagonals
//                    previousDiagonal = 0;
//                    currentDiagonal = 0;
//                }
//            }
//        }
//        return false;
//    }

    /**
     * rotate primitive
     *
     * @param offset      angles to rotate around x, y, z in Coordinates(x, y, z)
     * @param aroundPoint point, around which to rotate primitive
     */
    public void rotate(Coordinates offset, Coordinates aroundPoint) {
        for (int i = 0; i < vertices.size(); i++) {
            vertices.set(i, vertices.get(i).rotateX(offset.getX(), aroundPoint).
                    rotateY(offset.getY(), aroundPoint).
                    rotateZ(offset.getZ(), aroundPoint));
        }
    }

    /**
     * move primitive to steed center from oldCenter
     *
     * @param oldCenter old center of primitive
     */
    public void move(Coordinates oldCenter, Coordinates center) {

        this.center = center;

        Coordinates offset = new Coordinates(center.getX() - oldCenter.getX(),
                center.getY() - oldCenter.getY(),
                center.getZ() - oldCenter.getZ());

        for (int i = 0; i < vertices.size(); i++) {
            vertices.set(i, vertices.get(i).move(offset));
        }

        for (int i = 0; i < backgroundVertices.length; i++) {
            backgroundVertices[i] = backgroundVertices[i].move(offset);
        }
    }

    /**
     * Set new size of model image
     *
     * @param offset offset to scale (size will be multiplied on it)
     */
    public void scale(Coordinates offset) {

        this.viewWidth *= offset.getX();
        this.viewHeight *= offset.getY();

        for (int i = 0; i < vertices.size(); i++) {
            Point3D scaled = vertices.get(i).scale(offset, center);
            vertices.set(i, scaled);
        }

        for (int i = 0; i < backgroundVertices.length; i++) {
            backgroundVertices[i] = backgroundVertices[i].scale(offset, center);
        }
    }

    public Point3D[] getBackgroundVertices() {
        return backgroundVertices;
    }

    /**
     * set View enabled or disabled
     */
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        if (isEnabled)
            setBackgroundColor(Color.BACKGROUND);
        else
            setBackgroundColor(Color.TRANSPARENT);
    }

    /**
     * set primitive backgound color
     *
     * @param color Color of background
     */
    private void setBackgroundColor(Color color) {


        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.PaintStyle.FILL);

        //set color to drawers
        drawers[faces.length].setPaint(paint);
        drawers[faces.length + 1].setPaint(paint);
    }

    /**
     * set color of faces
     *
     * @param color      new color
     * @param startIndex index of first face
     * @param endIndex   index of to which face change colors (without face with this index)
     */
    public void setFacesColor(Color color, int startIndex, int endIndex) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.PaintStyle.FILL);

        for (int i = startIndex; i < endIndex; i++) {
            drawers[i].setPaint(paint);
        }
    }

    public boolean isOnFocus() {
        return isOnFocus;
    }

    public ArrayList<Point3D> getVertices() {
        return vertices;
    }

    public int[][] getFaces() {
        return faces;
    }
}
