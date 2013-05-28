package com.jff.engine3d.model;

import com.jff.engine3d.model.utils.draw.Coordinates;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scene implements Serializable {

    private Camera camera;

    private List<SceneObject> objects = new ArrayList<SceneObject>();
    private transient List<EngineListener> engineListeners = new ArrayList<EngineListener>();

    public void removeObject(SceneObject sceneObject) {
        objects.remove(sceneObject);
    }

    public boolean addObject(SceneObject sceneObject) {
        return objects.add(sceneObject);
    }


    public boolean addEngineListener(EngineListener engineListener) {
        return engineListeners.add(engineListener);
    }

    public boolean removeEngineListener(EngineListener o) {
        return engineListeners.remove(o);
    }

    public void clean() {
        objects.clear();
        engineListeners.clear();
    }

    public void init(Scene oldScene) {
        this.engineListeners.addAll(oldScene.engineListeners);
    }

    public void setViewType(ViewType viewType) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void startPanorama() {


    }


    public void setCameraFromCoordinates(Coordinates coordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setCameraToCoordinates(Coordinates coordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setCameraRotation(CameraRotateType rotateType, RotationCoordinates rotationCoordinates) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public List<SceneObject> getSelectedObjects() {
        return Collections.emptyList();  //To change body of created methods use File | Settings | File Templates.
    }

    public SceneObject getCurrentSelectedObject() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setCoordinatesForObject(Coordinates coordinates, SceneObject object) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setRotationForObject(RotationCoordinates rotationCoordinates, SceneObject object) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setScaleForObject(float scale, SceneObject object) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setProjectionType(ProjectionType projectionType) {
        //To change body of created methods use File | Settings | File Templates.
    }
//
//
//    /**
//     * Draw triangulated abstractObject
//     */
//
//    public void onDraw(AbstractObject abstractObject) {
//
//        Canvas canvas = this;
//
//        int[][] faces = abstractObject.getFaces();
//        ArrayList<Point3D> vertices = abstractObject.getVertices();
//
//        int[] order = new int[faces.length];
//        paintersAlgorithm(vertices.toArray(new Point3D[vertices.size()]), order, faces);
//
//        //draw background
//        AbstractFaceDrawer[] drawers = abstractObject.getDrawers();
//
//        Point3D[] backgroundVertices = abstractObject.getBackgroundVertices();
//        drawers[faces.length].setPath(backgroundVertices, new int[]{0, 2, 3});
//        drawers[faces.length + 1].setPath(backgroundVertices, new int[]{0, 1, 3});
//
//        GC gc = new GC(canvas);
//
//        int indexFace;
//        AbstractFaceDrawer faceDrawer;
//
//        indexFace = faces.length;
//        faceDrawer = drawers[indexFace];
//        Path swtPath1 = calculatePath(faceDrawer);
//
//        indexFace = faces.length + 1;
//        faceDrawer = drawers[indexFace];
//        Path swtPath2 = calculatePath(faceDrawer);
//
//        gc.drawPath(swtPath1);
//        gc.drawPath(swtPath2);
//
//
//        //draw faces
//        for (int i = 0; i < faces.length; i++) {
//            int index = order[i];
//            Point3D[] point3Ds = new Point3D[vertices.size()];
//            Point3D[] backgroundVertices1 = vertices.toArray(point3Ds);
//            drawers[index].setPath(backgroundVertices1, faces[index]);
//
//
//            faceDrawer = drawers[index];
//            Path swtPath = calculatePath(faceDrawer);
//
//            gc.drawPath(swtPath);
//        }
//    }
//
//    private Path calculatePath(AbstractFaceDrawer faceDrawer) {
//
//        FaceDrawerSWT swtFaceDrawer = (FaceDrawerSWT) faceDrawer;
//
//
//        return swtFaceDrawer.getSWTPath();
//    }
//
//    /**
//     * Painters algorithm for sorting polygons order
//     *
//     * @param t     vertices of model
//     * @param order order of viewing polygons
//     */
//    protected static void paintersAlgorithm(Point3D[] t, int[] order, int[][] faces) {
//
//        double avgZ[] = new double[faces.length];
//
//        // Compute the average Z value of each face.
//        for (int i = 0; i < faces.length; i++) {
//            for (int j = 0; j < faces[i].length; j++) {
//                avgZ[i] += t[faces[i][j]].z;
//            }
//            order[i] = i;
//        }
//
//        // Next we sort the faces in descending order based on the Z value.
//        // The objective is to draw distant faces first.
//        // The sorting algorithm used is the SELECTION SORT.
//        for (int i = 0; i < faces.length; i++) {
//            int iMax = i;
//
//            for (int j = i + 1; j < faces.length; j++) {
//
//                if (avgZ[iMax] < avgZ[j]) {
//                    iMax = j;
//                }
//            }
//            if (iMax != i) {
//
//                double dTmp = avgZ[i];
//                avgZ[i] = avgZ[iMax];
//                avgZ[iMax] = dTmp;
//
//                int iTmp = order[i];
//                order[i] = order[iMax];
//                order[iMax] = iTmp;
//            }
//        }
//    }


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


}
