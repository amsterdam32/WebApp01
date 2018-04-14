package app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
import servlets.AllRequestsServlet;
import servlets.ServletRequestMirror;

public class Main {
    public static void main(String[] args) throws Exception{
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        ServletRequestMirror servletRequestMirror = new ServletRequestMirror();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(allRequestsServlet), "/*");
        context.addServlet(new ServletHolder(servletRequestMirror), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        System.out.println("Server started");
        server.join();

    }
}
