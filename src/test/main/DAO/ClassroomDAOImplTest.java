package test.main.DAO; 

import main.DAO.ClassroomDAO;
import main.DAO.DAOFactory;
import main.entity.Classroom;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ClassroomDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/04/2019</pre> 
* @version 1.0 
*/ 
public class ClassroomDAOImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 
private ClassroomDAO classroomDAO= DAOFactory.getClassroomDAOInstance();
/** 
* 
* Method: append(Classroom classroom) 
* 
*/ 
@Test
public void testAppend() throws Exception {

    Classroom classroom=new Classroom("2201","100");
    assert (1==classroomDAO.append(classroom));
} 

/** 
* 
* Method: delete(String room_id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 

    String room_id="2201";
    assert (1==classroomDAO.delete(room_id));
} 

/** 
* 
* Method: modify(Classroom classroom) 
* 
*/ 
@Test
public void testModify() throws Exception { 

    Classroom classroom=new Classroom("2201","29");
    assert (1==classroomDAO.modify(classroom));
} 

/** 
* 
* Method: infoList(String capacity) 
* 
*/ 
@Test
public void testInfoList() throws Exception { 

    String capacity="30";
    System.out.println(classroomDAO.infoList(capacity));
} 

/**
 * Method:Max(String room_id)
 * */
@Test
public void testMax(){
    String room_id = "9";
    assert (classroomDAO.max(room_id).equals("100"));
}


/** 
* 
* Method: setReturn(ResultSet res) 
* 
*/ 
@Test
public void testSetReturn() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = ClassroomDAOImpl.getClass().getMethod("setReturn", ResultSet.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
