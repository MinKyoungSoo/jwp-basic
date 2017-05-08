package core.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.nmvc.AnnotationHandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private LegacyRequestMapping lrm;
    private AnnotationHandlerMapping ahm;

    @Override
    public void init() throws ServletException {
        lrm = new LegacyRequestMapping();
        lrm.initMapping();
        ahm = new AnnotationHandlerMapping("next.controller");
        try {
            ahm.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);

        Controller controller = lrm.findController(req.getRequestURI());
        
        try {
            if (controller != null) {
                render(controller.execute(req, resp), req, resp);
            } else {
                render(ahm.getHandler(req).handle(req, resp), req, resp);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void render(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse resp) {
        try {
            View view = modelAndView.getView();
            view.render(modelAndView.getModel(), req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
