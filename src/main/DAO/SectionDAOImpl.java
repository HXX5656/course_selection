package main.DAO;

import main.entity.Section;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionDAOImpl implements SectionDAO {
    @Override
    public int append(Section section) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`section` (course_id, section_id, semester, year, start, end, max, exam_id) VALUES (?, ?, ?, ?,?,?,?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, section.getCourse_id());
            ppst.setString(2, section.getSection_id());
            ppst.setString(3, section.getSemester());
            ppst.setString(4, section.getYear());
            ppst.setString(5, section.getStart());
            ppst.setString(6, section.getEnd());
            ppst.setString(7, section.getMax());
            ppst.setString(8, section.getExam_id());

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
    public int delete(String course_id, String section_id, String semester, String year) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`section` WHERE course_id='" + course_id + "'AND section_id='" + section_id + "' AND semester='" + semester + "' AND year='" + year + "'";
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
    public int modify(Section section) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "UPDATE `data`.`section` SET ";
            String match = "";
//            if(StringUtil.isNotEmpty(section.getCourse_id()))
//               match +=", course_id='"+section.getCourse_id()+"' ";
//            if(StringUtil.isNotEmpty(section.getSection_id()))
//                match +=", section_id='"+section.getSection_id()+"' ";
//            if(StringUtil.isNotEmpty(section.getSemester()))
//                match +=", semester='"+section.getSemester()+"' ";
//            if(StringUtil.isNotEmpty(section.getYear()))
//                match +=", year='"+section.getYear()+"' ";
            if (StringUtil.isNotEmpty(section.getStart()))
                match += ", start='" + section.getStart() + "' ";
            if (StringUtil.isNotEmpty(section.getEnd()))
                match += ", end='" + section.getEnd() + "' ";
            if (StringUtil.isNotEmpty(section.getMax()))
                match += ", max='" + section.getMax() + "' ";
            if (StringUtil.isNotEmpty(section.getExam_id()))
                match += ", exam_id='" + section.getExam_id() + "' ";
            if (!match.isEmpty()) {
                sql += match.substring(1);//delete the first ,
            } else  {
                return 0;
            }
            sql += "WHERE course_id='" + section.getCourse_id() + "' AND section_id='" + section.getSection_id() + "' AND semester='" + section.getSemester() + "' AND year='" + section.getYear() + "'";
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
    public List<Map<String, String>> infoList(String course_id, String section_id, String semester, String year) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "select * from data.section where course_id =" + course_id + " AND section_id =" + section_id + " AND semester=" + semester + " AND year=" + year;
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
                map.put("course_id", res.getString("course_id"));
                map.put("section_id", res.getString("section_id"));
                map.put("semester", res.getString("semester"));
                map.put("year", res.getString("year"));
                map.put("start", res.getString("start"));
                map.put("end", res.getString("end"));
                map.put("max", res.getString("max"));
                map.put("exam_id", res.getString("exam_id"));
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
