package core.nmvc;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationHandlerMapping {
    private static final Logger logger = LoggerFactory.getLogger(ControllerScanner.class);
    
    private Object[] basePackage;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() throws Exception {
        new ControllerScanner(basePackage).getControllers().forEach(
                (clazz, o) -> 
                        ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(RequestMapping.class))
                                .forEach(method -> {
                                    RequestMapping rm = method.getAnnotation(RequestMapping.class);
                                    handlerExecutions.put(createHandlerKey(rm), createHandlerExecution(o, method));
                                })
        );
    }

    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
        return handlerExecutions.get(new HandlerKey(requestUri, rm));
    }

    private HandlerKey createHandlerKey(RequestMapping rm) {
        return new HandlerKey(rm.value(), rm.method());
    }
    
    private HandlerExecution createHandlerExecution(Object o, Method method) {
        return new HandlerExecution(o, method);
    }
}
