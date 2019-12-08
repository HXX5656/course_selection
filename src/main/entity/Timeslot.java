package main.entity;

import main.util.StringUtil;

public class Timeslot {
    private String time_id;
    private String start_time;
    private String end_time;
    private String day_of_week;

    public Timeslot(String time_id, String start_time, String end_time, String day_of_week) {
        this.time_id = time_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.day_of_week = day_of_week;
    }

    public Timeslot() {
    }

    public Timeslot(String start_time, String end_time, String day_of_week) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.day_of_week = day_of_week;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }
}
