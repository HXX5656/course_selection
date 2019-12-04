package main.entity;

public class Application {
    private String app_id;
    private String reason;
    private String status;
    private String app_time;
    private String student_id;
    private String course_id;
    private String section_id;
    private String semester;
    private String year;

    public Application(String app_id, String reason, String status, String app_time, String student_id, String course_id, String section_id, String semester, String year) {
        this.app_id = app_id;
        this.reason = reason;
        this.status = status;
        this.app_time = app_time;
        this.student_id = student_id;
        this.course_id = course_id;
        this.section_id = section_id;
        this.semester = semester;
        this.year = year;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApp_time() {
        return app_time;
    }

    public void setApp_time(String app_time) {
        this.app_time = app_time;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String studeng_id) {
        this.student_id = studeng_id;
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
}
