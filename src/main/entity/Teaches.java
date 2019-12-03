package main.entity;

public class Teaches {
    private String teacher_id;
    private String course_id;
    private String section_id;
    private String semster;
    private String year;

    public Teaches(String teacher_id, String course_id, String section_id, String semster, String year) {
        this.teacher_id = teacher_id;
        this.course_id = course_id;
        this.section_id = section_id;
        this.semster = semster;
        this.year = year;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getSection_id() {
        return section_id;
    }

    public String getSemster() {
        return semster;
    }

    public String getYear() {
        return year;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public void setSemster(String semster) {
        this.semster = semster;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
