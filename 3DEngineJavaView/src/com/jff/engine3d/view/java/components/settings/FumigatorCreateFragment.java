package com.jff.engine3d.view.java.components.settings;


import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.logic.Controller;
import com.jff.engine3d.model.logic.EngineManager;
import com.jff.engine3d.model.logic.interfaces.IEngineView;
import com.jff.engine3d.model.object.params.AbstractObjectParams;
import com.jff.engine3d.model.object.params.FumigatorParams;
import com.jff.engine3d.model.scene.Scene;
import com.jff.engine3d.view.java.components.utils.UIUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class FumigatorCreateFragment extends Composite implements IEngineView {
    private Text centerXText;
    private Text centerYText;
    private Text centerZText;

    public Text diameterSocketCylinder;
    public Text heightSocketCylinder;

    public Text widthSocketBox;
    public Text lengthSocketBox;
    public Text heightSocketBox;

    public Text widthBaseBox;
    public Text lengthBaseBox;
    public Text heightBaseBox;


    public Text diameterLowerCylinder;
    public Text heightLowerCylinder;

    public Text diameterMiddleCylinder;
    public Text heightMiddleCylinder;


    public FumigatorCreateFragment(Composite parent) {
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

        centerXText.setText("0");
        centerYText.setText("0");
        centerZText.setText("0");


        diameterSocketCylinder.setText("10");
        heightSocketCylinder.setText("30");

        widthSocketBox.setText("50");
        lengthSocketBox.setText("80");
        heightSocketBox.setText("60");

        widthBaseBox.setText("100");
        lengthBaseBox.setText("100");
        heightBaseBox.setText("50");


        diameterLowerCylinder.setText("50");
        heightLowerCylinder.setText("20");

        diameterMiddleCylinder.setText("30");
        heightMiddleCylinder.setText("10");


    }

    private void createObjectParamsSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("ObjectParams");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);


        new Label(group, SWT.NONE).setText("diameterSocketCylinder");
        diameterSocketCylinder = new Text(group, SWT.NONE);
        addListeners(diameterSocketCylinder);
        addLayoutData(diameterSocketCylinder);
        new Label(group, SWT.NONE).setText("heightSocketCylinder");
        heightSocketCylinder = new Text(group, SWT.NONE);
        addListeners(heightSocketCylinder);
        addLayoutData(heightSocketCylinder);

        new Label(group, SWT.NONE).setText("widthSocketBox");
        widthSocketBox = new Text(group, SWT.NONE);
        addListeners(widthSocketBox);
        addLayoutData(widthSocketBox);
        new Label(group, SWT.NONE).setText("lengthSocketBox");
        lengthSocketBox = new Text(group, SWT.NONE);
        addListeners(lengthSocketBox);
        addLayoutData(lengthSocketBox);
        new Label(group, SWT.NONE).setText("heightSocketBox");
        heightSocketBox = new Text(group, SWT.NONE);
        addListeners(heightSocketBox);
        addLayoutData(heightSocketBox);

        new Label(group, SWT.NONE).setText("widthBaseBox");
        widthBaseBox = new Text(group, SWT.NONE);
        addListeners(widthBaseBox);
        addLayoutData(widthBaseBox);
        new Label(group, SWT.NONE).setText("lengthBaseBox");
        lengthBaseBox = new Text(group, SWT.NONE);
        addListeners(lengthBaseBox);
        addLayoutData(lengthBaseBox);
        new Label(group, SWT.NONE).setText("heightBaseBox");
        heightBaseBox = new Text(group, SWT.NONE);
        addListeners(heightBaseBox);
        addLayoutData(heightBaseBox);


        new Label(group, SWT.NONE).setText("diameterLowerCylinder");
        diameterLowerCylinder = new Text(group, SWT.NONE);
        addListeners(diameterLowerCylinder);
        addLayoutData(diameterLowerCylinder);
        new Label(group, SWT.NONE).setText("heightLowerCylinder");
        heightLowerCylinder = new Text(group, SWT.NONE);
        addListeners(heightLowerCylinder);
        addLayoutData(heightLowerCylinder);

        new Label(group, SWT.NONE).setText("diameterMiddleCylinder");
        diameterMiddleCylinder = new Text(group, SWT.NONE);
        addListeners(diameterMiddleCylinder);
        addLayoutData(diameterMiddleCylinder);
        new Label(group, SWT.NONE).setText("heightMiddleCylinder");
        heightMiddleCylinder = new Text(group, SWT.NONE);
        addListeners(heightMiddleCylinder);
        addLayoutData(heightMiddleCylinder);


    }

    private void addLayoutData(Text text) {
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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

                    Point3D point3d = new Point3D(x, y, z);


                    int diameterSocketCylinderInt = UIUtils.retrieveInteger(diameterSocketCylinder);
                    int heightSocketCylinderInt = UIUtils.retrieveInteger(heightSocketCylinder);

                    int widthSocketBoxInt = UIUtils.retrieveInteger(widthSocketBox);
                    int lengthSocketBoxInt = UIUtils.retrieveInteger(lengthSocketBox);
                    int heightSocketBoxInt = UIUtils.retrieveInteger(heightSocketBox);

                    int widthBaseBoxInt = UIUtils.retrieveInteger(widthBaseBox);
                    int lengthBaseBoxInt = UIUtils.retrieveInteger(lengthBaseBox);
                    int heightBaseBoxInt = UIUtils.retrieveInteger(heightBaseBox);


                    int diameterLowerCylinderInt = UIUtils.retrieveInteger(diameterLowerCylinder);
                    int heightLowerCylinderInt = UIUtils.retrieveInteger(heightLowerCylinder);

                    int diameterMiddleCylinderInt = UIUtils.retrieveInteger(diameterMiddleCylinder);
                    int heightMiddleCylinderInt = UIUtils.retrieveInteger(heightMiddleCylinder);


                    AbstractObjectParams objectParams = new FumigatorParams(
                            diameterSocketCylinderInt, heightSocketCylinderInt, widthSocketBoxInt,
                            lengthSocketBoxInt, heightSocketBoxInt, widthBaseBoxInt, lengthBaseBoxInt,
                            heightBaseBoxInt, diameterLowerCylinderInt, heightLowerCylinderInt,
                            diameterMiddleCylinderInt, heightMiddleCylinderInt);


                    EngineManager engineManager = EngineManager.getInstance();
                    Controller controller = engineManager.getController();
                    controller.createObject(point3d, objectParams);

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
