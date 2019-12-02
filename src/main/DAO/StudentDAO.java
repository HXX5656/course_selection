package main.DAO;
import java.util.List;
import java.util.Map;
import main.entity.Student;
public interface StudentDAO {
    int append(Student student);
    int delete(String student_id);
    int modify(Student student);
    List<Map<String,String>> infoList(String student_id);
}
