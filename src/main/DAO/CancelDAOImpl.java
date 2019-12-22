package main.DAO;

import main.entity.Cancel;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CancelDAOImpl implements CancelDAO {
    @Override
    public int append(Cancel cancel) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`cancel` (student_id, course_id, section_id, semester, year) VALUES (?, ?, ?, ?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1,cancel.getStudent_id());
            ppst.setString(2, cancel.getCourse_id());
            ppst.setString(3, cancel.getSection_id());
            ppst.setString(4, cancel.getSemester());
            ppst.setString(5, cancel.getYear());


            int ret = ppst.executeUpdate();
            SqlUtil.closeCon();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }
    }

    @Override
    public int modify(Cancel cancel) {
        return 0;
    }

    @Override
    public int info_exist(String student_id, String course_id, String section_id, String semester, String year) {
        Connection connection=SqlUtil.createCon();
        try
        {
            String sql = "select * from data.cancel where student_id =" + student_id + " AND course_id=" + course_id + " AND section_id=" + section_id + " AND semester=" + semester + " AND year=" + year;
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res=ppst.executeQuery();
            List<Map<String,String>> result=setReturn(res);
            SqlUtil.closeCon();
            if(result==null||result.size()==0)
                return 0;//学生没有退这门课
            if(result.size()>1) {
                return -1;//退课记录多于一条 是异常错误
            }
            else {
                return 1;//学生退了这门课
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }
    }

    @Override
    public int delete_by_section(String course_id,String section_id,String semster,String year){
        Connection connection = SqlUtil.createCon();
        try{
            String sql="DELETE FROM data.cancel WHERE course_id='"+course_id+"' AND section_id='"+section_id+"' AND semester='"+semster+"' AND year='"+year+"'";
            PreparedStatement ppst = connection.prepareStatement(sql);
            int ret=ppst.executeUpdate();
            SqlUtil.closeCon();
            return ret;
        }catch (Exception e){
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }
    }


    @Override
    public int delete(Cancel cancel) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql="DELETE FROM data.cancel where ";
            String match="";
            if(StringUtil.isNotEmpty(cancel.getStudent_id()))
                match+="AND student_id='"+cancel.getStudent_id()+"' ";
            if(StringUtil.isNotEmpty(cancel.getCourse_id()))
                match+="AND course_id='"+cancel.getCourse_id()+"' ";
            if(StringUtil.isNotEmpty(cancel.getSection_id()))
                match+="AND section_id='"+cancel.getSection_id()+"' ";
            if(StringUtil.isNotEmpty(cancel.getSemester()))
                match+="AND semester='"+cancel.getSemester()+"' ";
            if(StringUtil.isNotEmpty(cancel.getYear()))
                match+="AND year='"+cancel.getYear()+"' ";
            if(!match.isEmpty()) {
                sql+=match.substring(3);
            } else {
                return 0;
            }
            sql+=match;
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
                map.put("student_id",res.getString("student_id"));
                map.put("course_id",res.getString("course_id"));
                map.put("section_id",res.getString("section_id"));
                map.put("semester",res.getString("semester"));
                map.put("year",res.getString("year"));
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
