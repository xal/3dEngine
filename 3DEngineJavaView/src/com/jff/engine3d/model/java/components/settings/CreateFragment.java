package com.jff.engine3d.model.java.components.settings;

import com.jff.engine3d.model.Controller;
import com.jff.engine3d.model.EngineManager;
import com.jff.engine3d.model.Point3D;
import com.jff.engine3d.model.Scene;
import com.jff.engine3d.model.object.AbstractObjectParams;
import com.jff.engine3d.model.object.BicycleParams;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class CreateFragment extends Composite {
    private Text centerXText;
    private Text centerYText;
    private Text centerZText;

    private Text widthWheel;
    private Text diameterWheel;
    private Text widthBush;
    private Text diameterBush;
    private Text widthHelm;
    private Text diameterHelm;
    private Text widthSeat;
    private Text heightSeat;
    private Text widthPedal;
    private Text heightPedal;

    public CreateFragment(Composite parent) {
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

        widthWheel.setText("100");
        diameterWheel.setText("100");
        widthBush.setText("100");
        diameterBush.setText("100");
        widthHelm.setText("100");
        diameterHelm.setText("100");
        widthSeat.setText("100");
        heightSeat.setText("100");
        widthPedal.setText("100");
        heightPedal.setText("100");

    }

    private void createObjectParamsSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("ObjectParams");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        new Label(group, SWT.NONE).setText("Width wheel");
        widthWheel = new Text(group, SWT.NONE);
        addListeners(widthWheel);

        new Label(group, SWT.NONE).setText("Diameter wheel");
        diameterWheel = new Text(group, SWT.NONE);
        addListeners(diameterWheel);


        new Label(group, SWT.NONE).setText("Width bush");
        widthBush = new Text(group, SWT.NONE);
        addListeners(widthBush);

        new Label(group, SWT.NONE).setText("Diameter bush");
        diameterBush = new Text(group, SWT.NONE);
        addListeners(diameterBush);


        new Label(group, SWT.NONE).setText("Width helm");
        widthHelm = new Text(group, SWT.NONE);
        addListeners(widthHelm);

        new Label(group, SWT.NONE).setText("Diameter helm");
        diameterHelm = new Text(group, SWT.NONE);
        addListeners(diameterHelm);


        new Label(group, SWT.NONE).setText("Width seat");
        widthSeat = new Text(group, SWT.NONE);
        addListeners(widthSeat);

        new Label(group, SWT.NONE).setText("Height seat");
        heightSeat = new Text(group, SWT.NONE);
        addListeners(heightSeat);


        new Label(group, SWT.NONE).setText("Width pedal");
        widthPedal = new Text(group, SWT.NONE);
        addListeners(widthPedal);

        new Label(group, SWT.NONE).setText("Height pedal");
        heightPedal = new Text(group, SWT.NONE);
        addListeners(heightPedal);


    }

    private void addListeners(Text text) {
        text.addListener(SWT.Verify, Utils.createVerifyPositiveIntegerListener());
    }

    private void createButtonOk() {
        Button buttonOk = new Button(this, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                try {
                    int x = Utils.retrieveInteger(centerXText);
                    int y = Utils.retrieveInteger(centerYText);
                    int z = Utils.retrieveInteger(centerZText);

                    Point3D point3D = new Point3D(x, y, z);


                    int widthWheelInt = Utils.retrieveInteger(widthWheel);
                    int diameterWheelInt = Utils.retrieveInteger(diameterWheel);
                    int widthBushInt = Utils.retrieveInteger(widthBush);
                    int diameterBushInt = Utils.retrieveInteger(diameterBush);
                    int widthHelmInt = Utils.retrieveInteger(widthHelm);
                    int diameterHelmInt = Utils.retrieveInteger(diameterHelm);
                    int widthSeatInt = Utils.retrieveInteger(widthSeat);
                    int heightSeatInt = Utils.retrieveInteger(heightSeat);
                    int widthPedalInt = Utils.retrieveInteger(widthPedal);
                    int heightPedalInt = Utils.retrieveInteger(heightPedal);


                    AbstractObjectParams objectParams = new BicycleParams(widthWheelInt, diameterWheelInt, widthBushInt, diameterBushInt,
                            widthHelmInt, diameterHelmInt, widthSeatInt, heightSeatInt, widthPedalInt, heightPedalInt);

                    EngineManager engineManager = EngineManager.getInstance();
                    Controller controller = engineManager.getController();
                    controller.createObject(point3D, objectParams);

                } catch (Exception e) {
                    e.printStackTrace();
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


    private void createCoordinateSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Center");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        Label textX = new Label(group, SWT.NONE);
        textX.setText("X");
        centerXText = new Text(group, SWT.NONE);
        centerXText.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Label textY = new Label(group, SWT.NONE);
        textY.setText("Y");
        centerYText = new Text(group, SWT.NONE);
        centerYText.addListener(SWT.Verify, Utils.createVerifyIntegerListener());

        Label textZ = new Label(group, SWT.NONE);
        textZ.setText("Z");
        centerZText = new Text(group, SWT.NONE);
        centerZText.addListener(SWT.Verify, Utils.createVerifyIntegerListener());


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
