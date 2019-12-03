package main.entity;

public class Section {
    private String course_id;
    private String section_id;
    private String semester;
    private String year;
    private String start;
    private String end;
    private String max;
    private String exam_id;

    public Section(String course_id, String section_id, String semester, String year, String start, String end, String max, String exam_id) {
        this.course_id = course_id;
        this.section_id = section_id;
        this.semester = semester;
        this.year = year;
        this.start = start;
        this.end = end;
        this.max = max;
        this.exam_id = exam_id;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }
}
