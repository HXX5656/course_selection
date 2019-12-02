package main.DAO;

import main.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseDAO {
    int append(Course course);
    int delete(String course_id);
    int modify(Course course);
    List<Map<String,String>> infoList(String course_id);
}
