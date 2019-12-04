package main.DAO;

import main.entity.Essay;
import main.entity.Exam;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamDAOImpl implements ExamDAO {
    @Override
    public int append(Exam exam) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`exam` (exam_id, exam_week) VALUES (?, ?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, StringUtil.isEmpty(exam.getExam_id())?"0":exam.getExam_id());
            ppst.setString(2, exam.getExam_week());

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
    public int delete(String exam_id) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`exam` WHERE exam_id='" + exam_id + "'";
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
    public int modify(Exam exam) {
        return 0;
    }

    @Override
    public List<Map<String, String>> infoList(String exam_id) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql="select * from data.exam where exam_id ="+exam_id;
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
                map.put("exam_week",res.getString("exam_week"));

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
