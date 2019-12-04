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
}
