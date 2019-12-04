package main.DAO;

import main.entity.Sec_arrangement;

import java.util.List;
import java.util.Map;

public interface Sec_arrangementDAO {
    int append(Sec_arrangement sec_arrangement);
    int modify(Sec_arrangement sec_arrangement);
    List<Map<String,String>> infoList(String time_slot_id,String room_id);
    int delete(String time_slot_id,String room_id);
}
