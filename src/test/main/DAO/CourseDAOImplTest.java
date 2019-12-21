package test.main.DAO; 

import main.DAO.CourseDAO;
import main.DAO.DAOFactory;
import main.entity.Course;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* CourseDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/02/2019</pre> 
* @version 1.0 
*/ 
public class CourseDAOImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 
private static CourseDAO courseDAO= DAOFactory.getCourseDAOInstance();
/** 
* 
* Method: append(Course course) 
* 
*/ 
@Test
public void testAppend() throws Exception {
    Course course=new Course("4","CSE","2","100","1");
    assert (1==courseDAO.append(course));
} 

/** 
* 
* Method: delete(String course_id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 

    String course_id="1";
    assert (1==courseDAO.delete(course_id));
} 

/** 
* 
* Method: modify(Course course) 
* 
*/ 
@Test
public void testModify() throws Exception { 

    Course course=new Course("1","ICS","","","");
    assert (1==courseDAO.modify(course));

} 

/** 
* 
* Method: infoList(String course_id) 
* 
*/ 
@Test
public void testInfoList() throws Exception { 

    String course_id="1";
    System.out.println(courseDAO.infoList(course_id));
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
   Method method = CourseDAOImpl.getClass().getMethod("setReturn", ResultSet.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
