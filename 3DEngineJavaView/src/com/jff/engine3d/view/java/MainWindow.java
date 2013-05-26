package com.jff.engine3d.view.java;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import sun.jkernel.Bundle;

public class MainWindow extends Composite {


    private EngineCanvas engineCanvas;
    protected SettingsFragment settingsFragment;

    public MainWindow(Composite parent) {
        super(parent, SWT.NONE);

        this.onCreate();
    }


    protected void onCreate() {

        Composite parent = this
                ;
        engineCanvas = new EngineCanvas(parent);
        settingsFragment = new SettingsFragment(parent);


//        settingsFragment.setOnPrimitiveChangedListener(new OnPrimitiveChangingListener() {
//            @Override
//            public void onPrimitiveChanged(Primitive object) {
//                engineCanvas.removeObject(object);
//            }
//        });
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_drawing_object, menu);
//        menu.add("Add object");
//
//        menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
//
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                openCreateObjectDialog();
//                return false;
//            }
//        });
//        return true;
//    }
//
//    protected void openCreateObjectDialog() {
//
//        final String[] myObjects = {Constants.PARALLELEPIPED_NAME,
//                Constants.CILINDER_NAME,
//                Constants.CONE_NAME,
//                Constants.HALF_FRUST_CONE_NAME,
//                Constants.ROLLER_NAME};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setItems(myObjects, new OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int position) {
//                if (myObjects[position] == Constants.PARALLELEPIPED_NAME) {
//                    Parallelepiped parallelepiped = new Parallelepiped(new Coordinates(100, 100, 0), 50, 100, 30);
//                    engineCanvas.addObject(parallelepiped);
//                }
//                if (myObjects[position] == Constants.CONE_NAME) {
//                    Cone cone = new Cone(new Coordinates(100, 100, 100), 50, 200);
//                    engineCanvas.addObject(cone);
//                }
//                if (myObjects[position] == Constants.CILINDER_NAME) {
//                    Cilinder cilinder = new Cilinder(new Coordinates(100, 100, 100), 100, 100, 200);
//                    engineCanvas.addObject(cilinder);
//                }
//                if (myObjects[position] == Constants.HALF_FRUST_CONE_NAME) {
//                    HalfFrustCone cone = new HalfFrustCone(new Coordinates(100, 100, 100), 50, 100, 200);
//                    engineCanvas.addObject(cone);
//                }
//                if (myObjects[position] == Constants.ROLLER_NAME) {
//                    Roller roller = new Roller(new Coordinates(100, 100, 100), 100, 50, 30, 100, 50, 10, 20, 150, 30, 30, 4);
//                    engineCanvas.addObject(roller);
//                }
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    @Override
//    public void onPrimitiveChanged(Primitive object) {
//        settingsFragment.setObject(object);
//        settingsFragment.updateCoordinates();
//    }
}
