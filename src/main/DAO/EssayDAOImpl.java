package main.DAO;

import main.entity.Essay;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EssayDAOImpl implements EssayDAO {
    @Override
    public int append(Essay essay) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`essay` (exam_id, demand) VALUES (?, ?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, essay.getExam_id());
            ppst.setString(2, essay.getDemand());

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
            String sql = "DELETE FROM `data`.`essay` WHERE sexam_id='" + exam_id + "'";
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
    public int modify(Essay essay) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql="UPDATE `data`.`essay` SET ";
            String match="";
            if(StringUtil.isNotEmpty(essay.getDemand()))
                match+=", demand='"+essay.getDemand()+"' ";

            if(!match.isEmpty()&&StringUtil.isNotEmpty(essay.getExam_id())) {
                sql+=match.substring(1);
            } else  {
                return 0;
            }
            sql+="WHERE exam_id='"+essay.getExam_id()+"'";
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
    public List<Map<String, String>> infoList(String exam_id) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql="select * from data.essay where exam_id ="+exam_id;
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
                map.put("demand",res.getString("demand"));

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
