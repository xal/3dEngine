package com.jff.engine3d.view.java.components.settings;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.entities.RotationCoordinates;
import com.jff.engine3d.model.logic.Controller;
import com.jff.engine3d.model.logic.exceptions.CollisionDetectedException;
import com.jff.engine3d.model.logic.interfaces.EngineListener;
import com.jff.engine3d.model.primitives.AbstractObject;
import com.jff.engine3d.model.scene.Scene;
import com.jff.engine3d.model.scene.SceneObject;
import com.jff.engine3d.view.java.components.utils.UIUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;


public class ModifyFragment extends Composite {

    private Combo comboSelectedObjects;
    private Slider sliderScale;
    private Text editScale;
    private Slider sliderRotateX;
    private Slider sliderRotateY;
    private Slider sliderRotateZ;
    private Text editRotateX;
    private Text editRotateY;
    private Text editRotateZ;
    private Text editX;
    private Text editY;
    private Text editZ;
    private Button isAllSelectedCheckBox;

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

        Group group = new Group(this, SWT.NONE);
        Composite parent = group;


        GridLayout gridLayout = new GridLayout(1, false);

        group.setLayout(gridLayout);


        group.setText("Select");


        comboSelectedObjects = new Combo(parent, SWT.NONE);
        comboSelectedObjects.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        comboSelectedObjects.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


                int index = comboSelectedObjects.getSelectionIndex();

                Controller controller = UIUtils.getController();
                List<SceneObject> sceneObjects = controller.getObjects();
                SceneObject sceneObject = sceneObjects.get(index);

                controller.setCurrentSelectedObject(sceneObject);


            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        updateSelectedObjectCombo();

        Controller controller = UIUtils.getController();
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

        isAllSelectedCheckBox = new Button(parent, SWT.CHECK);


        isAllSelectedCheckBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        isAllSelectedCheckBox.setText("IsAllSelected");
        isAllSelectedCheckBox.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                Controller controller = UIUtils.getController();
                controller.setIsAllSelected(isAllSelectedCheckBox.getSelection());
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    private void updateSelectedObjectCombo() {
        Controller controller = UIUtils.getController();
        List<SceneObject> sceneObjects = controller.getObjects();

        String[] items = new String[sceneObjects.size()];

        for (int i = 0; i < sceneObjects.size(); i++) {
            SceneObject sceneObject = sceneObjects.get(i);
            items[i] = sceneObject.getDescription();
        }

        comboSelectedObjects.setItems(items);


        boolean isAllSelected = controller.isAllSelected();


        comboSelectedObjects.setEnabled(!isAllSelected);


        updateCurrentSelectedObject();
    }

    private void updateCurrentSelectedObject() {
        Controller controller = UIUtils.getController();
        List<SceneObject> sceneObjects = controller.getObjects();
        SceneObject sceneObject = getCurrentSelectedObject();

        int index = sceneObjects.indexOf(sceneObject);

        comboSelectedObjects.select(index);

    }

    private SceneObject getCurrentSelectedObject() {
        Controller controller = UIUtils.getController();
        return controller.getCurrentSelectedObject();
    }


    private void createMoveSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Move");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        Label textX = new Label(group, SWT.NONE);
        textX.setText("X");
        editX = new Text(group, SWT.NONE);
        editX.addListener(SWT.Verify, UIUtils.createVerifyIntegerListener());

        Label textY = new Label(group, SWT.NONE);
        textY.setText("Y");
        editY = new Text(group, SWT.NONE);
        editY.addListener(SWT.Verify, UIUtils.createVerifyIntegerListener());

        Label textZ = new Label(group, SWT.NONE);
        textZ.setText("Z");
        editZ = new Text(group, SWT.NONE);
        editZ.addListener(SWT.Verify, UIUtils.createVerifyIntegerListener());


        editX.setText("0");
        editY.setText("0");
        editZ.setText("0");

