package test.main.DAO; 

import main.DAO.DAOFactory;
import main.DAO.DepartmentDAO;
import main.entity.Department;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* DepartmentDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/04/2019</pre> 
* @version 1.0 
*/ 
public class DepartmentDAOImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 
private DepartmentDAO departmentDAO= DAOFactory.getDepartmentDAOInstance();
/** 
* 
* Method: append(Department department) 
* 
*/ 
@Test
public void testAppend() throws Exception {

    Department department=new Department("3","SE");
    assert (1==departmentDAO.append(department));
} 

/** 
* 
* Method: delete(String dep_id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 

} 

/** 
* 
* Method: modify(Department department) 
* 
*/ 
@Test
public void testModify() throws Exception { 

} 

/** 
* 
* Method: infoList(String dep_id) 
* 
*/ 
@Test
public void testInfoList() throws Exception { 

    String dep_id="2";
    System.out.println(departmentDAO.infoList(dep_id));
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
   Method method = DepartmentDAOImpl.getClass().getMethod("setReturn", ResultSet.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
