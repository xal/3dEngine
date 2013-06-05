package com.jff.engine3d.model.java.components.settings;

import com.jff.engine3d.model.IEngineView;
import com.jff.engine3d.model.Scene;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class SettingsTabsFragment extends Composite implements IEngineView {

    private CreateFragment createFragment;
    private ModifyFragment modifyFragment;
    private CameraFragment cameraFragment;
    private ViewFragment viewFragment;
    private FileFragment fileFragment;

    public SettingsTabsFragment(Composite parent) {
        super(parent, SWT.NONE);


        Layout layout = new GridLayout(1, false);
        this.setLayout(layout);


        createTabs();


    }


    private void createTabs() {
        GridData tabsData = new GridData(SWT.FILL, SWT.FILL, false, true);


        final TabFolder tabFolder = new TabFolder(this, SWT.NONE);

        tabFolder.setLayoutData(tabsData);


        String title;


        title = "Create";
        createFragment = new CreateFragment(tabFolder);
        createTab(tabFolder, title, createFragment);


        title = "Modify";
        modifyFragment = new ModifyFragment(tabFolder);
        createTab(tabFolder, title, modifyFragment);

        title = "Camera";
        cameraFragment = new CameraFragment(tabFolder);
        createTab(tabFolder, title, cameraFragment);

//        title = "View";
//        viewFragment = new ViewFragment(tabFolder);
//        createTab(tabFolder, title, viewFragment);

        title = "File";
        fileFragment = new FileFragment(tabFolder);
        createTab(tabFolder, title, fileFragment);
    }

    private void createTab(TabFolder tabFolder, String title, Control control) {
        TabItem item = new TabItem(tabFolder, SWT.NULL);
        item.setText(title);
        item.setControl(control);
    }

    @Override
    public void sceneChanged(Scene scene) {
        createFragment.sceneChanged(scene);
        modifyFragment.sceneChanged(scene);
        cameraFragment.sceneChanged(scene);
//        viewFragment.sceneChanged(scene);
        fileFragment.sceneChanged(scene);
    }

}
