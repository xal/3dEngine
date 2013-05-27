package com.jff.engine3d.model;

public class EngineManager {


    private Engine engine;
    private Controller controller;
    private static EngineManager instance;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

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

    public Controller getController() {
        return controller;
    }
}
