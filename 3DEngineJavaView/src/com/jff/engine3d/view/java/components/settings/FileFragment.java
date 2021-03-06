package com.jff.engine3d.view.java.components.settings;

import com.jff.engine3d.model.logic.Controller;
import com.jff.engine3d.model.logic.EngineManager;
import com.jff.engine3d.model.scene.Scene;
import com.jff.engine3d.view.java.components.utils.UIUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class FileFragment extends Composite {
    public FileFragment(Composite parent) {
        super(parent, SWT.NONE);


        RowLayout layout = new RowLayout(SWT.VERTICAL);
        layout.fill = true;
        this.setLayout(layout);

        createSaveSettings();
    }

    private void createSaveSettings() {

        final Composite parent = this;
        Group group = new Group(parent, SWT.NONE);

        group.setText("Load/Save");

        GridLayout gridLayout = new GridLayout(2, true);

        group.setLayout(gridLayout);


        Button buttonLoad = new Button(group, SWT.NONE);
        buttonLoad.setText("Load...");
        buttonLoad.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                Shell shell = parent.getShell();
                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);


                String pathToFile = fileDialog.open();

                EngineManager engineManager = EngineManager.getInstance();
                Controller controller = engineManager.getController();
                try {

                    controller.loadScene(pathToFile);
                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    UIUtils.showMessage(message);
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {


            }
        });

        Button buttonSave = new Button(group, SWT.NONE);
        buttonSave.setText("Save...");
        buttonSave.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                Shell shell = parent.getShell();
                FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);

                String pathToFile = fileDialog.open();

                EngineManager engineManager = EngineManager.getInstance();
                Controller controller = engineManager.getController();
                try {

                    controller.saveScene(pathToFile);
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
        //To change body of created methods use File | Settings | File Templates.
    }
}
