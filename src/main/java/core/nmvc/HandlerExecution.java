package core.nmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class HandlerExecution {
    private static final Logger logger = LoggerFactory.getLogger(ControllerScanner.class);
    
    private Object declaredObject;
    private Method method;
    
    public HandlerExecution(Object declaredObject, Method method) {
        this.declaredObject = declaredObject;
        this.method = method;
    }
    
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = declaredObject.getClass();
        ModelAndView modelAndView = new ModelAndView();
        for(Method executeMethod : clazz.getDeclaredMethods()) {
            if(executeMethod.getName().equals(this.method.getName())) {
                modelAndView = (ModelAndView)this.method.invoke(declaredObject, request, response);
            }
        }
        return modelAndView;
    }
}
