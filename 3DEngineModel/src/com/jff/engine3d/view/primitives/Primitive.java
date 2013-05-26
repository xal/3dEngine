package com.jff.engine3d.view.primitives;


import com.jff.engine3d.view.utils.draw.*;
import com.jff.engine3d.view.utils.draw.Color;
import com.jff.engine3d.view.utils.draw.Paint;

import java.awt.*;
import java.util.ArrayList;

public abstract class Primitive {

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
    private IFaceDrawer[] drawers;

    /**
     * Vertices for backgroundEnabled face
     */
    private Point3D[] backgroundVertices;

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
     * Initialize view geometry
     */
    protected void initializeGeometry() {
        vertices = new ArrayList<Point3D>();
        //initialize vertices and faces
        initializeVertices();
        initializeFaces();

        //initialize drawers
        drawers = new IFaceDrawer[faces.length + 2];
        for (int i = 0; i < faces.length + 2; i++)
            drawers[i] = new IFaceDrawer() {
                @Override
                public void setPath(Path path) {

                }

                @Override
                public void setPaint(Paint paint) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
            };

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
     * Draw triangulated primitive
     */

    public void onDraw(Canvas canvas) {
//
//        int[] order = new int[faces.length];
//        paintersAlgorithm(vertices.toArray(new Point3D[vertices.size()]), order, faces);
//
//        //draw background
//        drawers[faces.length].setPath(backgroundVertices, new int[]{0, 2, 3});
//        drawers[faces.length + 1].setPath(backgroundVertices, new int[]{0, 1, 3});
//        canvas.drawPath(drawers[faces.length].getPath(), drawers[faces.length].getPaint());
//        canvas.drawPath(drawers[faces.length + 1].getPath(), drawers[faces.length + 1].getPaint());
//
//        //draw faces
//        for (int i = 0; i < faces.length; i++) {
//            int index = order[i];
//            drawers[index].setPath(vertices.toArray(new Point3D[vertices.size()]), faces[index]);
//            canvas.drawPath(drawers[index].getPath(), drawers[index].getPaint());
//        }
    }

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
//            //set flag that view is on touch mode
//            isOnFocus = true;
//            //If touched two fingers calculate diagonal between them
//            if (event.getPointerCount() == 2) {
//                //calculate diagonal between fingers
//                previousDiagonal = Math.sqrt(Math.pow((event.getX() - event.getX(1)), 2) +
//                        Math.pow((event.getY() - event.getY(1)), 2));
//            }
//            //remember previous touch
//            lastTouchX = event.getX();
//            lastTouchY = event.getY();
//        } else {
//            if (event.getAction() == MotionEvent.ACTION_MOVE) {
//
//                //if primitive is not enabled
//                //scale when two fingers moving
//                if (!isEnabled) {
//                    //rotate primitive in other case
//                    float dx = (event.getX() - lastTouchX) / 30.0f;
//                    float dy = (event.getY() - lastTouchY) / 30.0f;
//                    //set rotation angles of primitive
//                    ax -= dy;
//                    ay += dx;
//                    //rotate on angle -dy for x, dx for y
//                    rotate(new Coordinates(-dy, dx, 0), center);
//                } else {
//                    if (event.getPointerCount() == 2) {
//                        //calculate diagonal between fingers
//                        currentDiagonal = Math.sqrt(Math.pow((event.getX() - event.getX(1)), 2) +
//                                Math.pow((event.getY() - event.getY(1)), 2));
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
//                        move(new Coordinates(center.getX(), center.getY(), center.getZ()),
//                                new Coordinates(event.getX(), event.getY(), center.getZ()));
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
     * Set new size of view image
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

    /**
     * Painters algorithm for sorting polygons order
     *
     * @param t     vertices of view
     * @param order order of viewing polygons
     */
    protected static void paintersAlgorithm(Point3D[] t, int[] order, int[][] faces) {

        double avgZ[] = new double[faces.length];

        // Compute the average Z value of each face.
        for (int i = 0; i < faces.length; i++) {
            for (int j = 0; j < faces[i].length; j++) {
                avgZ[i] += t[faces[i][j]].z;
            }
            order[i] = i;
        }

        // Next we sort the faces in descending order based on the Z value.
        // The objective is to draw distant faces first.
        // The sorting algorithm used is the SELECTION SORT.
        for (int i = 0; i < faces.length; i++) {
            int iMax = i;

            for (int j = i + 1; j < faces.length; j++) {

                if (avgZ[iMax] < avgZ[j]) {
                    iMax = j;
                }
            }
            if (iMax != i) {

                double dTmp = avgZ[i];
                avgZ[i] = avgZ[iMax];
                avgZ[iMax] = dTmp;

                int iTmp = order[i];
                order[i] = order[iMax];
                order[iMax] = iTmp;
            }
        }
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
