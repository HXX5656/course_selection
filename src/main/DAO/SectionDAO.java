package main.DAO;
import main.entity.Section;

import java.util.List;
import java.util.Map;
public interface SectionDAO {
    int append(Section section);
    int delete(String couse_id,String section_id,String semester,String year);
    int modify(Section section);
    List<Map<String,String>> infoList(String couse_id,String section_id,String semester,String year);
}
