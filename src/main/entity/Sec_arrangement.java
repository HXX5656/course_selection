package main.entity;

public class Sec_arrangement {
    String time_slot_id;
    String room_id;
    String course_id;
    String section_id;
    String semester;
    String year;


    public Sec_arrangement(String time_slot_id, String room_id, String course_id, String section_id, String semester, String year) {
        this.time_slot_id = time_slot_id;
        this.room_id = room_id;
        this.course_id = course_id;
        this.section_id = section_id;
        this.semester = semester;
        this.year = year;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
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

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
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
