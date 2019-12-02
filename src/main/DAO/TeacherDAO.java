package main.DAO;

import main.entity.Course;
import main.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherDAO {
    int append(Teacher teacher);
    int delete(String teacher_id);
    int modify(Teacher teacher);
    List<Map<String,String>> infoList(String teacher_id);
}
