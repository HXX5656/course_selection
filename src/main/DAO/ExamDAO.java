package main.DAO;

import main.entity.Exam;

import java.util.List;
import java.util.Map;

public interface ExamDAO {
    int append(Exam exam);
    int delete(String exam_id);
    int modify(Exam exam);
    List<Map<String,String>> infoList(String exam_id);
}
