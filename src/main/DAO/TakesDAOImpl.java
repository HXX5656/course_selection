package main.DAO;

import main.entity.Takes;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TakesDAOImpl implements TakesDAO {
    @Override
    public int append(Takes takes) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`takes` (student_id, course_id, section_id, semester, year, grade) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, takes.getStudent_id());
            ppst.setString(2, takes.getCourse_id());
            ppst.setString(3, takes.getSection_id());
            ppst.setString(4, takes.getSemester());
            ppst.setString(5, takes.getYear());
            ppst.setString(6, takes.getGrade());

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
    public int modify(Takes takes) {

        Connection connection = SqlUtil.createCon();
        try {
            String sql = "UPDATE `data`.`takes` SET ";
            String match = "";
            if (StringUtil.isNotEmpty(takes.getGrade())) {
                match += ", grade='" + takes.getGrade() + "' ";
            } else {
                return 0;
            }
            if (!match.isEmpty()) {
                sql += match.substring(1);//delete the first ,
            } else {
                return 0;
            }
            sql += "WHERE student_id='" + takes.getStudent_id() + "' AND course_id='" + takes.getCourse_id() + "' AND section_id='" + takes.getSection_id() + "' AND semester='" + takes.getSemester() + "' AND year='" + takes.getYear() + "'";
            PreparedStatement ppst = connection.prepareStatement(sql);
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
    public List<Map<String, String>> infoList(String student_id, String course_id, String section_id, String semster, String year) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "select * from data.takes where student_id =" + student_id + " AND course_id=" + course_id + " AND section_id=" + section_id + " AND semester=" + semster + " AND year=" + year;
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res = ppst.executeQuery();
            SqlUtil.closeCon();
            return setReturn(res);
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }

    @Override
    public List<Map<String, String>> studentInfoList(String course_id, String section_id, String semster, String year) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "select * from data.takes where course_id=" + course_id + " AND section_id=" + section_id + " AND semester=" + semster + " AND year=" + year;
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res = ppst.executeQuery();
            SqlUtil.closeCon();
            return setReturn(res);
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }


    @Override
    public int delete(Takes takes) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "DELETE  FROM data.takes WHERE ";
            String match = "";
            if (StringUtil.isNotEmpty(takes.getStudent_id()))
                match += "AND student_id='" + takes.getStudent_id() + "' ";
            if (StringUtil.isNotEmpty(takes.getCourse_id()))
                match += "AND course_id='" + takes.getCourse_id() + "' ";
            if (StringUtil.isNotEmpty(takes.getSection_id()))
                match += "AND section_id='" + takes.getSection_id() + "' ";
            if (StringUtil.isNotEmpty(takes.getSemester()))
                match += "AND semester='" + takes.getSemester() + "'";
            if (StringUtil.isNotEmpty(takes.getYear()))
                match += "AND year='" + takes.getYear() + "'";
            if (!match.isEmpty()){
                sql += match.substring(3);
            }else {
                return 0;
            }
            PreparedStatement ppst = connection.prepareStatement(sql);
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
    public List<Map<String,String>> findByStudent(String student_id){
        Connection connection = SqlUtil.createCon();
        try{
            String sql="SELECT * FROM data.takes WHERE student_id=" + student_id;
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res = ppst.executeQuery();
            SqlUtil.closeCon();
            return setReturn(res);
        }catch (Exception e){
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }

    @Override
    public int delete_by_section(String course_id,String section_id,String semster,String year){
        Connection connection = SqlUtil.createCon();
        try{
            String sql="DELETE FROM data.takes WHERE course_id='"+course_id+"' AND section_id='"+section_id+"' AND semester='"+semster+"' AND year='"+year+"'";
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
    public List<Map<String,String>> get_taken_course(String student_id) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql="select * from data.takes as t, data.course as c where student_id ='"+student_id+"' and t.course_id = c.course_id";
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
    private List<Map<String, String>> setReturn(ResultSet res) {
        try {
            List<Map<String, String>> result = new ArrayList<>();
            while (res.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("student_id", res.getString("student_id"));
                map.put("course_id", res.getString("course_id"));
                map.put("section_id", res.getString("section_id"));
                map.put("semester", res.getString("semester"));
                map.put("year", res.getString("year"));
                map.put("grade", res.getString("grade"));
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