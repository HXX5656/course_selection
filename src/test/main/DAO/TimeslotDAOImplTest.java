package test.main.DAO; 

import main.DAO.DAOFactory;
import main.DAO.TimeslotDAO;
import main.entity.Timeslot;
import main.util.StringUtil;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* TimeslotDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/03/2019</pre>
* @version 1.0 
*/ 
public class TimeslotDAOImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

private static TimeslotDAO timeslotDAO = DAOFactory.getTimeslotDAOInstance();

/** 
* 
* Method: append(Timeslot timeslot) 
* 
*/ 
@Test
public void testAppend() throws Exception {
//    for (int i = 1; i <=7; i++) {
//        for (int j = 1; j <=13 ; j++) {
//            Timeslot timeslot=new Timeslot(StringUtil.getStart(j),StringUtil.getEnd(j),i+"");
//            timeslotDAO.append(timeslot);
//        }
//    }
    String day="7";
    String start="";
    String end="";
    for (int i=1;i<=5;i++) {
        if(i==1) {
            start="0800";
            end="1000";
        } else if(i==2) {
            start="1030";
            end="1230";
        } else if(i==3) {
            start="1330";
            end="1530";
        }else if(i==4) {
            start="1600";
            end="1800";
        } else  {
            start="1830";
            end="2030";
        }
        Timeslot timeslot=new Timeslot(start,end,day);
        timeslotDAO.append(timeslot);
    }
} 

/** 
* 
* Method: modify(Timeslot timeslot) 
* 
*/ 
@Test
public void testModify() throws Exception { 
    Timeslot timeslot = new Timeslot("4","6","7","4");
    assert (1 == timeslotDAO.modify(timeslot));
} 

/** 
* 
* Method: infoList(String time_id) 
* 
*/ 
@Test
public void testInfoList() throws Exception { 
    String time_id = "4";
    System.out.println(timeslotDAO.infoList("4"));
} 

/** 
* 
* Method: delete(String time_id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
    String time_id = "3";
    assert (1 == timeslotDAO.delete(time_id));
} 



} 
