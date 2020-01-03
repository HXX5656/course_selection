package main.DAO;

import main.entity.Teaches;
import main.util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeachesDAOImpl implements TeachesDAO {
    @Override
    public int append(Teaches teaches) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`teaches` (teacher_id, course_id, section_id, semester, year) VALUES (?, ?, ?, ?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, teaches.getTeacher_id());
            ppst.setString(2, teaches.getCourse_id());
            ppst.setString(3, teaches.getSection_id());
            ppst.setString(4, teaches.getSemster());
            ppst.setString(5, teaches.getYear());

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
    public int delete(String teacher_id, String course_id, String section_id, String semester, String year) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`teaches` WHERE teacher_id='" + teacher_id + "' AND course_id='" + course_id + "' AND section_id='" + section_id + "' AND semester='" + semester + "' AND year='" + year + "'";
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
    public int delete_by_section(String course_id,String section_id,String semster,String year){
        Connection connection = SqlUtil.createCon();
        try{
            String sql="DELETE FROM data.teaches WHERE course_id='"+course_id+"' AND section_id='"+section_id+"' AND semester='"+semster+"' AND year='"+year+"'";
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
    public int modify(Teaches teaches) {
        return 0;
    }

    @Override
    public List<Map<String, String>> infoList(String teacher_id, String course_id, String section_id, String semester, String year) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "select * from data.teaches where teacher_id =" + teacher_id + " AND course_id=" + course_id + " AND section_id=" + section_id + " AND semester=" + semester + " AND year=" + year;
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
    public List<Map<String, String>> infoList(String teacher_id) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "select * from data.teaches where teacher_id ='" + teacher_id + "'" ;
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
    public List<Map<String, String>> get_teacher_id(String course_id,String section_id,String semester,String year) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "select * from data.teaches where  course_id=" + course_id + " AND section_id=" + section_id + " AND semester=" + semester + " AND year=" + year;
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
    private List<Map<String, String>> setReturn(ResultSet res) {
        try {
            List<Map<String, String>> result = new ArrayList<>();
            while (res.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("teacher_id", res.getString("teacher_id"));
                map.put("course_id", res.getString("course_id"));
                map.put("section_id", res.getString("section_id"));
                map.put("semester", res.getString("semester"));
                map.put("year", res.getString("year"));
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
