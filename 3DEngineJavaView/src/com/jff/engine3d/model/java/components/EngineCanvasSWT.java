package com.jff.engine3d.model.java.components;

import com.jff.engine3d.model.java.FaceDrawerSWT;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.utils.draw.AbstractFaceDrawer;
import com.jff.engine3d.model.utils.draw.IEngineCanvas;
import com.jff.engine3d.model.utils.draw.Point3D;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import java.util.ArrayList;
import java.util.List;

public class EngineCanvasSWT extends Canvas implements IEngineCanvas {


    private List<AbstractObject> myObjects = new ArrayList<AbstractObject>();

    public EngineCanvasSWT(Composite parent) {
        super(parent, SWT.NONE);

        onCreate();
    }

    private void onCreate() {

        Display display = Display.getCurrent();
        Color backgroundColor = display.getSystemColor(SWT.COLOR_WHITE);

        setBackground(backgroundColor);

        this.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent paintEvent) {
                for (AbstractObject abstractObject : myObjects) {

                    onDraw(abstractObject);
                }
            }
        });
    }

    /**
     * Draw triangulated abstractObject
     */

    public void onDraw(AbstractObject abstractObject) {

        Canvas canvas = this;

        int[][] faces = abstractObject.getFaces();
        ArrayList<Point3D> vertices = abstractObject.getVertices();

        int[] order = new int[faces.length];
        paintersAlgorithm(vertices.toArray(new Point3D[vertices.size()]), order, faces);

        //draw background
        AbstractFaceDrawer[] drawers = abstractObject.getDrawers();

        Point3D[] backgroundVertices = abstractObject.getBackgroundVertices();
        drawers[faces.length].setPath(backgroundVertices, new int[]{0, 2, 3});
        drawers[faces.length + 1].setPath(backgroundVertices, new int[]{0, 1, 3});

        GC gc = new GC(canvas);

        int indexFace;
        AbstractFaceDrawer faceDrawer;

        indexFace = faces.length;
        faceDrawer = drawers[indexFace];
        Path swtPath1 = calculatePath(faceDrawer);

        indexFace = faces.length + 1;
        faceDrawer = drawers[indexFace];
        Path swtPath2 = calculatePath(faceDrawer);

        gc.drawPath(swtPath1);
        gc.drawPath(swtPath2);


        //draw faces
        for (int i = 0; i < faces.length; i++) {
            int index = order[i];
            Point3D[] point3Ds = new Point3D[vertices.size()];
            Point3D[] backgroundVertices1 = vertices.toArray(point3Ds);
            drawers[index].setPath(backgroundVertices1, faces[index]);


            faceDrawer = drawers[index];
            Path swtPath = calculatePath(faceDrawer);

            gc.drawPath(swtPath);
        }
    }

    private Path calculatePath(AbstractFaceDrawer faceDrawer) {

        FaceDrawerSWT swtFaceDrawer = (FaceDrawerSWT) faceDrawer;


        return swtFaceDrawer.getSWTPath();
    }

    /**
     * Painters algorithm for sorting polygons order
     *
     * @param t     vertices of model
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


//    /**
//     * initialize model listeners (onTouchListener and long-click listener)
//     */
//    public void initializeListeners() {
//        //handle and runnable for long click
//        final Handler handler = new Handler();
//        final Runnable mLongPressed = new EnablingRunnable();
//
//        this.setFocusableInTouchMode(true);
//        //set up on touch listener
//        this.setOnTouchListener(new OnTouchListener() {
//            float mDownX = 0;
//            float mDownY = 0;
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    mDownX = event.getXz();
//                    mDownY = event.getYz();
//                    //handle onLongClick
//                    handler.postDelayed(mLongPressed, 1000);
//                }
//                if (event.getAction() == MotionEvent.ACTION_MOVE)
//                    if (Math.abs(mDownX - event.getXz()) > Constants.FINGER_MOVING_DEAD_ZONE ||
//                            Math.abs(mDownY - event.getYz()) > Constants.FINGER_MOVING_DEAD_ZONE)
//                        //remove onLongClick
//                        handler.removeCallbacks(mLongPressed);
//                if (event.getAction() == MotionEvent.ACTION_UP)
//                    //remove onLongClick
//                    handler.removeCallbacks(mLongPressed);
//
//                for (AbstractObject object : myObjects) {
//
//                    if ((event.getXz() >= object.center.getXz() - object.viewWidth / 2) &&
//                            (event.getXz() <= object.center.getXz() + object.viewWidth / 2) &&
//                            (event.getYz() >= object.center.getYz() - object.viewHeight / 2) &&
//                            (event.getYz() <= object.center.getYz() + object.viewHeight / 2) &&
//                            (event.getAction() == MotionEvent.ACTION_DOWN || object.isOnFocus())) {
//
//                        //set object which can be long clicked
//                        ((EnablingRunnable)mLongPressed).setObject(object);
//                        //call touch event to this object
//                        object.onTouchEvent(event);
//                        //callback to activity for changing fragment
//                        ((DrawingObjectActivity)context).onPrimitiveChanged(object);
//                        //redraw canvas
//                        invalidate();
//
//                        return true;
//                    }
//                }
//                //if touched out of any object - skip all enabling
//                for (AbstractObject object : myObjects) {
//                    object.setEnabled(false);
//                }
//                ((DrawingObjectActivity)context).hideObjectSettingsFragment();
//                //redraw canvas
//                invalidate();
//
//                return true;
//            }
//        });
//    }

    /**
     * add object to canvas
     *
     * @param object object for add
     */
    @Override
    public void addObject(AbstractObject object) {
        myObjects.add(object);
        invalidate();
    }

    private void invalidate() {

        this.redraw();
    }

    /**
     * remove object from canvas
     *
     * @param object removing object
     */
    @Override
    public void removeObject(AbstractObject object) {
        myObjects.remove(object);
        invalidate();
    }

}
