package container;

import java.util.HashMap;
import java.util.Map;

import controller.Controller;
import controller.ResourceController;

public class HandlerMapping {
    private static final Map<String, Controller> controllers;
    
    static {
        controllers = new HashMap<>();
        controllers.put("/data.dat", new ResourceController());;
    }

    public static Controller getController(String path) {
        return controllers.get(path);
    }
    
}
