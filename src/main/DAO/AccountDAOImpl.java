package main.DAO;

import main.entity.Account;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class AccountDAOImpl implements AccountDAO{
    @Override
    public int append(Account account) {
        Connection connection= SqlUtil.createCon();
        try
        {
            String sql= "INSERT  INTO `data`.`account_table` (ac_id, pwd) VALUES (?, ?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1,account.getAc_id());
            ppst.setString(2,account.getPwd());
            int ret= ppst.executeUpdate();
            SqlUtil.closeCon();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }

    }

    @Override
    public int delete(String ac_id) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `data`.`account_table` WHERE ac_id='" + ac_id + "'";
            PreparedStatement ppst = connection.prepareStatement(sql);
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
    public int modify(Account account) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql = "UPDATE `data`.`account_table` SET ";
            String match="";
//            if(StringUtil.isNotEmpty(course.getCourse_id()))
//                match +=", course_id='"+course.getCourse_id()+"' ";
            if(StringUtil.isNotEmpty(account.getPwd()))
                match +=", pwd='"+account.getPwd()+"' ";
            if (!match.isEmpty()) {
                sql+=match.substring(1);//delete the first ,
            } else {
                return 0;
            }
            sql+="WHERE ac_id='"+account.getAc_id()+"'";
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
    public String infoList(String ac_id) {
        Connection connection=SqlUtil.createCon();
        try
        {
            String sql="select * from data.account_table where ac_id ='"+ac_id+"'";
            PreparedStatement ppst=connection.prepareStatement(sql);
            ResultSet res=ppst.executeQuery();
            SqlUtil.closeCon();
            while (res.next()){
                return res.getString("pwd");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }
}
