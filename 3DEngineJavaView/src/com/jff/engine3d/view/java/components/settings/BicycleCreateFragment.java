package com.jff.engine3d.view.java.components.settings;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.logic.Controller;
import com.jff.engine3d.model.logic.EngineManager;
import com.jff.engine3d.model.logic.interfaces.IEngineView;
import com.jff.engine3d.model.object.params.AbstractObjectParams;
import com.jff.engine3d.model.object.params.BicycleParams;
import com.jff.engine3d.model.scene.Scene;
import com.jff.engine3d.view.java.components.utils.UIUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class BicycleCreateFragment extends Composite implements IEngineView {
    private Text centerXText;
    private Text centerYText;
    private Text centerZText;

    private Text widthWheel;
    private Text diameterWheel;


    private Text widthHelm;

    private Text lengthSeat;
    private Text widthSeat;
    private Text heightSeat;

    private Text widthPedal;
    private Text heightPedal;
    private Text lengthPedal;

    private Text diameterFrame;

    public BicycleCreateFragment(Composite parent) {
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

        widthWheel.setText("20");
        diameterWheel.setText("100");
        diameterFrame.setText("10");

        lengthPedal.setText("40");
        widthPedal.setText("40");
        heightPedal.setText("10");

        lengthSeat.setText("60");
        widthSeat.setText("40");
        heightSeat.setText("10");


        widthHelm.setText("100");
    }

    private void createObjectParamsSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("ObjectParams");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        new Label(group, SWT.NONE).setText("Width wheel");
        widthWheel = new Text(group, SWT.NONE);

        new Label(group, SWT.NONE).setText("Diameter wheel");
        diameterWheel = new Text(group, SWT.NONE);


        new Label(group, SWT.NONE).setText("Width helm");
        widthHelm = new Text(group, SWT.NONE);


        new Label(group, SWT.NONE).setText("Width seat");
        widthSeat = new Text(group, SWT.NONE);

        new Label(group, SWT.NONE).setText("Height seat");
        heightSeat = new Text(group, SWT.NONE);
        addListeners(heightSeat);


        new Label(group, SWT.NONE).setText("Length seat");
        lengthSeat = new Text(group, SWT.NONE);


        new Label(group, SWT.NONE).setText("Width pedal");
        widthPedal = new Text(group, SWT.NONE);

        new Label(group, SWT.NONE).setText("Height pedal");
        heightPedal = new Text(group, SWT.NONE);

        new Label(group, SWT.NONE).setText("Length pedal");
        lengthPedal = new Text(group, SWT.NONE);

        new Label(group, SWT.NONE).setText("Diameter frame");
        diameterFrame = new Text(group, SWT.NONE);

        addListeners(widthHelm);
        addListeners(widthWheel);
        addListeners(diameterWheel);
        addListeners(widthSeat);
        addListeners(lengthSeat);
        addListeners(widthPedal);
        addListeners(heightPedal);
        addListeners(diameterFrame);
        addListeners(lengthPedal);

        applyLayoutData(widthHelm);
        applyLayoutData(widthWheel);
        applyLayoutData(diameterWheel);
        applyLayoutData(widthSeat);
        applyLayoutData(heightSeat);
        applyLayoutData(lengthSeat);
        applyLayoutData(widthPedal);
        applyLayoutData(heightPedal);
        applyLayoutData(diameterFrame);
        applyLayoutData(lengthPedal);
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


                    int widthWheelInt = UIUtils.retrieveInteger(widthWheel);
                    int diameterWheelInt = UIUtils.retrieveInteger(diameterWheel);
                    int diameterFrameInt = UIUtils.retrieveInteger(diameterFrame);
                    int widthHelmInt = UIUtils.retrieveInteger(widthHelm);

                    int widthSeatInt = UIUtils.retrieveInteger(widthSeat);
                    int heightSeatInt = UIUtils.retrieveInteger(heightSeat);
                    int lengthSeatInt = UIUtils.retrieveInteger(lengthSeat);

                    int widthPedalInt = UIUtils.retrieveInteger(widthPedal);
                    int heightPedalInt = UIUtils.retrieveInteger(heightPedal);
                    int lengthPedalInt = UIUtils.retrieveInteger(lengthPedal);


                    AbstractObjectParams objectParams =
                            new BicycleParams(widthWheelInt, diameterWheelInt,
                                    lengthSeatInt, diameterFrameInt, widthHelmInt,
                                    lengthPedalInt, widthSeatInt, heightSeatInt, widthPedalInt,
                                    heightPedalInt);

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
