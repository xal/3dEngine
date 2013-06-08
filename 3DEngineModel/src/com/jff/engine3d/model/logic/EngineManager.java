package com.jff.engine3d.model.logic;

import com.jff.engine3d.model.logic.interfaces.IEngineCanvas;
import com.jff.engine3d.model.logic.interfaces.IEngineView;
import com.jff.engine3d.model.scene.Scene;

public class EngineManager {


    private Controller controller;
    private static EngineManager instance;


    public static EngineManager getInstance() {
        if (instance == null) {
            instance = new EngineManager();
        }
        return instance;
    }

    public EngineManager() {
        Scene scene = new Scene();
        this.controller = new Controller(scene);
    }

    public void init(IEngineCanvas canvas, IEngineView view) {
        controller.init(canvas, view);
    }

    public Controller getController() {
        return controller;
    }
}
