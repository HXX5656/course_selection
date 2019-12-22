package main.DAO;

import main.entity.Application;
import main.entity.Course;

import java.util.List;
import java.util.Map;

public interface ApplicationDAO {
    int append(Application application);
    int delete(String app_id);
    int modify(Application application);
    List<Map<String,String>> infoList(String app_id);
    List<Map<String,String>> studentInfoList(String course_id,String section_id,String semester,String year);
    int info_id(String student_id, String course_id, String section_id, String semster, String year);
    List<Map<String, String>> studentAppList(String student_id);
}
