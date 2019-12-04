package main.service;

import com.google.gson.JsonObject;
import main.DAO.DAOFactory;
import main.DAO.StudentDAO;
import main.entity.Student;
import main.util.ExcelUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ImportServiceImpl implements ImportService {
    private JsonObject jsonObject;
    private StudentDAO studentDAO;
    public ImportServiceImpl(JsonObject param) {
        this.jsonObject=param;
        this.studentDAO= DAOFactory.getStudentDAOInstance();
    }


    @Override
    public int importStudent() throws IOException {
        String path=(jsonObject==null)?"src/main/service/student.xlsx":jsonObject.get("filePath").getAsString();
        //String realPath = path.substring(path.lastIndexOf("\\")+1);
        try {
            List<Student> list=ExcelUtil.parseFromExcel(path, Student.class);
            for (Student s:list) {
                studentDAO.append(s);
            }
        }
        catch (Exception e) {e.printStackTrace();}

        return 0;
    }
}
