package main.DAO;

import main.entity.Timeslot;

import java.util.List;
import java.util.Map;

public interface TimeslotDAO {
    int append(Timeslot timeslot);
    int modify(Timeslot timeslot);
    List<Map<String,String>> infoList(String time_id);
    int delete(String time_id);
}
