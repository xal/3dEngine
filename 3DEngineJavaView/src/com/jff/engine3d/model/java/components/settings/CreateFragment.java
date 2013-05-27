package com.jff.engine3d.model.java.components.settings;

import com.jff.engine3d.model.Controller;
import com.jff.engine3d.model.EngineManager;
import com.jff.engine3d.model.object.AbstractObjectParams;
import com.jff.engine3d.model.object.BicycleParams;
import com.jff.engine3d.model.utils.draw.Coordinates;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

        createButtonOk();
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
        text.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());
    }

    private void createButtonOk() {
        Button buttonOk = new Button(this, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                try {
                    int x = SWTUtils.retrieveInteger(centerXText);
                    int y = SWTUtils.retrieveInteger(centerYText);
                    int z = SWTUtils.retrieveInteger(centerZText);

                    Coordinates coordinates = new Coordinates(x, y, z);


                    int widthWheelInt = SWTUtils.retrieveInteger(widthWheel);
                    int diameterWheelInt = SWTUtils.retrieveInteger(diameterWheel);
                    int widthBushInt = SWTUtils.retrieveInteger(widthBush);
                    int diameterBushInt = SWTUtils.retrieveInteger(diameterBush);
                    int widthHelmInt = SWTUtils.retrieveInteger(widthHelm);
                    int diameterHelmInt = SWTUtils.retrieveInteger(diameterHelm);
                    int widthSeatInt = SWTUtils.retrieveInteger(widthSeat);
                    int heightSeatInt = SWTUtils.retrieveInteger(heightSeat);
                    int widthPedalInt = SWTUtils.retrieveInteger(widthPedal);
                    int heightPedalInt = SWTUtils.retrieveInteger(heightPedal);


                    AbstractObjectParams objectParams = new BicycleParams(widthWheelInt, diameterWheelInt, widthBushInt, diameterBushInt,
                            widthHelmInt, diameterHelmInt, widthSeatInt, heightSeatInt, widthPedalInt, heightPedalInt);

                    EngineManager engineManager = EngineManager.getInstance();
                    Controller controller = engineManager.getController();
                    controller.createObject(coordinates, objectParams);

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


    private void createCoordinateSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Coordinates");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);

        Label textX = new Label(group, SWT.NONE);
        textX.setText("X");
        centerXText = new Text(group, SWT.NONE);
        centerXText.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Label textY = new Label(group, SWT.NONE);
        textY.setText("Y");
        centerYText = new Text(group, SWT.NONE);
        centerYText.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());

        Label textZ = new Label(group, SWT.NONE);
        textZ.setText("Z");
        centerZText = new Text(group, SWT.NONE);
        centerZText.addListener(SWT.Verify, SWTUtils.createVerifyIntegerListener());


    }
}
