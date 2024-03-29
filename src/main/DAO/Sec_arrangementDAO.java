package main.DAO;

import main.entity.Sec_arrangement;

import java.util.List;
import java.util.Map;

public interface Sec_arrangementDAO {
    int append(Sec_arrangement sec_arrangement);
    int modify(Sec_arrangement sec_arrangement);
    List<Map<String, String>> infoList(String time_slot_id, String room_id);
    int delete(String time_slot_id, String room_id);
    List<String> findRoom(String course_id, String section_id, String semester, String year);
    int delete_by_section(String course_id,String section_id,String semster,String year);
    List<Integer> find_time(String course_id,String section_id,String semster,String year);
    List<Map<String,String>> getArrangements(String course_id, String section_id, String semester, String year);
    List<Map<String, String>> infoList(String time_slot_id, String course_id,String section_id,String semester,String year);

    }
