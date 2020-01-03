package main.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import main.DAO.*;
import main.entity.Takes;
import main.util.StringUtil;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private JsonObject jsonObject;
    private TakesDAO takesDAO;
    private CourseDAO courseDAO;
    private DepartmentDAO departmentDAO;
    private SectionDAO sectionDAO;
    private Sec_arrangementDAO sec_arrangementDAO;
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    private ApplicationDAO applicationDAO;
    private PaperDAO paperDAO;
    private EssayDAO essayDAO;
    private TimeslotDAO timeslotDAO;
    private TeachesDAO teachesDAO;

    public UserServiceImpl(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.takesDAO = DAOFactory.getTakesDAOInstance();
        this.courseDAO = DAOFactory.getCourseDAOInstance();
        this.departmentDAO = DAOFactory.getDepartmentDAOInstance();
        this.sectionDAO = DAOFactory.getSectionDAOInstance();
        this.sec_arrangementDAO = DAOFactory.getSecArrangementDAOInstance();
        this.studentDAO = DAOFactory.getStudentDAOInstance();
        this.teacherDAO = DAOFactory.getTeacherDAOInstance();
        this.applicationDAO = DAOFactory.getApplicationDAOInstance();
        this.paperDAO = DAOFactory.getPaperDAOInstance();
        this.essayDAO = DAOFactory.getEssayDAOInstance();
        this.timeslotDAO = DAOFactory.getTimeslotDAOInstance();
        this.teachesDAO = DAOFactory.getTeachesDAOInstance();
    }
    @Override
    public JsonObject get_student_exam(){
        JsonObject json = new JsonObject();
        String s_id = jsonObject.get("student_id").getAsString();
        JsonObject datas = get_student_courseInfo();
        for(int i = 0; i < datas.size() - 1;i ++){
            JsonObject temp = datas.getAsJsonObject(""+i);
            JsonObject j = new JsonObject();
            List<Map<String,String>> list =  sectionDAO.infoList(temp.get("course_id").getAsString(),temp.get("section_id").getAsString(),temp.get("semester").getAsString(),temp.get("year").getAsString());
            j.addProperty("exam_id",list.get(0).get("exam_id"));
            String id = j.get("exam_id").getAsString();
            String type = "";
            String value = "";
            if(paperDAO.infoList(id).size()>0){
                type="笔试";
                String time_id = paperDAO.infoList(id).get(0).get("time_slot_id");
                String room_id = paperDAO.infoList(id).get(0).get("room_id");
                Map<String,String> time = timeslotDAO.infoList(time_id).get(0);
                int day = (Integer.parseInt(time_id) - 96)/5 + 1;
                String start=time.get("start_time");
                String end = time.get("end_time");
                value="周"+day+" "+start+"-"+end+" H"+room_id+";";
            } else {
                type = "论文";
                value = essayDAO.infoList(id).get(0).get("demand");
            }
            j.addProperty("type",type);
            j.addProperty("value",value);
            json.add(i+"",j);
        }
        return json;

    }
    @Override
    public JsonObject get_student_courseInfo() {
        String s_id = jsonObject.get("student_id").getAsString();
        List<Map<String,String>> courses = takesDAO.get_taken_course(s_id);
        List<Map<String,String>> tmp;
        JsonObject jsons = new JsonObject();
        int index = 0;
        tmp = studentDAO.infoList(s_id);
        if(tmp.size()==1) {
            jsons.addProperty("student_name",tmp.get(0).get("student_name"));
        } else {
            assert (false);
        }
        for (Map<String,String> map:courses) {
//            Gson gson=new Gson();
//            JsonObject jj =gson.toJson(map);
            JsonObject j = new JsonObject();
            j.addProperty("student_id",s_id);
            j.addProperty("grade",map.get("grade"));
            String course_id = map.get("course_id");
            String section_id = map.get("section_id");
            String semester = map.get("semester");
            String year = map.get("year");
            j.addProperty("course_id",course_id);
            j.addProperty("section_id",section_id);
            j.addProperty("semester",semester);
            j.addProperty("year",year);
            tmp = courseDAO.infoList(course_id);
            if (tmp.size() == 1) {
                Map<String,String> course_info = tmp.get(0);
                j.addProperty("course_name",course_info.get("course_name"));
                j.addProperty("course_credit", course_info.get("credit"));
                j.addProperty("course_period",course_info.get("period"));
                String course_dep = course_info.get("department");
                tmp = departmentDAO.infoList(course_dep);
                if(tmp.size() == 1) {
                    j.addProperty("course_code", StringUtil.create_course_code(course_id,
                            tmp.get(0).get("dep_name"),section_id));
                    //选课人数，上课时间和地点等等
                    tmp = takesDAO.studentInfoList(course_id,section_id,semester,year);
                    j.addProperty("selected_persons",tmp.size()+"");
                    tmp = sectionDAO.infoList(course_id,section_id,semester,year);
                    if(tmp.size() == 1) {
                        j.addProperty("max_members",tmp.get(0).get("max")+"");
                        tmp = sec_arrangementDAO.getArrangements(course_id,section_id,semester,year);
                        JsonObject jj = new JsonObject();
                        int count = 0;
                        for (Map<String,String> entry:tmp) {
                            JsonObject arran = new JsonObject();
                            String id = entry.get("time_slot_id");
                            Map<String,Integer> time = StringUtil.getStep(id);
                            arran.addProperty("step",time.get("step")+"");
                            arran.addProperty("day",time.get("day")+"");
                            arran.addProperty("room",entry.get("room_id"));
                            jj.add(count+"",arran);
                            count+=1;
                        }
                        j.add("time_place",jj);
                        jsons.add(index+"",j);
                        index+=1;
                        continue;

                    }

                }
            }
            assert (false);
        }
        return jsons;
    }
    @Override
    public JsonObject get_teacher_info() {
        String id = jsonObject.get("teacher_id").getAsString();
        List<Map<String,String>> tmp=teacherDAO.infoList(id);
        JsonObject json = new JsonObject();
        String status="";
        if(tmp.size()==1) {
            json.addProperty("teacher_id",tmp.get(0).get("teacher_id"));
            json.addProperty("teacher_name",tmp.get(0).get("teacher_name"));
            json.addProperty("enter_time",tmp.get(0).get("enter_time"));
            tmp = departmentDAO.infoList(tmp.get(0).get("department"));
            if(tmp.size() == 1) {
                json.addProperty("department",tmp.get(0).get("dep_name"));
                status = "success";
            } else {
                status += "该院系不存在";
            }
        } else {
            status+="该教师不存在";
        }
        json.addProperty("status",status);
        return json;
    }
    @Override
    public JsonObject showStudents() {
        String course_code = jsonObject.get("course_code").getAsString();
        Map<String,String> code = StringUtil.parse_course_code(course_code);
        String course_id = code.get("course_id");
        String section_id = code.get("section_id");
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();
        JsonObject jsons = new JsonObject();
        List<Map<String,String>> tmp = takesDAO.studentInfoList(course_id,section_id,semester,year);
        int index = 0;
        for (Map<String,String> map:tmp) {
            JsonObject j=new JsonObject();
            List<Map<String,String>> tmp_tmp = studentDAO.infoList(map.get("student_id"));
            if(tmp_tmp.size()==1) {
                j.addProperty("student_id",map.get("student_id"));
                j.addProperty("student_name",tmp_tmp.get(0).get("student_name"));
                jsons.add(index+"",j);
                index+=1;
                continue;

            }
            assert (false);
        }
        return jsons;
    }
    @Override
    public JsonObject generate_students() {
        JsonObject jsons = new JsonObject();
        List<Map<String,String>> tmp = studentDAO.allList();
        int index = 0;
        for (Map<String,String> map:tmp) {
            JsonObject j=new JsonObject();
            j.addProperty("student_id",map.get("student_id"));
            j.addProperty("student_name",map.get("student_name"));
            j.addProperty("enter_time",map.get("enter_time"));
            j.addProperty("gradu_time",map.get("gradu_time"));
            j.addProperty("department",map.get("department"));
            jsons.add(index+"",j);
            index+=1;
        }
        return jsons;
    }
    public JsonObject generate_courses() {
        List<Map<String,String>> courses = sectionDAO.allList();
        List<Map<String,String>> tmp;
        JsonObject jsons = new JsonObject();
        int index = 0;
        for (Map<String,String> map:courses) {
            JsonObject j = new JsonObject();
//            j.addProperty("teacher_id",teacher_id);
            String course_id = map.get("course_id");
            String section_id = map.get("section_id");
            String semester = map.get("semester");
            String year = map.get("year");
            j.addProperty("course_id",course_id);
            j.addProperty("section_id",section_id);
            j.addProperty("year",year);
            j.addProperty("semester",semester);
            tmp = courseDAO.infoList(course_id);
            if (tmp.size() == 1) {
                Map<String,String> course_info = tmp.get(0);
                j.addProperty("course_name",course_info.get("course_name"));
                j.addProperty("course_credit", course_info.get("credit"));
                j.addProperty("course_period",course_info.get("period"));
                String course_dep = course_info.get("department");
                tmp = departmentDAO.infoList(course_dep);
                if(tmp.size() == 1) {
                    j.addProperty("course_code", StringUtil.create_course_code(course_id,
                            tmp.get(0).get("dep_name"),section_id));
                    //选课人数，上课时间和地点等等
                    tmp = takesDAO.studentInfoList(course_id,section_id,semester,year);
                    j.addProperty("selected_persons",tmp.size()+"");
                    tmp = sectionDAO.infoList(course_id,section_id,semester,year);
                    if(tmp.size() == 1) {
                        if(paperDAO.infoList(tmp.get(0).get("exam_id")).size() == 0){
                            j.addProperty("exam_time","");
                            j.addProperty("exam_type","essay");
                        } else {
                            j.addProperty("exam_type","paper");
                            List<Map<String,String>> t_temp = paperDAO.infoList(tmp.get(0).get("exam_id"));
                            if(t_temp.size() == 1) {
                                String time_so_id = t_temp.get(0).get("time_slot_id");
                                List<Map<String,String>> any = timeslotDAO.infoList(time_so_id);
                                if(any.size()==1) {
                                    j.addProperty("exam_time","周"+any.get(0).get("day_of_week")+" "
                                            +any.get(0).get("start_time")+"-"+any.get(0).get("end_time")+" H"+t_temp.get(0).get("room_id"));
                                } else {
                                    assert (false);

                                }
                            } else {
                                assert (false);
                            }

                        }
                        j.addProperty("max_members",tmp.get(0).get("max")+"");
                        tmp = sec_arrangementDAO.getArrangements(course_id,section_id,semester,year);
                        JsonObject jj = new JsonObject();
                        int count = 0;
                        for (Map<String,String> entry:tmp) {
                            JsonObject arran = new JsonObject();
                            String id = entry.get("time_slot_id");
                            Map<String,Integer> time = StringUtil.getStep(id);
                            arran.addProperty("day",time.get("day")+"");
                            arran.addProperty("room",entry.get("room_id"));
                            arran.addProperty("step",time.get("step")+"");
                            jj.add(count+"",arran);
                            count+=1;
                        }
                        j.add("time_place",jj);
                        List<Map<String,String>> teacher_size=teachesDAO.get_teacher_id(course_id,section_id,semester,year);
                        if(teacher_size.size()!=1){
                            assert (false);
                        }
                        j.addProperty("teacher_id",teacher_size.get(0).get("teacher_id"));
                        jsons.add(index+"",j);
                        index+=1;
                        continue;

                    }

                }
            }
            assert (false);
        }
        return jsons;
    }
    @Override
    public JsonObject generate_teachers() {
        JsonObject jsons = new JsonObject();
        List<Map<String,String>> tmp = teacherDAO.allList();
        int index = 0;
        for (Map<String,String> map:tmp) {
            JsonObject j=new JsonObject();
            j.addProperty("teacher_id",map.get("teacher_id"));
            j.addProperty("teacher_name",map.get("teacher_name"));
            j.addProperty("enter_time",map.get("enter_time"));
            j.addProperty("leave_time",map.get("leave_time"));
            j.addProperty("department",map.get("department"));
            jsons.add(index+"",j);
            index+=1;
        }
        return jsons;
    }
    @Override
    public JsonObject student_applications() {
        String student_name = jsonObject.get("student_id").getAsString();
        List<Map<String,String>> applications = applicationDAO.studentAppList(student_name);
        JsonObject jsons = new JsonObject();
        for (Map<String,String> map:applications) {
            JsonObject j = new JsonObject();
            String course_id = map.get("course_id");
            String section_id = map.get("section_id");
            String semester = map.get("semester");
            String year = map.get("year");
            String course_code = course_id+"."+section_id;
            j.addProperty("course_code",course_code);
            j.addProperty("semester",semester);
            j.addProperty("year",year);
            j.addProperty("reason",map.get("reason"));
            j.addProperty("status",map.get("status"));
            j.addProperty("apply_time",map.get("apply_time"));
            j.addProperty("student_id",map.get("student_id"));
            jsons.add(map.get("app_id"),j);
        }
        return jsons;
    }
    @Override
    public JsonObject showApplications() {
        int len = jsonObject.size();
        JsonObject jsons = new JsonObject();
        for (int i=0;i<len-1;i++) {
            Map<String,String> tmp=StringUtil.parse_code(jsonObject.get(i+"").getAsString());
            String course_id = tmp.get("course_id");
            String section_id = tmp.get("section_id");
            String semester = tmp.get("semester");
            String year = tmp.get("year");
            String course_code = jsonObject.get(i+"").getAsString().substring(7);
            List<Map<String,String>> cr_aps = applicationDAO.studentInfoList(course_id,section_id,semester,year);
            for(Map<String,String> map:cr_aps) {
                JsonObject j = new JsonObject();
//                j.addProperty("app_id",map.get("app_id"));
                j.addProperty("course_code",course_code);
                j.addProperty("semester",semester);
                j.addProperty("year",year);
                j.addProperty("reason",map.get("reason"));
                j.addProperty("status",map.get("status"));
                j.addProperty("apply_time",map.get("apply_time"));
                j.addProperty("student_id",map.get("student_id"));
                jsons.add(map.get("app_id"),j);
            }
        }
        return jsons;
    }
}
