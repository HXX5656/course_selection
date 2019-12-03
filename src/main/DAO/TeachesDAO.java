package main.DAO;

import main.entity.Teaches;

import java.util.List;
import java.util.Map;

public interface TeachesDAO {
    int append(Teaches teaches);
    int delete(String teacher_id,String course_id,String section_id,String semester,String year);
    int modify(Teaches teaches);
    List<Map<String,String>> infoList(String teacher_id,String course_id,String section_id,String semester,String year);
}
