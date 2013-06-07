package model;

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
