package main.DAO;

import main.entity.Timeslot;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeslotDAOImpl implements TimeslotDAO {
    @Override
    public int append(Timeslot timeslot) {
        Connection connection= SqlUtil.createCon();
        try{
            String sql ="SELECT * FROM `data`.`time_slot` WHERE start_time=" + timeslot.getStart_time() + " AND end_time=" + timeslot.getEnd_time() + " AND day_of_week=" + timeslot.getDay_of_week();
            PreparedStatement ppst = connection.prepareStatement(sql);
            if (ppst.executeQuery().next()){
                return 0;
            }
        }catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }


        try {
            String sql= "INSERT  INTO `data`.`time_slot` (start_time, end_time, day_of_week )VALUES (?, ?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1,timeslot.getStart_time());
            ppst.setString(2,timeslot.getEnd_time());
            ppst.setString(3,timeslot.getDay_of_week());

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
    public int modify(Timeslot timeslot) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql = "UPDATE `data`.`time_slot` SET ";
            String match="";
            if(StringUtil.isNotEmpty(timeslot.getStart_time()))
                match +=", start_time='"+timeslot.getStart_time()+"' ";
            if(StringUtil.isNotEmpty(timeslot.getEnd_time()))
                match +=", end_time='"+timeslot.getEnd_time()+"' ";
            if(StringUtil.isNotEmpty(timeslot.getDay_of_week()))
                match +=", day_of_week='"+timeslot.getDay_of_week()+"' ";

            if (!match.isEmpty()) {
                sql+=match.substring(1);//delete the first ,
            }
            sql+="WHERE time_id='"+timeslot.getTime_id()+"'";
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
    public List<Map<String, String>> infoList(String time_id) {
        Connection connection=SqlUtil.createCon();
        try
        {
            String sql="select * from data.time_slot where time_id ="+time_id;
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

    @Override
    public int delete(String time_id) {
        Connection connection =SqlUtil.createCon();
        try
        {
            String sql = "DELETE FROM `data`.`time_slot` WHERE time_id='" + time_id + "'";
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

    private List<Map<String,String>> setReturn(ResultSet res) {
        try {
            List<Map<String,String>> result = new ArrayList<>();
            while (res.next()) {
                Map<String,String> map=new HashMap<>();
                map.put("time_id",res.getString("time_id"));
                map.put("start_time",res.getString("start_time"));
                map.put("end_time",res.getString("end_time"));
                map.put("day_of_week",res.getString("day_of_week"));
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
