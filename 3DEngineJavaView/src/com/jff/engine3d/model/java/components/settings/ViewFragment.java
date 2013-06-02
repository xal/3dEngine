package com.jff.engine3d.model.java.components.settings;

import com.jff.engine3d.model.Controller;
import com.jff.engine3d.model.EngineManager;
import com.jff.engine3d.model.Scene;
import com.jff.engine3d.model.ViewType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class ViewFragment extends Composite {
    private final Controller controller;

    public ViewFragment(Composite parent) {
        super(parent, SWT.NONE);

        RowLayout layout = new RowLayout(SWT.VERTICAL);
        layout.fill = true;
        this.setLayout(layout);

        createViewSettings();
        createPanoramaSettings();

        EngineManager engineManager = EngineManager.getInstance();
        controller = engineManager.getController();


    }


    private void createViewSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("View");

        GridLayout gridLayout = new GridLayout(3, true);

        group.setLayout(gridLayout);

        Button polygonsButton = new Button(group, SWT.RADIO);
        polygonsButton.setText("Polygons");

        Button edgesButton = new Button(group, SWT.RADIO);
        edgesButton.setText("Edges");

        Button verticesButton = new Button(group, SWT.RADIO);
        verticesButton.setText("Vertices");

        polygonsButton.setSelection(true);

        polygonsButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                controller.setViewType(ViewType.POLYGONS);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        edgesButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                controller.setViewType(ViewType.EDGES);

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        verticesButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                controller.setViewType(ViewType.VERTICES);

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


    }


    private void createPanoramaSettings() {

        Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Panorama");

        GridLayout gridLayout = new GridLayout(1, true);

        group.setLayout(gridLayout);

        Button buttonOk = new Button(group, SWT.NONE);
        buttonOk.setText("Go");
        buttonOk.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                controller.startPanorama();

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }


    public void sceneChanged(Scene scene) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
