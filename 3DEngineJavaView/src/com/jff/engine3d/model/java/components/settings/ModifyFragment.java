package com.jff.engine3d.model.java.components.settings;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class ModifyFragment extends Composite {
    public ModifyFragment(Composite parent) {
        super(parent, SWT.NONE);

        Layout layout = new FillLayout(SWT.VERTICAL);
        this.setLayout(layout);


        createMoveSettings();
        createRotateSettings();
        createScaleSettings();
    }

    private void createMoveSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Coordinates");

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

        group.setText("Rotate");

        GridLayout gridLayout = new GridLayout(2, true);

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

    private void createScaleSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Scale");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        final Label textScale = new Label(group, SWT.NONE);
        textScale.setText("Scale");
        final Text editScale = new Text(group, SWT.NONE);
        editScale.addListener(SWT.Verify, SWTUtils.createVerifyFloatListener());


        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                float scale = Float.parseFloat(editScale.getText());


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }
}
