package main.service;

import com.google.gson.JsonObject;
import main.DAO.*;
import main.entity.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseService {
    private JsonObject jsonObject;
    private TakesDAO takesDAO;
    private CancelDAO cancelDAO;
    private ApplicationDAO applicationDAO;


    public CourseServiceImpl(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.takesDAO = DAOFactory.getTakesDAOInstance();
        this.cancelDAO = DAOFactory.getCancelDAOINstance();
        this.applicationDAO = DAOFactory.getApplicationDAOInstance();
    }

    /*
    * 实现学生选退课函数
    * 注意点：表之间的约束关系
    * 测试例子：学生1选上课A，又退了A，又选上A
    * 预期结果：第一步操作之后选课表有学生1和a，第二步操作后退课表里有，选课表里没有，第三步操作后选课表里有，退课表里没有
    * 上面的课指的是开课实例而不是课程
    * 实现思路：根据逻辑调用选课和退课的dao*/
    @Override
    public String select_or_cancel() {

        String student_id = jsonObject.get("student_id").getAsString();
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();

        if (!check_sec_time().equals("no")){
            return check_sec_time();
        }

        if (!chec_exam_time().equals("no")){
            return chec_exam_time();
        }

        if (isRoomMax()) {
            return "0";
        }

        if (isSecMax()) {
            return "0";
        }

        if (takesDAO.infoList(student_id, course_id, section_id, semester, year).size() == 0) {
            int i = takesDAO.append(new Takes(student_id, course_id, section_id, semester, year, null));
            cancelDAO.delete(new Cancel(student_id, course_id, section_id, semester, year));
            auto();//自动检查
            if (!getApp_id().equals("0")) {
                applicationDAO.modify(new Application(getApp_id(), null, "-2", null, null, null, null, null, null));
            }
            return ""+i;
        } else {

            if (!isCanceled()) {
                int i = cancelDAO.append(new Cancel(student_id, course_id, section_id, semester, year));
                takesDAO.delete(new Takes(student_id, course_id, section_id, semester, year, null));
                auto();
                return ""+i;
            }else {
                return "0";
            }
        }
    }

    /*TODO：申请函数逻辑完善
    * 已经选上的课不能再申请
    * 退了的课不能再申请
    * 已经提交过的申请不能再申请
    *
    * 建议实现思路：1。我在申请和取消的dao里各新增了函数，建议使用
    * 2。条件判断 已经选上的课；已经退了的课；申请表中已有记录的课（通过学生和开课主码来确定） 不能再申请
    * 注：如果选用了自动驳回特别处理的方式，这里需要注意一下，见下一个函数注释*/
    @Override
    public int apply() {
        String student_id = jsonObject.get("student_id").getAsString();
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();
        String reason = jsonObject.get("reason").getAsString();
        String date = jsonObject.get("date").getAsString();
        boolean notApplied = (applicationDAO.info_id(student_id, course_id, section_id, semester, year) == 0);

        if (!isTaken()&&!isCanceled()&& notApplied) {
            Application application = new Application("", reason, "0", date, student_id, course_id, section_id, semester, year);
            applicationDAO.append(application);
            return 1;
        } else {
            return 0;
        }
    }

    /*
    * TODO：完善逻辑：同意申请之后应该加到学生选课表中去
    * 注意点：在加入选课表之前要判断表中是否已有该记录
    * 比如：学生提交申请后，又通过正常选课选上了这门课，应该将申请自动驳回
    * 实现建议：takes的dao里可以添加函数判断是否存在，也可以直接用之前的查的函数
    * 另：我的看法：我认为需要区别自动驳回和教师驳回
    * 按理说，教师驳回之后应该是不能再申请的，但是若是系统因到达教室人数上限而自动驳回，学生应该还有再申请的权利
    * 比如：教室容纳20人，选满之后，学生a的申请被自动驳回，但是之后陆续有人退课，a应该还是可以继续申请的吧？
    * 建议实现：教师驳回设置状态为-1，自动驳回设置状态为-2，使用不同函数实现
    * 这只是我的看法，你也可以简单实现驳回后就不能再申请，决定之后微信告知我一下就好*/
    @Override
    public String confirm_apply() {
        //if(!TimeControlService.getInstance().isCanSelect())
         //   return "-2";
        Application application = new Application(getApp_id(), "", "", "", "", "", "", "", "");
        if (isTaken()) {
            return "taken";
        }

        if(!check_sec_time().equals("no")){
            return check_sec_time();
        }

        if (isCanceled()) {
            return "caneceled";
        }

        if(isRoomMax()){
            return "room max";
        }

        String student_id = jsonObject.get("student_id").getAsString();
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();

        application.setStatus("1");
        int applied = applicationDAO.modify(application);

        int taken = takesDAO.append(new Takes(student_id,course_id,section_id,semester,year,null));

        if (applied == 1 && taken == 1) {
            return "1";
        } else {
            return "0";
        }


    }

    @Override
    public int refuse_apply() {//手动
        if(!TimeControlService.getInstance().isCanSelect())
            return -2;
        Application application = new Application(getApp_id(), "", "", "", "", "", "", "", "");
        application.setStatus("-1");
        return applicationDAO.modify(application);

    }

    @Override
    public int delete_section(){
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();
        SectionDAO sectionDAO = DAOFactory.getSectionDAOInstance();
        List<Map<String,String>> infoList = sectionDAO.infoList(course_id,section_id,semester,year);
        if (infoList.size() == 0){//开课不存在就返回0
            return 0;
        }

        cancelDAO.delete_by_section(course_id,section_id,semester,year);
        takesDAO.delete_by_section(course_id,section_id,semester,year);
        TeachesDAO teachesDAO = DAOFactory.getTeachesDAOInstance();
        teachesDAO.delete_by_section(course_id,section_id,semester,year);
        String exam_id = infoList.get(0).get("exam_id");
        EssayDAO essayDAO = DAOFactory.getEssayDAOInstance();
        essayDAO.delete(exam_id);
        PaperDAO paperDAO = DAOFactory.getPaperDAOInstance();
        paperDAO.delete_by_examID(exam_id);
        ExamDAO examDAO = DAOFactory.getExamDAOInstance();
        examDAO.delete(exam_id);
        Sec_arrangementDAO sec_arrangementDAO = DAOFactory.getSecArrangementDAOInstance();
        sec_arrangementDAO.delete_by_section(course_id,section_id,semester,year);
        applicationDAO.delete(getApp_id());
        sectionDAO.delete(course_id,section_id,semester,year);
        return 1;
    }



    private String getApp_id() {
        String student_id = jsonObject.get("student_id").getAsString();
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();
        int app_id = applicationDAO.info_id(student_id, course_id, section_id, semester, year);
        return app_id >= 1 ? app_id + "" : "0";
    }

    private boolean isTaken() {
        String student_id = jsonObject.get("student_id").getAsString();
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();


        if (takesDAO.infoList(student_id, course_id, section_id, semester, year).size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCanceled() {
        String student_id = jsonObject.get("student_id").getAsString();
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();

        if (cancelDAO.info_exist(student_id, course_id, section_id, semester, year) == 1) {
            return true;
        } else {
            return false;
        }
    }



    private int auto() {//自动驳回教室满了的
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();

        List<Map<String, String>> list = applicationDAO.studentInfoList(course_id, section_id, semester, year);
        if (list.size() == 0) {
            return 0;
        }
        int n = 0;//返回改动数量
        if (isRoomMax()) {
            for (int i = 0; i < list.size(); i++) {//待处理变自动驳回
                if (list.get(i).get("status").equals("0")) {
                    n += applicationDAO.modify(new Application(list.get(i).get("app_id"), null, "-2", null, null, null, null, null, null));
                }
            }
            return n;
        } else {
            for (int i = 0; i < list.size(); i++) {//自动驳回变待处理
                if (list.get(i).get("status").equals("-2")) {
                    n += applicationDAO.modify(new Application(list.get(i).get("app_id"), null, "0", null, null, null, null, null, null));
                }
            }
            return n;
        }
    }

    private boolean isRoomMax() {
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();
        Sec_arrangementDAO sec_arrangementDAO = DAOFactory.getSecArrangementDAOInstance();
        List<String> stringList = sec_arrangementDAO.findRoom(course_id, section_id, semester, year);
        ClassroomDAO classroomDAO = DAOFactory.getClassroomDAOInstance();

        int number = takesDAO.studentInfoList(course_id, section_id, semester, year).size();
        for (int i = 0; i < stringList.size(); i++) {
            if (number >= Integer.parseInt(classroomDAO.max(stringList.get(i)))) {
                return true;
            }
        }
        return false;
    }

    private boolean isSecMax() {
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();
        SectionDAO sectionDAO = DAOFactory.getSectionDAOInstance();
        String max = sectionDAO.infoList(course_id, section_id, semester, year).get(0).get("max");
        int number = takesDAO.studentInfoList(course_id, section_id, semester, year).size();
        int sec_max = Integer.parseInt(max);
        return number >= sec_max;
    }

    private String check_sec_time(){
        String student_id = jsonObject.get("student_id").getAsString();
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();

        Sec_arrangementDAO sec_arrangementDAO = DAOFactory.getSecArrangementDAOInstance();
        List<Map<String,String>> studentTakes = takesDAO.findByStudent(student_id);
        if (studentTakes == null || studentTakes.size() == 0){
            return "no";
        }
        List<Integer> takesTime = new ArrayList<Integer>();
        for(int i = 0;i < studentTakes.size();i ++){
            String temp_course = studentTakes.get(i).get("course_id");
            String temp_section = studentTakes.get(i).get("section_id");
            String temp_semester = studentTakes.get(i).get("semsester");
            String temp_year = studentTakes.get(i).get("year");
            int temp = sec_arrangementDAO.find_time(temp_course,temp_section,temp_semester,temp_year);
            takesTime.add(temp);
        }
        int this_time = 0;

        this_time = sec_arrangementDAO.find_time(course_id,section_id,semester,year);
        for (int i = 0;i < takesTime.size();i ++){
            if(takesTime.get(i) == this_time){
                return studentTakes.get(i).get("course_id")+","+studentTakes.get(i).get("section_id")+","+studentTakes.get(i).get("semester")+","+studentTakes.get(i).get("year");
            }
        }
        return "no";
    }

    private String chec_exam_time(){
        String student_id = jsonObject.get("student_id").getAsString();
        String course_id = jsonObject.get("course_id").getAsString();
        String section_id = jsonObject.get("section_id").getAsString();
        String semester = jsonObject.get("semester").getAsString();
        String year = jsonObject.get("year").getAsString();
        SectionDAO sectionDAO =DAOFactory.getSectionDAOInstance();
        String this_exam = sectionDAO.infoList(course_id,section_id,semester,year).get(0).get("exam_id");

        List<String> examTimesTaken = new ArrayList<String>();

        List<Map<String,String>> studentTakes = takesDAO.findByStudent(student_id);
        if (studentTakes == null || studentTakes.size() == 0){
            return "no";
        }
        for(int i = 0;i < studentTakes.size();i ++){
            String temp_course = studentTakes.get(i).get("course_id");
            String temp_section = studentTakes.get(i).get("section_id");
            String temp_semester = studentTakes.get(i).get("semsester");
            String temp_year = studentTakes.get(i).get("year");
            examTimesTaken.add(sectionDAO.infoList(temp_course,temp_section,temp_semester,temp_year).get(0).get("exam_id"));

        }
        for (int i = 0;i < examTimesTaken.size();i ++){
            if (examTimesTaken.get(i).equals(this_exam)){
                return examTimesTaken.get(i);
            }
        }
        return "no";
    }

}
