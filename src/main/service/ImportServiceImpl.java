package main.service;

import com.google.gson.JsonObject;
import main.DAO.*;
import main.entity.*;
import main.util.ExcelUtil;
import main.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    private int importStudent(String path) throws IOException {
        //String path=(jsonObject==null)?"src/main/service/student.xlsx":jsonObject.get("filePath").getAsString();
        //String realPath = path.substring(path.lastIndexOf("\\")+1);
        try {
            List<Student> list=ExcelUtil.parseFromExcel(path, Student.class);
            for (Student s:list) {
                studentDAO.append(s);
            }
            return deleteExcel(path);
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
            return deleteExcel(path);
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
    private  void addCourse(JsonObject jsonObject) throws IOException {
        Map<String,String> result= StringUtil.parse_course_code(jsonObject.get("course_code").getAsString());
        String course_id=result.get("course_id");
        String dep_id=departmentDAO.info_id(result.get("dep_name")).get(0).get("dep_id");
        String section_id=result.get("section_id");
        String course_name=jsonObject.get("course_name").getAsString();
        String credit=jsonObject.get("credit").getAsString();
        String hours=jsonObject.get("hours").getAsString();
        String max=jsonObject.get("max").getAsString();
        String exam_type=jsonObject.get("exam_type").getAsString();
        String semester=timeControlService.getSemester()+"";
        String year=timeControlService.getYear()+"";
        String teacher_id=jsonObject.get("teacher_id").getAsString();
        String arrangement=jsonObject.get("time-place").getAsString();
        String exam_time=jsonObject.get("exam_time").getAsString();
        Course course=new Course(course_id,course_name,credit,hours,dep_id);
        if(courseDAO.infoList(course.getCourse_id()).size()==0) {
            courseDAO.append(course);
        }
        Exam exam=new Exam("","18");
        int ret_exam=examDAO.append(exam);
        if(ret_exam<1) {
            return;
        }
        String exam_id=ret_exam+"";
        Section section=new Section(course_id,section_id,semester,year,"1","17",max,exam_id);
        if(sectionDAO.infoList(course_id,section_id,semester,year).size()==0) {
            sectionDAO.append(section);
        }
        Teaches teaches=new Teaches(teacher_id,course_id,section_id,semester,year);
        if(teachesDAO.infoList(teacher_id,course_id,section_id,semester, year).size()==0) {
            teachesDAO.append(teaches);
        }
        List<Map<String,String>> sec_time=StringUtil.parse_time_and_place(arrangement);
        //sec_arrangement
        for (Map<String,String> res:sec_time) {
            if("".equals(res.get("time_start_id"))) {
                assert (false);
                Timeslot timeslot=new Timeslot(res.get("start"),res.get("end"),res.get("day"));
                timeslotDAO.append(timeslot);
            } else {
                String time_end_id="";
                if("".equals(res.get("time_end_id"))) {
                    time_end_id=res.get("time_start_id");
                } else  {
                    time_end_id=res.get("time_end_id");
                }
                int start=Integer.parseInt(res.get("time_start_id"));
                int end=Integer.parseInt(time_end_id);
                String place=res.get("place");
                for (int i = start; i <=end ; i++) {
                    Sec_arrangement arrangement1=new Sec_arrangement(i+"",place,course_id,section_id,semester,year);
                    sec_arrangementDAO.append(arrangement1);
                }
            }
        }
        //exam_arrange
        if(exam_type.equals("paper")) {
            List<Map<String,String>> exam_arr=StringUtil.parse_time_and_place(exam_time);
            assert (exam_arr.size()==1&&exam_arr.get(0).get("place").equals(""));
            Paper paper=new Paper(exam_id,"paper",exam_arr.get(0).get("time_start_id"),"");
            paperDAO.append(paper);
        } else {
            Essay essay=new Essay(exam_id,"未补充");
            essayDAO.append(essay);
        }


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
