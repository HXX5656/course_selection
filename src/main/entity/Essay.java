package main.entity;

public class Essay {
    private String exam_id;
    private String demand;

    public Essay(String exam_id, String demand) {
        this.exam_id = exam_id;
        this.demand = demand;
    }

    public Essay() {
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }
}
