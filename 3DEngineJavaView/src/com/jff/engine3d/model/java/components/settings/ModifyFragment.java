package com.jff.engine3d.model.java.components.settings;

import com.jff.engine3d.model.Controller;
import com.jff.engine3d.model.EngineListener;
import com.jff.engine3d.model.EngineManager;
import com.jff.engine3d.model.SceneObject;
import com.jff.engine3d.model.utils.draw.Coordinates;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;


public class ModifyFragment extends Composite {
    private final Controller controller;
    private Combo comboSelectedObjects;

    public ModifyFragment(Composite parent) {
        super(parent, SWT.NONE);

        RowLayout layout = new RowLayout(SWT.VERTICAL);

        layout.fill = true;

        this.setLayout(layout);

        EngineManager engineManager = EngineManager.getInstance();
        controller = engineManager.getController();


        createSelectedComboSettings();
        createMoveSettings();
        createRotateSettings();
        createScaleSettings();
        createDeleteSettings();
    }

    private void createSelectedComboSettings() {

        Composite parent = this;

        comboSelectedObjects = new Combo(parent, SWT.NONE);

        comboSelectedObjects.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        updateSelectedObjectCombo();

        controller.addEngineListener(new EngineListener() {
            @Override
            public void onSelectedObjectsChanged(List<SceneObject> objects) {
                updateSelectedObjectCombo();
            }

            @Override
            public void onCurrentSelectedObjectChanged(SceneObject object) {
                updateCurrentSelectedObject();
            }
        });
    }

    private void updateSelectedObjectCombo() {
        List<SceneObject> sceneObjects = controller.getSelectedObjects();

        String[] items = new String[sceneObjects.size()];

        for (int i = 0; i < sceneObjects.size(); i++) {
            SceneObject sceneObject = sceneObjects.get(i);
            items[i] = sceneObject.getDescription();
        }

        comboSelectedObjects.setItems(items);


        updateCurrentSelectedObject();
    }

    private void updateCurrentSelectedObject() {
        List<SceneObject> sceneObjects = controller.getSelectedObjects();
        SceneObject sceneObject = getCurrentSelectedObject();

        int index = sceneObjects.indexOf(sceneObject);

        comboSelectedObjects.select(index);

    }

    private SceneObject getCurrentSelectedObject() {
        return controller.getCurrentSelectedObject();
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
                try {


                    int x = SWTUtils.retrieveInteger(editX);
                    int y = SWTUtils.retrieveInteger(editY);
                    int z = SWTUtils.retrieveInteger(editZ);

                    Coordinates coordinates = new Coordinates(x, y, z);

                    SceneObject object = getCurrentSelectedObject();

                    controller.setCoordinatesForObject(coordinates, object);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    SWTUtils.showMessage(message);
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

                try {

                    int xz = SWTUtils.retrieveInteger(editXZ);
                    int yz = SWTUtils.retrieveInteger(editYZ);
                    int xy = SWTUtils.retrieveInteger(editXY);

                    SWTUtils.checkDegrees(xz);
                    SWTUtils.checkDegrees(yz);
                    SWTUtils.checkDegrees(xy);


                    SceneObject object = getCurrentSelectedObject();

                    RotationCoordinates rotationCoordinates = new RotationCoordinates(xz, yz, xy);
                    controller.setRotationForObject(rotationCoordinates, object);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    SWTUtils.showMessage(message);
                }

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


                try {

                    float scale = SWTUtils.retrieveScale(editScale);


                    SceneObject object = getCurrentSelectedObject();


                    controller.setScaleForObject(scale, object);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    SWTUtils.showMessage(message);
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }

    private void createDeleteSettings() {

        Composite parent = this;


        Button buttonOk = new Button(parent, SWT.NONE);
        buttonOk.setText("Delete");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


                try {


                    SceneObject object = getCurrentSelectedObject();

                    controller.deleteObject(object);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    SWTUtils.showMessage(message);
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
