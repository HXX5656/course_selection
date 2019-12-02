package main.entity;

public class Course {
    private String course_id;
    private String course_name;
    private String credit;
    private String period;
    private String department;

    public Course(String course_id,String course_name,String credit,String period,String department) {
        this.course_id=course_id;
        this.course_name=course_name;
        this.credit=credit;
        this.period=period;
        this.department=department;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
