package com.jff.engine3d.model.java.components;

import com.jff.engine3d.model.Engine;
import com.jff.engine3d.model.EngineManager;
import com.jff.engine3d.model.java.components.settings.SettingsTabsFragment;
import com.jff.engine3d.model.java.main.EngineSWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public class MainWindow extends Composite {


    private EngineCanvasSWT engineCanvas;
    protected SettingsTabsFragment settingsTabsFragment;

    public MainWindow(Composite parent) {
        super(parent, SWT.NONE);


        init3DEngine();
        this.onCreate();
    }

    private void init3DEngine() {
        EngineManager engineManager = EngineManager.getInstance();

        Engine engine = new EngineSWT();
        engineManager.setEngine(engine);
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

        engineCanvas = new EngineCanvasSWT(parent);
        GridData canvasData = new GridData();
        canvasData.grabExcessHorizontalSpace = true;
        canvasData.grabExcessVerticalSpace = true;
        canvasData.horizontalAlignment = SWT.FILL;
        canvasData.verticalAlignment = SWT.FILL;

        engineCanvas.setLayoutData(canvasData);
    }
}
