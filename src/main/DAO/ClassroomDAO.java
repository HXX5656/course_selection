package main.DAO;

import main.entity.Classroom;

import java.util.List;
import java.util.Map;

public interface ClassroomDAO {
    int append(Classroom classroom);
    int delete(String room_id);
    int modify(Classroom classroom);
    List<Map<String,String>> infoList(String capacity);
}
