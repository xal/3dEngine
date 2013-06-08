package com.jff.engine3d.view.java.components.settings;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.logic.Controller;
import com.jff.engine3d.model.logic.EngineManager;
import com.jff.engine3d.model.object.params.AbstractObjectParams;
import com.jff.engine3d.model.object.params.TrafficLightParams;
import com.jff.engine3d.model.scene.Scene;
import com.jff.engine3d.view.java.components.utils.UIUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class TrafficLightCreateFragment extends Composite {
    private Text centerXText;
    private Text centerYText;
    private Text centerZText;

    private Text diameterPoleConeLower;
    private Text heightPoleCone;


    private Text heightPoleCylinderLower;
    private Text diameterPoleCylinderLower;

    private Text heightPoleCylinderUpper;
    private Text diameterPoleCylinderUpper;


    private Text heightBase;
    private Text widthBase;
    private Text lengthBase;


    private Text diameterLight;


    private Text lengthLight;


    private Text numberLights;


    private Text heightVisor;
    private Text widthVisor;
    private Text lengthVisor;


    public TrafficLightCreateFragment(Composite parent) {
        super(parent, SWT.NONE);


        RowLayout layout = new RowLayout(SWT.VERTICAL);
        layout.fill = true;
        this.setLayout(layout);

        createCoordinateSettings();
        createObjectParamsSettings();

        fillSettings();

        createButtonOk();
    }

    private void fillSettings() {

        centerXText.setText("100");
        centerYText.setText("100");
        centerZText.setText("100");

        this.heightPoleCone.setText("20");
        this.diameterPoleConeLower.setText("40");
        this.heightPoleCylinderLower.setText("20");
        this.diameterPoleCylinderLower.setText("20");
        this.heightPoleCylinderUpper.setText("10");
        this.diameterPoleCylinderUpper.setText("10");
        heightBase.setText("170");
        widthBase.setText("50");
        lengthBase.setText("100");
        this.diameterLight.setText("40");
        this.lengthLight.setText("10");
        this.numberLights.setText("3");
        this.heightVisor.setText("10");
        this.widthVisor.setText("40");
        this.lengthVisor.setText("10");


    }

    private void createObjectParamsSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("ObjectParams");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        new Label(group, SWT.NONE).setText("Height cone");
        heightPoleCone = new Text(group, SWT.NONE);
        addListeners(heightPoleCone);

        new Label(group, SWT.NONE).setText("Diameter cone");
        diameterPoleConeLower = new Text(group, SWT.NONE);
        addListeners(diameterPoleConeLower);

        new Label(group, SWT.NONE).setText("Diameter lower cylinder");
        diameterPoleCylinderLower = new Text(group, SWT.NONE);
        addListeners(diameterPoleCylinderLower);

        new Label(group, SWT.NONE).setText("Height lower cylinder");
        heightPoleCylinderLower = new Text(group, SWT.NONE);
        addListeners(heightPoleCylinderLower);

        new Label(group, SWT.NONE).setText("Height upper cylinder");
        heightPoleCylinderUpper = new Text(group, SWT.NONE);
        addListeners(heightPoleCylinderUpper);

        new Label(group, SWT.NONE).setText("Diameter lower cylinder");
        diameterPoleCylinderUpper = new Text(group, SWT.NONE);
        addListeners(diameterPoleCylinderUpper);

        new Label(group, SWT.NONE).setText("Height base");
        heightBase = new Text(group, SWT.NONE);
        addListeners(heightBase);

        new Label(group, SWT.NONE).setText("Width base");
        widthBase = new Text(group, SWT.NONE);
        addListeners(widthBase);


        new Label(group, SWT.NONE).setText("Length base");
        lengthBase = new Text(group, SWT.NONE);
        addListeners(lengthBase);

        new Label(group, SWT.NONE).setText("Diameter  lights");
        diameterLight = new Text(group, SWT.NONE);
        addListeners(diameterLight);

        new Label(group, SWT.NONE).setText("Length light");
        lengthLight = new Text(group, SWT.NONE);
        addListeners(lengthLight);

        new Label(group, SWT.NONE).setText("Height visor");
        heightVisor = new Text(group, SWT.NONE);
        addListeners(heightVisor);

        new Label(group, SWT.NONE).setText("Width visor");
        widthVisor = new Text(group, SWT.NONE);
        addListeners(widthVisor);

        new Label(group, SWT.NONE).setText("Length visor");
        lengthVisor = new Text(group, SWT.NONE);
        addListeners(lengthVisor);

        new Label(group, SWT.NONE).setText("Number lights");
        numberLights = new Text(group, SWT.NONE);
        addListeners(numberLights);


    }

    private void addListeners(Text text) {
        text.addListener(SWT.Verify, UIUtils.createVerifyPositiveIntegerListener());
    }

    private void createButtonOk() {
        Button buttonOk = new Button(this, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                try {
                    int x = UIUtils.retrieveInteger(centerXText);
                    int y = UIUtils.retrieveInteger(centerYText);
                    int z = UIUtils.retrieveInteger(centerZText);

                    Point3D point3D = new Point3D(x, y, z);


                    int heightPoleConeInt = UIUtils.retrieveInteger(heightPoleCone);
                    int diameterPoleConeLowerInt = UIUtils.retrieveInteger(diameterPoleConeLower);
                    int heightPoleCylinderLowerInt = UIUtils.retrieveInteger(heightPoleCylinderLower);
                    int diameterPoleCylinderLowerInt = UIUtils.retrieveInteger(diameterPoleCylinderLower);
                    int heightPoleCylinderUpperInt = UIUtils.retrieveInteger(heightPoleCylinderUpper);
                    int diameterPoleCylinderUpperInt = UIUtils.retrieveInteger(diameterPoleCylinderUpper);
                    int heightBaseInt = UIUtils.retrieveInteger(heightBase);
                    int widthBaseInt = UIUtils.retrieveInteger(widthBase);
                    int lengthBaseInt = UIUtils.retrieveInteger(lengthBase);
                    int diameterLightInt = UIUtils.retrieveInteger(diameterLight);
                    int lengthLightInt = UIUtils.retrieveInteger(lengthLight);
                    int numberLightsInt = UIUtils.retrieveInteger(numberLights);
                    int heightVisorInt = UIUtils.retrieveInteger(heightVisor);
                    int widthVisorInt = UIUtils.retrieveInteger(widthVisor);
                    int lengthVisorInt = UIUtils.retrieveInteger(lengthVisor);


                    AbstractObjectParams objectParams = new TrafficLightParams(heightPoleConeInt, diameterPoleConeLowerInt, heightPoleCylinderLowerInt, diameterPoleCylinderLowerInt, heightPoleCylinderUpperInt, diameterPoleCylinderUpperInt, heightBaseInt, widthBaseInt, lengthBaseInt, diameterLightInt, lengthLightInt, numberLightsInt, heightVisorInt, widthVisorInt, lengthVisorInt);


                    EngineManager engineManager = EngineManager.getInstance();
                    Controller controller = engineManager.getController();
                    controller.createObject(point3D, objectParams);

                } catch (Exception e) {
                    e.printStackTrace();
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


    private void createCoordinateSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Center");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        Label textX = new Label(group, SWT.NONE);
        textX.setText("X");
        centerXText = new Text(group, SWT.NONE);
        centerXText.addListener(SWT.Verify, UIUtils.createVerifyIntegerListener());

        Label textY = new Label(group, SWT.NONE);
        textY.setText("Y");
        centerYText = new Text(group, SWT.NONE);
        centerYText.addListener(SWT.Verify, UIUtils.createVerifyIntegerListener());

        Label textZ = new Label(group, SWT.NONE);
        textZ.setText("Z");
        centerZText = new Text(group, SWT.NONE);
        centerZText.addListener(SWT.Verify, UIUtils.createVerifyIntegerListener());


        applyLayoutData(centerXText);
        applyLayoutData(centerYText);
        applyLayoutData(centerZText);
    }


    private void applyLayoutData(Text editScale) {
        editScale.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    public void sceneChanged(Scene scene) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
