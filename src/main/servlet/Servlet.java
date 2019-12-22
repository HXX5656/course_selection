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
        resp.setContentType("application/json");
        PrintWriter pw=resp.getWriter();
        JsonObject param=new JsonObject();
        Map<String,String[]> input=req.getParameterMap();
        for (Map.Entry<String,String[]> e:input.entrySet())
            param.addProperty(e.getKey(),e.getValue()[0]);
        System.out.println(param.toString());
        switch (req.getParameter("type")) {
            case "import":
                ServiceFactory.getImportServiceInstance(param).import_select();
                break;
            case "login":
                pw.print(ServiceFactory.getLoginServiceInstance(param).login());
                break;
            case "student_info":
                pw.print(ServiceFactory.getUserServiceInstance(param).get_student_courseInfo());
                break;
            case "courseList":
                pw.print(ServiceFactory.getCourseServiceInstance(param).courseList());
                break;
            case "apply":
                pw.print(ServiceFactory.getCourseServiceInstance(param).apply());
                break;
            case "select_drop":
                pw.print(ServiceFactory.getCourseServiceInstance(param).select_or_cancel());
                break;
            case "teacher_info":
                pw.print(ServiceFactory.getUserServiceInstance(param).get_teacher_info());
                break;
            case "teacher_courses":
                pw.print(ServiceFactory.getCourseServiceInstance(param).teach_courses());
                break;
            case "student_list":
                pw.print(ServiceFactory.getUserServiceInstance(param).showStudents());
                break;
            case "applications":
                pw.print(ServiceFactory.getUserServiceInstance(param).showApplications());
                break;
            case "confirm_app":
                pw.print(ServiceFactory.getCourseServiceInstance(param).confirm_apply());
                break;
            case "refuse_app":
                pw.print(ServiceFactory.getCourseServiceInstance(param).refuse_apply());
                break;
            case "student_applications":
                pw.print(ServiceFactory.getUserServiceInstance(param).student_applications());
                break;
            case "a_students":
                pw.print(ServiceFactory.getUserServiceInstance(param).generate_students());
                break;
            case "a_teachers":
                pw.print(ServiceFactory.getUserServiceInstance(param).generate_teachers());
                break;
            case "a_courses":
                pw.print(ServiceFactory.getUserServiceInstance(param).generate_courses());
                break;
            case "add_student":
                pw.print(ServiceFactory.getImportServiceInstance(param).add_student());
                break;
            case "add_teacher":
                pw.print(ServiceFactory.getImportServiceInstance(param).add_teacher());
                break;
            case "add_course":
                pw.print(ServiceFactory.getImportServiceInstance(param).addCourse(param));
                break;
            case "status":
                pw.print(ServiceFactory.getImportServiceInstance(param).change_status());
                break;
        }
        pw.flush();
        pw.close();
    }
}
