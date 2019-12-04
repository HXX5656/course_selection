package main.entity;

public class Exam {
    private String exam_id;
    private String exam_week;

    public Exam(String exam_id, String exam_week) {
        this.exam_id = exam_id;
        this.exam_week = exam_week;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_week() {
        return exam_week;
    }

    public void setExam_week(String exam_week) {
        this.exam_week = exam_week;
    }
}
