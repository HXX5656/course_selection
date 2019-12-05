package main.DAO;

import main.entity.Application;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationDAOImpl implements ApplicationDAO {
    @Override
    public int append(Application application) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql= "INSERT  INTO `data`.`application` (app_id, reason, status, apply_time,student_id,course_id,section_id,semester,year) VALUES (?, ?, ?, ?,?,?,?,?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1,StringUtil.isEmpty(application.getApp_id())?"0":application.getApp_id());
            ppst.setString(2,application.getReason());
            ppst.setString(3,StringUtil.isEmpty(application.getStatus())?"0":application.getStatus());
            ppst.setString(4,application.getApp_time());
            ppst.setString(5,application.getStudent_id());
            ppst.setString(6,application.getCourse_id());
            ppst.setString(7,application.getSection_id());
            ppst.setString(8,application.getSemester());
            ppst.setString(9,application.getYear());

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
    public int delete(String app_id) {
        Connection connection =SqlUtil.createCon();
        try
        {
            String sql = "DELETE FROM `data`.`application` WHERE app_id='" + app_id + "'";
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
    public int modify(Application application) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql = "UPDATE `data`.`application` SET ";
            String match="";
//            if(StringUtil.isNotEmpty(course.getCourse_id()))
//                match +=", course_id='"+course.getCourse_id()+"' ";
            if(StringUtil.isNotEmpty(application.getReason()))
                match +=", reason='"+application.getReason()+"' ";
            if(StringUtil.isNotEmpty(application.getStatus()))
                match +=", status='"+application.getStatus()+"' ";
            if (!match.isEmpty()) {
                sql+=match.substring(1);//delete the first ,
            } else {
                return 0;
            }
            sql+="WHERE app_id='"+application.getApp_id()+"'";
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
    public List<Map<String, String>> infoList(String app_id) {
        Connection connection=SqlUtil.createCon();
        try
        {
            String sql="select * from data.application where app_id ="+app_id;
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
    public int info_id(String student_id, String course_id, String section_id, String semester, String year) {
        Connection connection=SqlUtil.createCon();
        try
        {
            String sql = "select app_id from data.application where student_id =" + student_id + " AND course_id=" + course_id + " AND section_id=" + section_id + " AND semester=" + semester + " AND year=" + year;
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res=ppst.executeQuery();
            List<Map<String,String>> result=setReturn(res);
            SqlUtil.closeCon();
            if(result==null||result.size()==0)
                return 0;
            if(result.size()>1) {
                return -1;
            }
            else {
                return Integer.parseInt(result.get(0).get("app_id"));
            }

        }
        catch (Exception e) {
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
                map.put("app_id",res.getString("app_id"));
                map.put("reason",res.getString("reason"));
                map.put("status",res.getString("status"));
                map.put("apply_time",res.getString("apply_time"));
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
