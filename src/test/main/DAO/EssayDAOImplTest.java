package test.main.DAO; 

import main.DAO.DAOFactory;
import main.DAO.EssayDAO;
import main.entity.Essay;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* EssayDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/04/2019</pre> 
* @version 1.0 
*/ 
public class EssayDAOImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 
private EssayDAO essayDAO= DAOFactory.getEssayDAOInstance();
/** 
* 
* Method: append(Essay essay) 
* 
*/ 
@Test
public void testAppend() throws Exception {

    Essay essay=new Essay("1","3000字");
    assert (essayDAO.append(essay)==1);
} 

/** 
* 
* Method: delete(String exam_id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
    String exam_id="1";
    assert (essayDAO.delete(exam_id)==1);

} 

/** 
* 
* Method: modify(Essay essay) 
* 
*/ 
@Test
public void testModify() throws Exception { 

    Essay essay=new Essay("1","3050字 不准抄袭");
    assert (1==essayDAO.modify(essay));
} 

/** 
* 
* Method: infoList(String exam_id) 
* 
*/ 
@Test
public void testInfoList() throws Exception { 

    String exam_id="1";
    System.out.println(essayDAO.infoList(exam_id));
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
   Method method = EssayDAOImpl.getClass().getMethod("setReturn", ResultSet.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
