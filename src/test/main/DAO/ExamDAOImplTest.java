package test.main.DAO; 

import main.DAO.DAOFactory;
import main.DAO.ExamDAO;
import main.entity.Exam;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ExamDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/04/2019</pre> 
* @version 1.0 
*/ 
public class ExamDAOImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}
private ExamDAO examDAO= DAOFactory.getExamDAOInstance();

/** 
* 
* Method: append(Exam exam) 
* 
*/ 
@Test
public void testAppend() throws Exception { 

    Exam exam=new Exam("","18");
    assert (1==examDAO.append(exam));
} 

/** 
* 
* Method: delete(String exam_id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 

    String exam_id="2";
    assert (1==examDAO.delete(exam_id));
} 

/** 
* 
* Method: modify(Exam exam) 
* 
*/ 
@Test
public void testModify() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: infoList(String exam_id) 
* 
*/ 
@Test
public void testInfoList() throws Exception { 

    String exam_id="1";
    System.out.println(examDAO.infoList(exam_id));
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
   Method method = ExamDAOImpl.getClass().getMethod("setReturn", ResultSet.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
