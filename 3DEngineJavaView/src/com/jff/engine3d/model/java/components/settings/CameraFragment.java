package com.jff.engine3d.model.java.components.settings;

import com.jff.engine3d.model.CameraRotateType;
import com.jff.engine3d.model.Controller;
import com.jff.engine3d.model.EngineManager;
import com.jff.engine3d.model.ProjectionType;
import com.jff.engine3d.model.utils.draw.Point3D;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class CameraFragment extends Composite {
    private final Controller controller;

    public CameraFragment(Composite parent) {
        super(parent, SWT.NONE);


        RowLayout layout = new RowLayout(SWT.VERTICAL);
        layout.fill = true;
        this.setLayout(layout);

        EngineManager engineManager = EngineManager.getInstance();

        controller = engineManager.getController();


        createProjectionSettings();

        createFromSettings();
        createToSettings();
        createRotateSettings();

    }

    private void createProjectionSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Projection");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        Button perspectiveButton = new Button(group, SWT.RADIO);
        perspectiveButton.setText("Perspective");

        Button parallelButton = new Button(group, SWT.RADIO);
        parallelButton.setText("Parallel");

        perspectiveButton.setSelection(true);

        perspectiveButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                controller.setProjectionType(ProjectionType.PERSPECTIVE);

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        parallelButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                controller.setProjectionType(ProjectionType.PARALLER);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


    }


    private void createFromSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Camera From");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        Label textX = new Label(group, SWT.NONE);
        textX.setText("X");
        final Text editX = new Text(group, SWT.NONE);
        editX.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Label textY = new Label(group, SWT.NONE);
        textY.setText("Y");
        final Text editY = new Text(group, SWT.NONE);
        editY.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Label textZ = new Label(group, SWT.NONE);
        textZ.setText("Z");
        final Text editZ = new Text(group, SWT.NONE);
        editZ.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                try {
                    int x = Utils.retrieveInteger(editX);
                    int y = Utils.retrieveInteger(editY);
                    int z = Utils.retrieveInteger(editZ);

                    Point3D point3D = new Point3D(x, y, z);


                    controller.setCameraFromCoordinates(point3D);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    Utils.showMessage(message);
                }


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }

    private void createToSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Camera To");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        Label textX = new Label(group, SWT.NONE);
        textX.setText("X");
        final Text editX = new Text(group, SWT.NONE);
        editX.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Label textY = new Label(group, SWT.NONE);
        textY.setText("Y");
        final Text editY = new Text(group, SWT.NONE);
        editY.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Label textZ = new Label(group, SWT.NONE);
        textZ.setText("Z");
        final Text editZ = new Text(group, SWT.NONE);
        editZ.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                try {


                    int x = Utils.retrieveInteger(editX);
                    int y = Utils.retrieveInteger(editY);
                    int z = Utils.retrieveInteger(editZ);

                    Point3D point3D = new Point3D(x, y, z);


                    controller.setCameraToCoordinates(point3D);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    Utils.showMessage(message);
                }


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }

    private void createRotateSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Rotate camera");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);


        final Button axisButton = new Button(group, SWT.RADIO);
        axisButton.setText("Axis");

        Button objectButton = new Button(group, SWT.RADIO);
        objectButton.setText("Object");

        axisButton.setSelection(true);


        parent = group;
        group = new Group(parent, SWT.NONE);

        gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        final Label textXZ = new Label(group, SWT.NONE);
        textXZ.setText("XZ");
        final Text editXZ = new Text(group, SWT.NONE);
        editXZ.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        final Label textYZ = new Label(group, SWT.NONE);
        textYZ.setText("YZ");
        final Text editYZ = new Text(group, SWT.NONE);
        editYZ.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        final Label textXY = new Label(group, SWT.NONE);
        textXY.setText("XY");
        final Text editXY = new Text(group, SWT.NONE);
        editXY.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                try {

                    CameraRotateType rotateType;
                    if (axisButton.getSelection()) {
                        rotateType = CameraRotateType.AXYS;
                    } else {
                        rotateType = CameraRotateType.OBJECT;
                    }

                    int xz = Utils.retrieveInteger(editXZ);
                    int yz = Utils.retrieveInteger(editYZ);
                    int xy = Utils.retrieveInteger(editXY);

                    Utils.checkDegrees(xz);
                    Utils.checkDegrees(yz);
                    Utils.checkDegrees(xy);


                    RotationCoordinates rotationCoordinates = new RotationCoordinates(xz, yz, xy);

                    controller.setCameraRotation(rotateType, rotationCoordinates);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    Utils.showMessage(message);
                }


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }


}
