package main.DAO;

public class DAOFactory {
    public static CourseDAO getCourseDAOInstance() {
        return new CourseDAOImpl();
    }
    public static TeacherDAO getTeacherDAOInstance() {
        return new TeacherDAOImpl();
    }
    public static StudentDAO getStudentDAOInstance(){return new StudentDAOImpl();}
    public static SectionDAO getSectionDAOInstance(){return new SectionDAOImpl();}
    public static TeachesDAO getTeachesDAOInstance(){return new TeachesDAOImpl();}
}
