package com.jff.engine3d.model.java.components.settings;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class CameraFragment extends Composite {
    public CameraFragment(Composite parent) {
        super(parent, SWT.NONE);

        Layout layout = new RowLayout(SWT.VERTICAL);
        this.setLayout(layout);


        createProjectionSettings();
        createViewSettings();
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


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        parallelButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


    }

    private void createViewSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("View");

        GridLayout gridLayout = new GridLayout(3, true);

        group.setLayout(gridLayout);

        Button polygonsButton = new Button(group, SWT.RADIO);
        polygonsButton.setText("Polygons");

        Button edgesButton = new Button(group, SWT.RADIO);
        edgesButton.setText("Edges");

        Button verticesButton = new Button(group, SWT.RADIO);
        verticesButton.setText("Vertices");

        polygonsButton.setSelection(true);

        polygonsButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        edgesButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        verticesButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


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
        editX.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Label textY = new Label(group, SWT.NONE);
        textY.setText("Y");
        final Text editY = new Text(group, SWT.NONE);
        editY.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Label textZ = new Label(group, SWT.NONE);
        textZ.setText("Z");
        final Text editZ = new Text(group, SWT.NONE);
        editZ.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                int x = Integer.parseInt(editX.getText());
                int y = Integer.parseInt(editY.getText());
                int z = Integer.parseInt(editZ.getText());

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

        group.setText("Camera TO");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        Label textX = new Label(group, SWT.NONE);
        textX.setText("X");
        final Text editX = new Text(group, SWT.NONE);
        editX.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Label textY = new Label(group, SWT.NONE);
        textY.setText("Y");
        final Text editY = new Text(group, SWT.NONE);
        editY.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Label textZ = new Label(group, SWT.NONE);
        textZ.setText("Z");
        final Text editZ = new Text(group, SWT.NONE);
        editZ.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                int x = Integer.parseInt(editX.getText());
                int y = Integer.parseInt(editY.getText());
                int z = Integer.parseInt(editZ.getText());

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


        Button axisButton = new Button(group, SWT.RADIO);
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
        editXZ.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        final Label textYZ = new Label(group, SWT.NONE);
        textYZ.setText("YZ");
        final Text editYZ = new Text(group, SWT.NONE);
        editYZ.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        final Label textXY = new Label(group, SWT.NONE);
        textXY.setText("XY");
        final Text editXY = new Text(group, SWT.NONE);
        editXY.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                int xz = Integer.parseInt(editXZ.getText());
                int yz = Integer.parseInt(editYZ.getText());
                int xy = Integer.parseInt(editXY.getText());


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }


}
