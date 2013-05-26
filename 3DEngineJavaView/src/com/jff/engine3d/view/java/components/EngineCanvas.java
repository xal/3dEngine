package com.jff.engine3d.view.java.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class EngineCanvas extends Canvas {


    public EngineCanvas(Composite parent) {
        super(parent, SWT.NONE);

        onCreate();
    }

    private void onCreate() {

        Display display = Display.getCurrent();
        Color backgroundColor = display.getSystemColor(SWT.COLOR_WHITE);

        setBackground(backgroundColor);
    }

//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        //draw all primitives on canvas
//        int[] order = new int[myObjects.size()];
//
//        for (Primitive primitive : myObjects) {
//            primitive.onDraw(canvas);
//        }
//    }
//
//    /**
//     * initialize view listeners (onTouchListener and long-click listener)
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
//                    mDownX = event.getX();
//                    mDownY = event.getY();
//                    //handle onLongClick
//                    handler.postDelayed(mLongPressed, 1000);
//                }
//                if (event.getAction() == MotionEvent.ACTION_MOVE)
//                    if (Math.abs(mDownX - event.getX()) > Constants.FINGER_MOVING_DEAD_ZONE ||
//                            Math.abs(mDownY - event.getY()) > Constants.FINGER_MOVING_DEAD_ZONE)
//                        //remove onLongClick
//                        handler.removeCallbacks(mLongPressed);
//                if (event.getAction() == MotionEvent.ACTION_UP)
//                    //remove onLongClick
//                    handler.removeCallbacks(mLongPressed);
//
//                for (Primitive object : myObjects) {
//
//                    if ((event.getX() >= object.center.getX() - object.viewWidth / 2) &&
//                            (event.getX() <= object.center.getX() + object.viewWidth / 2) &&
//                            (event.getY() >= object.center.getY() - object.viewHeight / 2) &&
//                            (event.getY() <= object.center.getY() + object.viewHeight / 2) &&
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
//                for (Primitive object : myObjects) {
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
//
//    /**
//     * add object to canvas
//     * @param object object for add
//     */
//    public void addObject(Primitive object) {
//        myObjects.add(object);
//        invalidate();
//    }
//
//    /**
//     * remove object from canvas
//     * @param object removing object
//     */
//    public void removeObject(Primitive object) {
//        myObjects.remove(object);
//        invalidate();
//    }
//
//    /**
//     * class for handling long-click event
//     */
//    private class EnablingRunnable implements Runnable {
//        /**
//         * selected object
//         */
//        private Primitive object;
//
//        public void setObject(Primitive object) {
//            this.object = object;
//        }
//
//        @Override
//        public void run() {
//            //long click on object
//            object.onLongClick();
//            //show fragment with object params
//            ((DrawingObjectActivity)context).showObjectSettingsFragment(object);
//            //redraw canvas
//            invalidate();
//        }
//    }
}
