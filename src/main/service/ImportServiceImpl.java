package main.service;

import com.google.gson.JsonObject;
import main.DAO.DAOFactory;
import main.DAO.StudentDAO;
import main.entity.Student;
import main.util.ExcelUtil;

import java.io.File;
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
            return deleteExcel(path);
        }
        catch (Exception e) {e.printStackTrace();}

        return -1;
    }
    private int deleteExcel(String path) throws IOException{

        try {
            File file=new File(path);
            if(!file.exists()) {
                return 0;
            } else  {
                file.delete();
                System.out.println("成功删除");
                return 0;
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
}
