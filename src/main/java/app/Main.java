package app;


import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    public static void main(String[] args) throws Exception{
        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin", "pass", "admin@email"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //contextHandler.addServlet(new ServletHolder(new UserServlet(accountService), "/api/v1/users"));
        //contextHandler.addServlet(new ServletHolder(new SessionsServlet(accountService)),"/api/v1/sessions");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)),"/signin");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)),"/signup");


        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        System.out.println("Server started");
        server.join();

    }
}
