package test.main.DAO; 

import main.DAO.DAOFactory;
import main.DAO.TimeslotDAO;
import main.entity.Timeslot;
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
    Timeslot timeslot = new Timeslot("5","6","7");
    assert (1 == timeslotDAO.append(timeslot));
} 

/** 
* 
* Method: modify(Timeslot timeslot) 
* 
*/ 
@Test
public void testModify() throws Exception { 
    Timeslot timeslot = new Timeslot("4","6","7","8");
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
    String time_id = "4";
    assert (1 == timeslotDAO.delete(time_id));
} 



} 
