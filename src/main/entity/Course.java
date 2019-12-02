package main.entity;

public class Course {
    private String course_id;
    private String course_name;
    private String credit;
    private String period;

    public Course(String course_id,String course_name,String credit,String period) {
        this.course_id=course_id;
        this.course_name=course_name;
        this.credit=credit;
        this.period=period;

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
}
