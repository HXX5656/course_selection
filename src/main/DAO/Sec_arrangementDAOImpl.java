package main.DAO;

import main.entity.Sec_arrangement;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sec_arrangementDAOImpl implements Sec_arrangementDAO {

    @Override
    public int append(Sec_arrangement sec_arrangement) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`sec_arrangement` (time_slot_id, room_id, course_id, section_id,semester, year) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, sec_arrangement.getTime_slot_id());
            ppst.setString(2, sec_arrangement.getRoom_id());
            ppst.setString(3, sec_arrangement.getCourse_id());
            ppst.setString(4, sec_arrangement.getSection_id());
            ppst.setString(5, sec_arrangement.getSemester());
            ppst.setString(6, sec_arrangement.getYear());

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
    public int modify(Sec_arrangement sec_arrangement) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "UPDATE `data`.`sec_arrangement` SET ";
            String match = "";
            if (StringUtil.isNotEmpty(sec_arrangement.getCourse_id()))
                match += ", course_id='" + sec_arrangement.getCourse_id() + "' ";
            if (StringUtil.isNotEmpty(sec_arrangement.getSection_id()))
                match += ", section_id='" + sec_arrangement.getSection_id() + "' ";
            if (StringUtil.isNotEmpty(sec_arrangement.getSemester()))
                match += ", semester='" + sec_arrangement.getSemester() + "' ";
            if (StringUtil.isNotEmpty(sec_arrangement.getYear()))
                match += ", year='" + sec_arrangement.getYear() + "' ";
            if (!match.isEmpty()) {
                sql += match.substring(1);//delete the first ,
            }
            sql += "WHERE time_slot_id='" + sec_arrangement.getTime_slot_id() + "' AND room_id='" + sec_arrangement.getRoom_id() + "'";
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
    public List<Map<String, String>> infoList(String time_slot_id, String room_id) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "select * from data.sec_arrangement where time_slot_id=" + time_slot_id + " AND room_id=" + room_id;
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
    public List<Map<String, String>> infoList(String time_slot_id, String course_id,String section_id,String semester,String year) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "select * from data.sec_arrangement where time_slot_id=" + time_slot_id + " AND course_id=" +course_id+
                    " AND section_id="+section_id+" AND semester="+semester+" AND year='"+year+"'";
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
    public int delete(String time_slot_id, String room_id) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`sec_arrangement` WHERE time_slot_id='" + time_slot_id + "' AND room_id='" + room_id + "'";
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
    public int delete_by_section(String course_id,String section_id,String semester,String year){
        Connection connection = SqlUtil.createCon();
        try{
            String sql="DELETE FROM data.sec_arrangement WHERE course_id='"+course_id+"' AND section_id='"+section_id+"' AND semester='"+semester+"' AND year='"+year+"'";
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
    public int find_time(String course_id, String section_id, String semester, String year){
        Connection connection = SqlUtil.createCon();
        int time = 0;
        try {
            String sql = "select * from data.sec_arrangement where course_id=" + course_id + " AND section_id=" + section_id + " AND semester=" + semester + " AND year=" + year;
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res = ppst.executeQuery();
            List<Map<String, String>> result = setReturn(res);
            SqlUtil.closeCon();
            if (result == null || result.size() == 0) {
                return time;
            } else {
                for (int i = 0; i < result.size(); i++) {
                    time = Integer.parseInt(result.get(0).get("time_slot_id"));
                }
                return time;
            }

        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return 0;
        }
    }

    @Override
    public List<String> findRoom(String course_id, String section_id, String semester, String year) {
        Connection connection = SqlUtil.createCon();
        List<String> stringList = new ArrayList<>();
        try {
            String sql = "select * from data.sec_arrangement where course_id=" + course_id + " AND section_id=" + section_id + " AND semester=" + semester + " AND year=" + year;
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res = ppst.executeQuery();
            List<Map<String, String>> result = setReturn(res);
            SqlUtil.closeCon();
            if (result == null || result.size() == 0) {
                stringList.add("0");
                return stringList;
            } else {
                for (int i = 0; i < result.size(); i++) {
                    stringList.add(result.get(i).get("room_id"));
                }
                return stringList;
            }

        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }

    }
    public List<Map<String,String>> getArrangements(String course_id, String section_id, String semester, String year) {
        Connection connection = SqlUtil.createCon();
        List<String> stringList = new ArrayList<>();
        try {
            String sql = "select * from data.sec_arrangement where course_id='" + course_id + "' AND section_id='" + section_id + "' AND semester='" + semester + "' AND year='" + year+"'";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res = ppst.executeQuery();
            List<Map<String, String>> result = setReturn(res);
            SqlUtil.closeCon();
            return result;
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
                map.put("time_slot_id", res.getString("time_slot_id"));
                map.put("room_id", res.getString("room_id"));
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
