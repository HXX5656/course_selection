package main.DAO;

import main.entity.Student;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAOImpl implements StudentDAO {


    @Override
    public int append(Student student) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql= "INSERT  INTO `data`.`student` (student_id, student_name, enter_time, gradu_time,student_dep) VALUES (?, ?, ?, ?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1,student.getStudent_id());
            ppst.setString(2,student.getStudent_name());
            ppst.setString(3,student.getEnter_time());
            ppst.setString(4,student.getGradu_time());
            ppst.setString(5,student.getDepartment());

            int ret=ppst.executeUpdate();
            SqlUtil.closeCon();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }
    }

    @Override
    public int delete(String student_id) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`student` WHERE student_id='" + student_id + "'";
            PreparedStatement ppst=connection.prepareStatement(sql);

            int ret = ppst.executeUpdate();
            SqlUtil.closeCon();
            return ret;
        }
        catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }
    }

    @Override
    public int modify(Student student) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql="UPDATE `data`.`student` SET ";
            String match="";
            if(StringUtil.isNotEmpty(student.getStudent_name()))
                match+=", student_name='"+student.getStudent_name()+"' ";
            if(StringUtil.isNotEmpty(student.getEnter_time()))
                match+=", enter_time='"+student.getEnter_time()+"' ";
            if(StringUtil.isNotEmpty(student.getGradu_time()))
                match+=", gradu_time='"+student.getGradu_time()+"' ";
            if(StringUtil.isNotEmpty(student.getDepartment()))
                match+=", student_dep='"+student.getDepartment()+"' ";
            if(!match.isEmpty()) {
                sql+=match.substring(1);
            }
            sql+="WHERE student_id='"+student.getStudent_id()+"'";
            PreparedStatement ppst=connection.prepareStatement(sql);
            int ret=ppst.executeUpdate();
            SqlUtil.closeCon();
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }
    }

    @Override
    public List<Map<String, String>> infoList(String student_id) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql="select * from data.student where student_id ="+student_id;
            PreparedStatement ppst=connection.prepareStatement(sql);
            ResultSet res=ppst.executeQuery();
            SqlUtil.closeCon();
            return setReturn(res);
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }

    private List<Map<String,String>> setReturn(ResultSet res) {
        try {
            List<Map<String,String>> result = new ArrayList<>();
            while (res.next()) {
                Map<String,String> map=new HashMap<>();
                map.put("student_id",res.getString("student_id"));
                map.put("student_name",res.getString("student_name"));
                map.put("enter_time",res.getString("enter_time"));
                map.put("gradu_time",res.getString("gradu_time"));
                map.put("department",res.getString("student_dep"));
                result.add(map);

            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }
}
