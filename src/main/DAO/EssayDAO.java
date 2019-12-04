package main.DAO;

import main.entity.Essay;

import java.util.List;
import java.util.Map;

public interface EssayDAO {
    int append(Essay essay);
    int delete(String exam_id);
    int modify(Essay essay);
    List<Map<String,String>> infoList(String exam_id);

}
