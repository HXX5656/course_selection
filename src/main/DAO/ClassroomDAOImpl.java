package main.DAO;

import main.entity.Classroom;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassroomDAOImpl implements ClassroomDAO {
    @Override
    public int append(Classroom classroom) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`classroom` (room_id, capacity) VALUES (?, ?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, classroom.getRoom_id());
            ppst.setString(2, classroom.getCapacity());

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
    public int delete(String room_id) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`classroom` WHERE room_id='" + room_id+"' ";
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
    public int modify(Classroom classroom) {
        Connection connection=SqlUtil.createCon();
        try
        {
            String sql = "UPDATE `data`.`classroom` SET ";
            String match="";

            if(StringUtil.isNotEmpty(classroom.getCapacity())) {
                match+=" capacity='"+classroom.getCapacity()+"'";
            }
            if(!match.isEmpty()&&StringUtil.isNotEmpty(classroom.getRoom_id())) {
                sql=sql+match+" WHERE room_id='"+classroom.getRoom_id()+"'";
            } else  {
                return 0;
            }
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
    public List<Map<String, String>> infoList(String capacity) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql="select * from data.classroom where capacity >='"+capacity+"' ";
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

    public String max(String id){
        Connection connection = SqlUtil.createCon();
        try{
            String sql = "select * from data.classroom where room_id='" + id +"' ";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet res = ppst.executeQuery();
            List<Map<String,String>> result=setReturn(res);
            SqlUtil.closeCon();
            if(result==null||result.size()==0)
                return "0";
            if(result.size()>1) {
                return  "0";
            }
            else {
                return result.get(0).get("capacity");
            }
        }catch (Exception e){
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
                map.put("room_id", res.getString("room_id"));
                map.put("capacity",res.getString("capacity"));

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
