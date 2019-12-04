package main.DAO;

import main.entity.Sec_arrangement;
import main.entity.Timeslot;

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
    public static TakesDAO getTakesDAOInstance(){return new TakesDAOImpl();}
    public static CancelDAO getCancelDAOINstance() {
        return new CancelDAOImpl();
    }
    public static ClassroomDAO getClassroomDAOInstance() {
        return new ClassroomDAOImpl();
    }
    public static DepartmentDAO getDepartmentDAOInstance() {
        return new DepartmentDAOImpl();
    }
    public static EssayDAO getEssayDAOInstance() {return new EssayDAOImpl();}
    public static TimeslotDAO getTimeslotDAOInstance(){return new TimeslotDAOImpl();}
    public static Sec_arrangementDAO getSecArrangementDAOInstance(){return  new Sec_arrangementDAOImpl();}
    public static PaperDAO getPaperDAOInstance(){return new PaperDAOImpl();}
}
