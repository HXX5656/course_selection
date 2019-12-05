package main.servlet;

import com.google.gson.JsonObject;
import main.DAO.DAOFactory;
import main.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("total get");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw=resp.getWriter();
        JsonObject param=new JsonObject();
        Map<String,String[]> input=req.getParameterMap();
        for (Map.Entry<String,String[]> e:input.entrySet())
            param.addProperty(e.getKey(),e.getValue()[0]);
        System.out.println(param.toString());
        switch (req.getParameter("type")) {
            case "import":
                ServiceFactory.getImportServiceInstance(param).importStudent();
                break;
        }
        pw.flush();
        pw.close();
    }
}
