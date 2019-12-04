package main.DAO;

import main.entity.Cancel;
import main.util.SqlUtil;
import main.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class CancelDAOImpl implements CancelDAO {
    @Override
    public int append(Cancel cancel) {
        Connection connection = SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `data`.`cancel` (student_id, course_id, section_id, semester, year) VALUES (?, ?, ?, ?,?)";
            PreparedStatement ppst = connection.prepareStatement(sql);
            ppst.setString(1,cancel.getStudent_id());
            ppst.setString(2, cancel.getCourse_id());
            ppst.setString(3, cancel.getSection_id());
            ppst.setString(4, cancel.getSemester());
            ppst.setString(5, cancel.getYear());


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
    public int modify(Cancel cancel) {
        return 0;
    }

    @Override
    public List<Map<String, String>> infoList(String student_id, String course_id, String section_id, String semester, String year) {
        return null;
    }

    @Override
    public int delete(Cancel cancel) {
        Connection connection=SqlUtil.createCon();
        try {
            String sql="DELETE FROM data.cancel where ";
            String match="";
            if(StringUtil.isNotEmpty(cancel.getStudent_id()))
                match+="AND student_id='"+cancel.getStudent_id()+"' ";
            if(StringUtil.isNotEmpty(cancel.getCourse_id()))
                match+="AND course_id='"+cancel.getCourse_id()+"' ";
            if(StringUtil.isNotEmpty(cancel.getSection_id()))
                match+="AND section_id='"+cancel.getSection_id()+"' ";
            if(StringUtil.isNotEmpty(cancel.getSemester()))
                match+="AND semester='"+cancel.getSemester()+"' ";
            if(StringUtil.isNotEmpty(cancel.getYear()))
                match+="AND year='"+cancel.getYear()+"' ";
            if(!match.isEmpty()) {
                sql+=match.substring(3);
            } else {
                return 0;
            }
            sql+=match;
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
}
