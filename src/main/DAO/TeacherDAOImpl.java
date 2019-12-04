package main.DAO;

import main.entity.Teacher;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public List<Map<String, String>> infoList(String teacher_id) {

        Connection connection=SqlUtil.createCon();
        try {
            String sql="select * from data.teacher where teacher_id ="+teacher_id;
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

    @Override
    public int append(Teacher teacher) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql= "INSERT  INTO `data`.`teacher` (teacher_id, teacher_name, enter_time, leave_time,teacher_dep) VALUES (?, ?, ?, ?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1,teacher.getTeacher_id());
            ppst.setString(2,teacher.getTeacher_name());
            ppst.setString(3,teacher.getEnter_time());
            ppst.setString(4,teacher.getLeave_time());
            ppst.setString(5,teacher.getDepartment());

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
    public int delete(String teacher_id) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`teacher` WHERE teacher_id='" + teacher_id + "'";
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
    public int modify(Teacher teacher) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql="UPDATE `data`.`teacher` SET ";
            String match="";
            if(StringUtil.isNotEmpty(teacher.getTeacher_name()))
                match+=", teacher_name='"+teacher.getTeacher_name()+"' ";
            if(StringUtil.isNotEmpty(teacher.getEnter_time()))
                match+=", enter_time='"+teacher.getEnter_time()+"' ";
            if(StringUtil.isNotEmpty(teacher.getLeave_time()))
                match+=", leave_time='"+teacher.getLeave_time()+"' ";
            if(StringUtil.isNotEmpty(teacher.getDepartment()))
                match+=", teacher_dep='"+teacher.getDepartment()+"' ";
            if(!match.isEmpty()) {
                sql+=match.substring(1);
            } else  {
                return 0;
            }
            sql+="WHERE teacher_id='"+teacher.getTeacher_id()+"'";
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
    private List<Map<String,String>> setReturn(ResultSet res) {
        try {
            List<Map<String,String>> result = new ArrayList<>();
            while (res.next()) {
                Map<String,String> map=new HashMap<>();
                map.put("teacher_id",res.getString("teacher_id"));
                map.put("teacher_name",res.getString("teacher_name"));
                map.put("enter_time",res.getString("enter_time"));
                map.put("leave_time",res.getString("leave_time"));
                map.put("department",res.getString("teacher_dep"));
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
