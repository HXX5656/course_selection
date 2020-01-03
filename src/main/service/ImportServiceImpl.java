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
    private TakesDAO takesDAO;
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
        this.takesDAO = DAOFactory.getTakesDAOInstance();
    }
    @Override
    public String import_select() throws IOException {
        String name=jsonObject.get("name").getAsString();
        String path=(jsonObject==null)?"src/main/service/student.xlsx":jsonObject.get("filePath").getAsString();
        //String realPath = path.substring(path.lastIndexOf("\\")+1);
        if(name.equals("student")){
            return importStudent(path)+"";
        } else if(name.equals("teacher")) {
            return importTeacher(path)+"";
        } else if(name.equals("course")) {
            return importCourse(path);
        } else if(name.equals("grade")) {
            return importGrade(path)+"";
        } else if(name.equals("classroom")) {
            return importClassroom(path)+"";
        } else if(name.equals("department")) {
            return importDepartment(path)+"";
        }
        return "不被识别的导入类型";
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
    private String importCourse(String path) throws IOException {
        String result = "";
        try {
            List<JsonObject> courses=ExcelUtil.parseJson(path);
            for (JsonObject json:courses) {
                String tmp = addCourse(json);
                if (!StringUtil.isEmpty(tmp))
                    result += tmp;
            }
            deleteExcel(path);

        } catch (Exception e) {
            e.printStackTrace();
            return "出现异常，失败";
        }
        return result;
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
    public  String addCourse(JsonObject jsonObject) throws IOException {
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
            List<Map<String, String>> sec_time = StringUtil.parse_time_and_place(arrangement);
            List<Map<String,String>> teach_courses = teachesDAO.infoList(teacher_id);
            //检测是否存在冲突
            //检测时间地点是否冲突 检测任课人是否冲突
            for (Map<String, String> res : sec_time) {
                if ("".equals(res.get("time_start_id"))) {
                    assert (false);
//                    Timeslot timeslot = new Timeslot(res.get("start"), res.get("end"), res.get("day"));
//                    timeslotDAO.append(timeslot);
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
                        if(sec_arrangementDAO.infoList(i+"",place).size()>0) {
                            String course_exist = sec_arrangementDAO.infoList(i+"", place).get(0).get("course_id");
                            String course_exist_name = courseDAO.infoList(course_exist).get(0).get("course_name");
                            return "在增加课程"+course_name+"时，与已经存在的课程"+course_exist_name+"存在时间地点上的冲突，该条课程插入失败；\n";
                        }
                        for (Map<String,String> tt:teach_courses) {
                            if(sec_arrangementDAO.infoList(i+"",tt.get("course_id"),tt.get("section_id"),tt.get("semester"),tt.get("year")).size()>0) {
                                String course_exist = sec_arrangementDAO.infoList(i+"",tt.get("course_id"),tt.get("section_id"),tt.get("semester"),tt.get("year")).get(0).get("course_id");
                                String course_exist_name = courseDAO.infoList(course_exist).get(0).get("course_name");
                                return "在增加课程"+course_name+"时，任课人已经在重叠时间段教授课程"+course_exist_name+",出现冲突，该条课程插入失败;\n";
                            }
                        }
                    }
                }
            }
            if (courseDAO.infoList(course.getCourse_id()).size() == 0) {
                courseDAO.append(course);
            }
            Exam exam = new Exam("", "18");
            int ret_exam = examDAO.append(exam);
            if (ret_exam < 1) {
                return "考试分配出现问题，插入失败";
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
            return "出现异常，插入失败";
        }
        return "";


    }
    private int importGrade(String path) throws IOException {
        int result = 0;
        try {
            List<JsonObject> grades=ExcelUtil.parseJson(path);
            for (JsonObject json:grades) {
                int tmp = add_grade(json);
                if (tmp < result)
                    result = tmp;
            }
            deleteExcel(path);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return result;
    }
    public int add_grade(JsonObject json) {
        String teacher_id = this.jsonObject.get("teacher_id").getAsString();
        String course_code = this.jsonObject.get("course_code").getAsString();
        TimeControlService timeControlService = TimeControlService.getInstance();
        String semester = ""+timeControlService.getSemester();
        String year = ""+timeControlService.getYear();
        Map<String,String> map=StringUtil.parse_course_code(course_code);
        if(!("".equals(teacher_id))&&(teachesDAO.infoList(teacher_id,map.get("course_id"),map.get("section_id"),semester,year).size()==0))
            return -2;
        if(takesDAO.infoList(json.get("student_id").getAsString(),map.get("course_id"),map.get("section_id"),semester,year).size() == 1) {
            Takes takes = new Takes(json.get("student_id").getAsString(),map.get("course_id"),map.get("section_id"),semester,year,json.get("grade").getAsString());
            return takesDAO.modify(takes);
        } else {
            return -1;
        }
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
