package com.jff.engine3d.model.java.components.settings;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class CreateFragment extends Composite {
    public CreateFragment(Composite parent) {
        super(parent, SWT.NONE);

        Layout layout = new FillLayout(SWT.VERTICAL);
        this.setLayout(layout);


        createCoordinateSettings();


        Button buttonOk = new Button(this, SWT.NONE);
        buttonOk.setText("OK");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                System.out.println("ok");

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


    }
}
