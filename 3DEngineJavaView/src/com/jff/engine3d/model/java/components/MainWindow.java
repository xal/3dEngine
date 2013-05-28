package com.jff.engine3d.model.java.components;


import com.jff.engine3d.model.java.components.settings.SettingsTabsFragment;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public class MainWindow extends Composite {


    protected SettingsTabsFragment settingsTabsFragment;
    protected EngineCanvasSWT engineCanvas;

    public MainWindow(Composite parent) {
        super(parent, SWT.NONE);


        this.onCreate();
    }


    protected void onCreate() {

        Composite parent = this;

        Layout layout = new GridLayout(2, false);

        parent.setLayout(layout);


        settingsTabsFragment = new SettingsTabsFragment(parent);

        GridData settingsFragmentData = new GridData();

        settingsFragmentData.grabExcessHorizontalSpace = false;
        settingsFragmentData.grabExcessVerticalSpace = true;
        settingsFragmentData.horizontalAlignment = SWT.FILL;
        settingsFragmentData.verticalAlignment = SWT.FILL;

        settingsTabsFragment.setLayoutData(settingsFragmentData);


        GridData canvasData = new GridData();
        canvasData.grabExcessHorizontalSpace = true;
        canvasData.grabExcessVerticalSpace = true;
        canvasData.horizontalAlignment = SWT.FILL;
        canvasData.verticalAlignment = SWT.FILL;

        engineCanvas = new EngineCanvasSWT(parent);
        engineCanvas.setLayoutData(canvasData);
    }
}
