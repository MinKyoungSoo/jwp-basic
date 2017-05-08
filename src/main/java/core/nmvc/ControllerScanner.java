package core.nmvc;

import com.google.common.collect.Maps;
import core.annotation.Controller;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

public class ControllerScanner {
    
    private static final Logger logger = LoggerFactory.getLogger(ControllerScanner.class);
    
    private Reflections reflections;
    
    public ControllerScanner(Object[] basePackage) {
        this.reflections = new Reflections(basePackage);
    }
    
    public Map<Class<?>, Object> getControllers() throws IllegalAccessException, InstantiationException {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
        Map<Class<?>, Object> controllers = Maps.newHashMap();
        for(Class clazz : annotated) {
            controllers.put(clazz, clazz.newInstance());
        }
        return controllers;
    }
}
