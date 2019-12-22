package main.DAO;

import main.entity.Takes;
import java.util.List;
import java.util.Map;

public interface TakesDAO {
    int append(Takes takes);
    int modify(Takes takes);
    List<Map<String,String>> infoList(String student_id,String course_id,String section_id,String semster,String year);
    List<Map<String,String>> studentInfoList(String course_id,String section_id,String semster,String year);
    int delete(Takes takes);
    List<Map<String,String>> get_taken_course(String student_id);
}
