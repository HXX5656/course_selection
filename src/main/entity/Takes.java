package main.entity;

public class Takes {
    private String student_id;
    private String course_id;
    private String section_id;
    private String semester;
    private String year;
    private String grade;

    public Takes(String student_id, String course_id, String section_id, String semester, String year, String grade) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.section_id = section_id;
        this.semester = semester;
        this.year = year;
        this.grade = grade;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
