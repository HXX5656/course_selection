package main.DAO;

import main.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentDAO {
    int append(Department department);
    int delete(String dep_id);
    int modify(Department department);
    List<Map<String,String>> infoList(String dep_id);
    List<Map<String, String>> info_id(String name);
}
