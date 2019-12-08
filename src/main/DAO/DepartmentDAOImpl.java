package main.DAO;

import main.entity.Department;
import main.util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public int append(Department department) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`department` (dep_id, dep_name) VALUES (?, ?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1, department.getDep_id());
            ppst.setString(2, department.getDep_name());

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
    public int delete(String dep_id) {
        return 0;
    }

    @Override
    public int modify(Department department) {
        return 0;
    }

    @Override
    public List<Map<String, String>> infoList(String dep_id) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql="select * from data.department where dep_id ="+dep_id;
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
    public List<Map<String, String>> info_id(String name) {
        Connection connection= SqlUtil.createCon();
        try {
            String sql="select * from data.department where dep_name ='"+name+"'";
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
                map.put("dep_id",res.getString("dep_id"));
                map.put("dep_name",res.getString("dep_name"));

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
