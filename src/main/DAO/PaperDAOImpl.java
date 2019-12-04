package main.DAO;

import main.entity.Paper;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaperDAOImpl implements PaperDAO {

    @Override
    public int append(Paper paper) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`paper` (exam_id, type, time_slot_id, room_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, paper.getExam_id());
            ppst.setString(2, paper.getType());
            ppst.setString(3, paper.getTime_slot_id());
            ppst.setString(4, paper.getRoom_id());

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
    public int delete(Paper paper) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`paper` WHERE ";
            String match = "";
            if (StringUtil.isNotEmpty(paper.getExam_id()))
                match += "AND exam_id='" + paper.getExam_id() +"'";
            if (StringUtil.isNotEmpty(paper.getType()))
                match += "AND type='" + paper.getType()+"'";
            if (StringUtil.isNotEmpty(paper.getTime_slot_id()))
                match += "AND time_slot_id='" + paper.getTime_slot_id() +"'";
            if (StringUtil.isNotEmpty(paper.getRoom_id()))
                match += "AND room_id='" + paper.getRoom_id() +"'";
            if (!match.isEmpty()){
                sql += match.substring(3);
            } else {
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
    public int modify(Paper paper) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "UPDATE `data`.`paper` SET ";
            String match = "";
            if (StringUtil.isNotEmpty(paper.getType()))
                match += ", type='" + paper.getType() + "' ";
            if (StringUtil.isNotEmpty(paper.getTime_slot_id()))
                match += ", time_slot_id='" + paper.getTime_slot_id() + "' ";
            if (StringUtil.isNotEmpty(paper.getRoom_id()))
                match += ", room_id='" + paper.getRoom_id() + "' ";
            if (!match.isEmpty()) {
                sql += match.substring(1);//delete the first ,
            }
            sql += "WHERE exam_id='" + paper.getExam_id() + "'";
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
    public List<Map<String, String>> infoList(String exam_id) {
        Connection connection=SqlUtil.createCon();
        try
        {
            String sql="select * from data.paper where exam_id ="+exam_id;
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
                map.put("exam_id",res.getString("exam_id"));
                map.put("type",res.getString("type"));
                map.put("time_slot_id",res.getString("time_slot_id"));
                map.put("room_id",res.getString("room_id"));
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
