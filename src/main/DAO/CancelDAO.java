package main.DAO;

import main.entity.Cancel;
import main.entity.Takes;

import java.util.List;
import java.util.Map;

public interface CancelDAO {
    int append(Cancel cancel);
    int modify(Cancel cancel);
    //List<Map<String,String>> infoList(String student_id, String course_id, String section_id, String semester, String year);
    int delete(Cancel cancel);
    int info_exist(String student_id, String course_id, String section_id, String semester, String year);
}
