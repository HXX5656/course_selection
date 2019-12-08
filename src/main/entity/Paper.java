package main.entity;

public class Paper {
    String exam_id;
    String type;
    String time_slot_id;
    String room_id;

    public Paper(String exam_id, String type, String time_slot_id, String room_id) {
        this.exam_id = exam_id;
        this.type = type;
        this.time_slot_id = time_slot_id;
        this.room_id = room_id;
    }

    public Paper() {
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime_slot_id() {
        return time_slot_id;
    }

    public void setTime_slot_id(String time_slot_id) {
        this.time_slot_id = time_slot_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
