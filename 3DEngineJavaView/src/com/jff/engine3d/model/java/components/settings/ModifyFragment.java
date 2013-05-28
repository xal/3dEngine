package com.jff.engine3d.model.java.components.settings;

import com.jff.engine3d.model.Controller;
import com.jff.engine3d.model.EngineListener;
import com.jff.engine3d.model.SceneObject;
import com.jff.engine3d.model.utils.draw.Point3D;
import com.jff.engine3d.model.utils.draw.RotationCoordinates;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;


public class ModifyFragment extends Composite {

    private Combo comboSelectedObjects;

    public ModifyFragment(Composite parent) {
        super(parent, SWT.NONE);

        RowLayout layout = new RowLayout(SWT.VERTICAL);

        layout.fill = true;

        this.setLayout(layout);


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

        Controller controller = Utils.getController();
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
        Controller controller = Utils.getController();
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
        Controller controller = Utils.getController();
        List<SceneObject> sceneObjects = controller.getSelectedObjects();
        SceneObject sceneObject = getCurrentSelectedObject();

        int index = sceneObjects.indexOf(sceneObject);

        comboSelectedObjects.select(index);

    }

    private SceneObject getCurrentSelectedObject() {
        Controller controller = Utils.getController();
        return controller.getCurrentSelectedObject();
    }


    private void createMoveSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Point3D");

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

                    SceneObject object = getCurrentSelectedObject();

                    Controller controller = Utils.getController();
                    controller.setCoordinatesForObject(point3D, object);

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

        group.setText("Rotate");

        GridLayout gridLayout = new GridLayout(2, true);

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

                    int xz = Utils.retrieveInteger(editXZ);
                    int yz = Utils.retrieveInteger(editYZ);
                    int xy = Utils.retrieveInteger(editXY);

                    Utils.checkDegrees(xz);
                    Utils.checkDegrees(yz);
                    Utils.checkDegrees(xy);


                    SceneObject object = getCurrentSelectedObject();

                    RotationCoordinates rotationCoordinates = new RotationCoordinates(xz, yz, xy);
                    Controller controller = Utils.getController();
                    controller.setRotationForObject(rotationCoordinates, object);

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

    private void createScaleSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Scale");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        final Label textScale = new Label(group, SWT.NONE);
        textScale.setText("Scale");
        final Text editScale = new Text(group, SWT.NONE);
        editScale.addListener(SWT.Verify, Utils.createVerifyFloatListener());


        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


                try {

                    float scale = Utils.retrieveScale(editScale);


                    SceneObject object = getCurrentSelectedObject();

                    Controller controller = Utils.getController();

                    controller.setScaleForObject(scale, object);

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

    private void createDeleteSettings() {

        Composite parent = this;


        Button buttonOk = new Button(parent, SWT.NONE);
        buttonOk.setText("Delete");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


                try {


                    SceneObject object = getCurrentSelectedObject();

                    Controller controller = Utils.getController();
                    controller.deleteObject(object);

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
