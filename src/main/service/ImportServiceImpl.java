package main.service;

import com.google.gson.JsonObject;
import main.DAO.*;
import main.entity.*;
import main.util.EncryptUtil;
import main.util.ExcelUtil;
import main.util.StringUtil;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImportServiceImpl implements ImportService {
    private JsonObject jsonObject;
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    private TimeControlService timeControlService;
    private DepartmentDAO departmentDAO;
    private CourseDAO courseDAO;
    private ExamDAO examDAO;
    private SectionDAO sectionDAO;
    private TeachesDAO teachesDAO;
    private TimeslotDAO timeslotDAO;
    private Sec_arrangementDAO sec_arrangementDAO;
    private PaperDAO paperDAO;
    private EssayDAO essayDAO;
    private ClassroomDAO classroomDAO;
    private AccountDAO accountDAO;
    public ImportServiceImpl(JsonObject param) {
        this.jsonObject=param;
        this.studentDAO= DAOFactory.getStudentDAOInstance();
        this.teacherDAO=DAOFactory.getTeacherDAOInstance();
        this.timeControlService=TimeControlService.getInstance();
        this.departmentDAO=DAOFactory.getDepartmentDAOInstance();
        this.courseDAO=DAOFactory.getCourseDAOInstance();
        this.examDAO=DAOFactory.getExamDAOInstance();
        this.sectionDAO=DAOFactory.getSectionDAOInstance();
        this.teachesDAO=DAOFactory.getTeachesDAOInstance();
        this.timeslotDAO=DAOFactory.getTimeslotDAOInstance();
        this.sec_arrangementDAO=DAOFactory.getSecArrangementDAOInstance();
        this.paperDAO=DAOFactory.getPaperDAOInstance();
        this.essayDAO=DAOFactory.getEssayDAOInstance();
        this.classroomDAO=DAOFactory.getClassroomDAOInstance();
        this.accountDAO=DAOFactory.getAccountDAOInstance();
    }
    @Override
    public int import_select() throws IOException {
        String name=jsonObject.get("name").getAsString();
        String path=(jsonObject==null)?"src/main/service/student.xlsx":jsonObject.get("filePath").getAsString();
        //String realPath = path.substring(path.lastIndexOf("\\")+1);
        if(name.equals("student")){
            return importStudent(path);
        } else if(name.equals("teacher")) {
            return importTeacher(path);
        } else if(name.equals("course")) {
            return importCourse(path);
        } else if(name.equals("grade")) {
            return importGrade(path);
        } else if(name.equals("classroom")) {
            return importClassroom(path);
        } else if(name.equals("department")) {
            return importDepartment(path);
        }
        return -1;
    }
    @Override
    public int add_student() {
        try {
        String student_id = jsonObject.get("student_id").getAsString();
        String student_name = jsonObject.get("student_name").getAsString();
        String enter_time = jsonObject.get("enter_time").getAsString();
        String gradu_time = jsonObject.get("gradu_time").getAsString();
        String department = jsonObject.get("department").getAsString();
        Student student=new Student(student_id,student_name,enter_time,gradu_time,department);
        if(studentDAO.infoList(student_id).size()>0) {
            studentDAO.modify(student);
        } else {
            studentDAO.append(student);
            List<Student> ss = new ArrayList<>();
            ss.add(student);
            auto_import_account(ss);
        }} catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    @Override
    public int add_teacher() {
        try {
            String student_id = jsonObject.get("teacher_id").getAsString();
            String student_name = jsonObject.get("teacher_name").getAsString();
            String enter_time = jsonObject.get("enter_time").getAsString();
            String gradu_time = jsonObject.get("leave_time").getAsString();
            String department = jsonObject.get("department").getAsString();
            Teacher teacher=new Teacher(student_id,student_name,enter_time,gradu_time,department);
            if(teacherDAO.infoList(student_id).size()>0) {
                teacherDAO.modify(teacher);
            } else {
                teacherDAO.append(teacher);
                List<Teacher> ss = new ArrayList<>();
                ss.add(teacher);
                auto_import_teacher(ss);
            }} catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    private int importStudent(String path) throws IOException {
        //String path=(jsonObject==null)?"src/main/service/student.xlsx":jsonObject.get("filePath").getAsString();
        //String realPath = path.substring(path.lastIndexOf("\\")+1);
        try {
            List<Student> list=ExcelUtil.parseFromExcel(path, Student.class);
            for (Student s:list) {
                studentDAO.append(s);
            }
            deleteExcel(path);
            return auto_import_account(list);
        }
        catch (Exception e) {e.printStackTrace();}

        return -1;
    }
    private int importTeacher(String path) throws IOException {
        try {
            List<Teacher> teachers=ExcelUtil.parseFromExcel(path,Teacher.class);
            for (Teacher teacher:teachers) {
                teacherDAO.append(teacher);
            }
            deleteExcel(path);
            return auto_import_teacher(teachers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    private int auto_import_teacher(List<Teacher> objects) {
        try {

            for (Teacher t:objects){
                String id = ((int)Float.parseFloat(t.getTeacher_id()))+"";
                id=StringUtil.parse_idNumber(id,true);
                String pwd_tmp = "12345678";//初始密码
                Account account = new Account(id, EncryptUtil.md5(id,pwd_tmp));
                accountDAO.append(account);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    private int auto_import_account(List<Student> objects) {
        try {

            for (Student t:objects){
                String id = ((int)Float.parseFloat(t.getStudent_id()))+"";
                id=StringUtil.parse_idNumber(id,false);
                String pwd_tmp = "12345";//初始密码
                Account account = new Account(id, EncryptUtil.md5(id,pwd_tmp));
                accountDAO.append(account);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    private int importDepartment(String path) throws IOException {
        try {
            List<Department> departments=ExcelUtil.parseFromExcel(path,Department.class);
            for (Department department:departments) {
                departmentDAO.append(department);
            }
            return deleteExcel(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    private int importClassroom(String path) throws IOException {
        try {
            List<Classroom> classrooms=ExcelUtil.parseFromExcel(path,Classroom.class);
            for (Classroom classroom:classrooms) {
                classroomDAO.append(classroom);
            }
            return deleteExcel(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    private int importCourse(String path) throws IOException {
        try {
            List<JsonObject> courses=ExcelUtil.parseJson(path);
            for (JsonObject json:courses) {
                addCourse(json);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    public int change_status() {
        String select = jsonObject.get("select").getAsString();
        String delete = jsonObject.get("delete").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();
        TimeControlService t = TimeControlService.getInstance();
        t.setCanSelect("1".equals(select));
        t.setCanDelete("1".equals(delete));
        t.setSemester(Integer.parseInt(semester));
        t.setYear(Integer.parseInt(year));
        return 0;
    }
    @Override
    public  int addCourse(JsonObject jsonObject) throws IOException {
        try {
            Map<String, String> result = StringUtil.parse_course_code(jsonObject.get("course_code").getAsString());
            String course_id = result.get("course_id");
            String dep_id = departmentDAO.info_id(result.get("dep_name")).get(0).get("dep_id");
            String section_id = result.get("section_id");
            String course_name = jsonObject.get("course_name").getAsString();
            String credit = jsonObject.get("credit").getAsString();
            String hours = jsonObject.get("hours").getAsString();
            String max = jsonObject.get("max").getAsString();
            String exam_type = jsonObject.get("exam_type").getAsString();
            String semester = timeControlService.getSemester() + "";
            String year = timeControlService.getYear() + "";
            String teacher_id = jsonObject.get("teacher_id").getAsString();
            String arrangement = jsonObject.get("time-place").getAsString();
            String exam_time = jsonObject.get("exam_time").getAsString();
            Course course = new Course(course_id, course_name, credit, hours, dep_id);
            if (courseDAO.infoList(course.getCourse_id()).size() == 0) {
                courseDAO.append(course);
            }
            Exam exam = new Exam("", "18");
            int ret_exam = examDAO.append(exam);
            if (ret_exam < 1) {
                return -1;
            }
            String exam_id = ret_exam + "";
            Section section = new Section(course_id, section_id, semester, year, "1", "17", max, exam_id);
            if (sectionDAO.infoList(course_id, section_id, semester, year).size() == 0) {
                sectionDAO.append(section);
            }
            Teaches teaches = new Teaches(teacher_id, course_id, section_id, semester, year);
            if (teachesDAO.infoList(teacher_id, course_id, section_id, semester, year).size() == 0) {
                teachesDAO.append(teaches);
            }
            List<Map<String, String>> sec_time = StringUtil.parse_time_and_place(arrangement);
            //sec_arrangement
            for (Map<String, String> res : sec_time) {
                if ("".equals(res.get("time_start_id"))) {
                    assert (false);
                    Timeslot timeslot = new Timeslot(res.get("start"), res.get("end"), res.get("day"));
                    timeslotDAO.append(timeslot);
                } else {
                    String time_end_id = "";
                    if ("".equals(res.get("time_end_id"))) {
                        time_end_id = res.get("time_start_id");
                    } else {
                        time_end_id = res.get("time_end_id");
                    }
                    int start = Integer.parseInt(res.get("time_start_id"));
                    int end = Integer.parseInt(time_end_id);
                    String place = res.get("place");
                    for (int i = start; i <= end; i++) {
                        Sec_arrangement arrangement1 = new Sec_arrangement(i + "", place, course_id, section_id, semester, year);
                        sec_arrangementDAO.append(arrangement1);
                    }
                }
            }
            //exam_arrange
            if (exam_type.equals("paper")) {
                List<Map<String, String>> exam_arr = StringUtil.parse_time_and_place(exam_time);
                assert (exam_arr.size() == 1 && exam_arr.get(0).get("place").equals(""));
                Paper paper = new Paper(exam_id, "paper", exam_arr.get(0).get("time_start_id"), "");
                paperDAO.append(paper);
            } else {
                Essay essay = new Essay(exam_id, "未补充");
                essayDAO.append(essay);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;


    }
    private int importGrade(String path) throws IOException {
        return -1;
    }
    private int deleteExcel(String path) throws IOException{

        try {
            File file=new File(path);
            if(!file.exists()) {
                return 0;
            } else  {
                file.delete();
                System.out.println("成功删除");
                return 0;
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
}