        applyLayoutData(editX);
        applyLayoutData(editY);
        applyLayoutData(editZ);

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                try {


                    int x = UIUtils.retrieveInteger(editX);
                    int y = UIUtils.retrieveInteger(editY);
                    int z = UIUtils.retrieveInteger(editZ);

                    Point3D point3D = new Point3D(x, y, z);

                    SceneObject object = getCurrentSelectedObject();

                    Controller controller = UIUtils.getController();
                    controller.setMoveCoordinatesForObject(point3D, object);

                    List<SceneObject> selectedObjects = controller.getSelectedObjects();
                    for (SceneObject selectedObject : selectedObjects) {

                        controller.setMoveCoordinatesForObject(point3D, selectedObject);
                    }

                } catch (Exception e) {
                    String message = e.getMessage();
                    UIUtils.showMessage(message);
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

        GridLayout gridLayout = new GridLayout(3, true);

        group.setLayout(gridLayout);

        final Label textXZ = new Label(group, SWT.NONE);
        textXZ.setText("X");
        editRotateX = new Text(group, SWT.NONE);
        editRotateX.addListener(SWT.Verify, UIUtils.createVerifyPositiveIntegerListener());

        sliderRotateX = new Slider(group, SWT.NONE);
        initSliderRotate(sliderRotateX);

        final Label textYZ = new Label(group, SWT.NONE);
        textYZ.setText("Y");
        editRotateY = new Text(group, SWT.NONE);
        editRotateY.addListener(SWT.Verify, UIUtils.createVerifyPositiveIntegerListener());

        sliderRotateY = new Slider(group, SWT.NONE);
        initSliderRotate(sliderRotateY);

        final Label textXY = new Label(group, SWT.NONE);
        textXY.setText("Z");
        editRotateZ = new Text(group, SWT.NONE);
        editRotateZ.addListener(SWT.Verify, UIUtils.createVerifyPositiveIntegerListener());

        sliderRotateZ = new Slider(group, SWT.NONE);
        initSliderRotate(sliderRotateZ);


        editRotateZ.setText("0");
        editRotateY.setText("0");
        editRotateX.setText("0");

        applyLayoutData(editRotateZ);
        applyLayoutData(editRotateY);
        applyLayoutData(editRotateX);


        SelectionListener sliderListener = new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                int x = sliderRotateX.getSelection();
                int y = sliderRotateY.getSelection();
                int z = sliderRotateZ.getSelection();


                applyRotate(x, y, z);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        sliderRotateX.addSelectionListener(sliderListener);
        sliderRotateY.addSelectionListener(sliderListener);
        sliderRotateZ.addSelectionListener(sliderListener);

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                try {

                    int x = UIUtils.retrieveInteger(editRotateX);
                    int y = UIUtils.retrieveInteger(editRotateY);
                    int z = UIUtils.retrieveInteger(editRotateZ);

                    UIUtils.checkDegrees(x);
                    UIUtils.checkDegrees(y);
                    UIUtils.checkDegrees(z);


                    applyRotate(x, y, z);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    UIUtils.showMessage(message);
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }

    private void applyRotate(int x, int y, int z) {
        SceneObject object = getCurrentSelectedObject();

        RotationCoordinates rotationCoordinates = new RotationCoordinates(x, y, z);
        Controller controller = UIUtils.getController();
        List<SceneObject> selectedObjects = controller.getSelectedObjects();

        for (SceneObject selectedObject : selectedObjects) {

            controller.setRotationForObject(rotationCoordinates, selectedObject);
        }


        sliderRotateX.setSelection(x);
        sliderRotateY.setSelection(y);
        sliderRotateZ.setSelection(z);

        editRotateX.setText("" + x);
        editRotateY.setText("" + y);
        editRotateZ.setText("" + z);


    }

    private void initSliderRotate(Slider sliderRotateX) {
        sliderRotateX.setMinimum(0);
        sliderRotateX.setMaximum(369);

        sliderRotateX.setSelection(0);
    }

    private void createScaleSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Scale");

        GridLayout gridLayout = new GridLayout(3, true);

        group.setLayout(gridLayout);

        final Label textScale = new Label(group, SWT.NONE);
        textScale.setText("Scale");
        editScale = new Text(group, SWT.NONE);
        editScale.addListener(SWT.Verify, UIUtils.createVerifyFloatListener());

        applyLayoutData(editScale);


        sliderScale = new Slider(group, SWT.NONE);

        sliderScale.setMinimum(1);
        sliderScale.setMaximum(200);

        sliderScale.setSelection(100);

        sliderScale.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                float scale = sliderScale.getSelection();

                scale = scale / 100;

                changeScale(scale);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        editScale.setText("1");

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


                try {

                    float scale = UIUtils.retrieveScale(editScale);


                    changeScale(scale);

                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    UIUtils.showMessage(message);
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }

    private void changeScale(float scale) {
        SceneObject object = getCurrentSelectedObject();

        Controller controller = UIUtils.getController();


        List<SceneObject> selectedObjects = controller.getSelectedObjects();
        for (SceneObject selectedObject : selectedObjects) {

            try {
                controller.setScaleForObject(scale, selectedObject);
            } catch (CollisionDetectedException e) {
                UIUtils.showMessage(e.getMessage());
            }

        }


        sliderScale.setSelection((int) (scale * 100));
        editScale.setText("" + scale);
    }

    private void applyLayoutData(Text editScale) {
        editScale.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    private void createDeleteSettings() {

        Composite parent = this;


        Button buttonOk = new Button(parent, SWT.NONE);
        buttonOk.setText("Delete");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {


                try {


                    Controller controller = UIUtils.getController();
                    List<SceneObject> selectedObjects = controller.getSelectedObjects();

                    controller.deleteObjects(selectedObjects);


                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    UIUtils.showMessage(message);
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void sceneChanged(Scene scene) {
        fillSettings(scene);
    }

    private void fillSettings(Scene scene) {

        updateSelectedObjectCombo();
        updateIsAllSelectedCheckbox();
        SceneObject currentSelectedObject = scene.getCurrentSelectedObject();

        if (currentSelectedObject != null) {
            AbstractObject object = currentSelectedObject.getGeometryObject();

            Point3D center = object.getCenter();

            editX.setText("" + center.x);
            editY.setText("" + center.y);
            editZ.setText("" + center.z);

            RotationCoordinates rotationCoordinates = object.getRotationCoordinates();


            int x = (int) rotationCoordinates.getX();
            int y = (int) rotationCoordinates.getY();
            int z = (int) rotationCoordinates.getZ();

            sliderRotateX.setSelection(x);
            sliderRotateY.setSelection(y);
            sliderRotateZ.setSelection(z);

            editRotateX.setText("" + x);
            editRotateY.setText("" + y);
            editRotateZ.setText("" + z);

            double scale = object.getScale();
            editScale.setText("" + scale);
            sliderScale.setSelection((int) (scale * 100));


        }


    }

    private void updateIsAllSelectedCheckbox() {

        Controller controller = UIUtils.getController();

        boolean isAllSelected = controller.isAllSelected();

        isAllSelectedCheckBox.setSelection(isAllSelected);

    }


}
