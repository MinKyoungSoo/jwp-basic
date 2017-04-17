package next.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/hello", loadOnStartup = 1)
public class HelloWorldServlet extends HttpServlet {

    // 상태를 가지면 Multi Thread 에서 문제가 발생할 소지가 있다.
    //private String name;


    @Override
    public void init() throws ServletException {
        System.out.println("Init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.getWriter().println(String.format("<h1>Hello %s</h1>", name));
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
