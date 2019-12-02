package main.DAO;

import main.entity.Course;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public int append(Course course) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql= "INSERT  INTO `data`.`course` (course_id, course_name, credit, period) VALUES (?, ?, ?, ?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1,course.getCourse_id());
            ppst.setString(2,course.getCourse_name());
            ppst.setString(3,course.getCredit());
            ppst.setString(4,course.getPeriod());

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
    public int delete(String course_id) {
        Connection connection =SqlUtil.createCon();
        try
        {
            String sql = "DELETE FROM `data`.`course` WHERE course_id='" + course_id + "'";
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
    public int modify(Course course) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql = "UPDATE `data`.`course` SET ";
            String match="";
//            if(StringUtil.isNotEmpty(course.getCourse_id()))
//                match +=", course_id='"+course.getCourse_id()+"' ";
            if(StringUtil.isNotEmpty(course.getCourse_name()))
                match +=", course_name='"+course.getCourse_name()+"' ";
            if(StringUtil.isNotEmpty(course.getCredit()))
                match +=", credit='"+course.getCredit()+"' ";
            if(StringUtil.isNotEmpty(course.getPeriod()))
                match +=", period='"+course.getPeriod()+"' ";
            if (!match.isEmpty()) {
                sql+=match.substring(1);//delete the first ,
            }
            sql+="WHERE course_id='"+course.getCourse_id()+"'";
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
    public List<Map<String, String>> infoList(String course_id) {

        Connection connection=SqlUtil.createCon();
        try
        {
            String sql="select * from data.course where course_id ="+course_id;
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
                map.put("course_id",res.getString("course_id"));
                map.put("course_name",res.getString("course_name"));
                map.put("credit",res.getString("credit"));
                map.put("period",res.getString("period"));
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
