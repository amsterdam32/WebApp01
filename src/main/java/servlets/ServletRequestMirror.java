package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServletRequestMirror extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map parameterMap = req.getParameterMap();
        if(null == parameterMap || !parameterMap.containsKey("key")){
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.getWriter().println("");
        }
        else{
            resp.setStatus(HttpServletResponse.SC_OK);
            //Map<String, Object> pageValue = createPageVariablesMap(req);
            //resp.getWriter().println(PageGenerator.instance().getPage("mirror.html", pageValue));
            resp.getWriter().println(req.getParameter("key"));
        }



        resp.setContentType("text/html; charset=utf-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageValue = createPageVariablesMap(req);
        String parameters = req.getParameterMap().toString();

        resp.setContentType("text/html; charset=utf-8");
        if(parameters == null || parameters.isEmpty()){
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        else resp.setStatus(HttpServletResponse.SC_OK);

        resp.getWriter().println(PageGenerator.instance().getPage("mirror.html", pageValue));

    }

    public static Map<String, Object> createPageVariablesMap(HttpServletRequest request){
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("key", request.getParameter("key"));
        return pageVariables;
    }
}
