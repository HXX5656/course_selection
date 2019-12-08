package main.entity;

public class Teacher {
    private String teacher_id;
    private String teacher_name;
    private String enter_time;
    private String leave_time;
    private String department;

    public Teacher(String teacher_id, String teacher_name, String enter_time, String leave_time, String department) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.enter_time = enter_time;
        this.leave_time = leave_time;
        this.department = department;
    }

    public Teacher() {
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getEnter_time() {
        return enter_time;
    }

    public void setEnter_time(String enter_time) {
        this.enter_time = enter_time;
    }

    public String getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(String leave_time) {
        this.leave_time = leave_time;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}