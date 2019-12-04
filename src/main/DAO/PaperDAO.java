package main.DAO;

import main.entity.Paper;

import java.util.List;
import java.util.Map;

public interface PaperDAO {
    int append(Paper paper);
    int delete(Paper paper);
    int modify(Paper paper);
    List<Map<String,String>> infoList(String exam_id);
}
