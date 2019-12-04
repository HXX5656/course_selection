package main.entity;

public class Student {
    private String student_id;
    private String student_name;
    private String enter_time;
    private String gradu_time;
    private String department;


    public Student(String student_id, String student_name, String enter_time, String gradu_time, String department) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.enter_time = enter_time;
        this.gradu_time = gradu_time;
        this.department = department;
    }

    public Student() {
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getEnter_time() {
        return enter_time;
    }

    public String getGradu_time() {
        return gradu_time;
    }

    public String getDepartment() {
        return department;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setEnter_time(String enter_time) {
        this.enter_time = enter_time;
    }

    public void setGradu_time(String gradu_time) {
        this.gradu_time = gradu_time;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
