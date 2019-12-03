package test.main.DAO; 

import main.DAO.DAOFactory;
import main.DAO.TeacherDAO;
import main.entity.Teacher;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* TeacherDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/02/2019</pre> 
* @version 1.0 
*/ 
public class TeacherDAOImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

private static TeacherDAO teacherDAO=DAOFactory.getTeacherDAOInstance();

/** 
* 
* Method: infoList(String teacher_id) 
* 
*/ 
@Test
public void testInfoList() throws Exception { 

    String teacher_id ="1";
    System.out.println(teacherDAO.infoList(teacher_id));
} 

/** 
* 
* Method: append(Teacher teacher) 
* 
*/ 
@Test
public void testAppend() throws Exception {

    Teacher teacher=new Teacher("1","blw",null,null,"1");
    assert (teacherDAO.append(teacher)==1);
} 

/** 
* 
* Method: delete(String teacher_id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 

    String teacher_id ="1";
    assert (teacherDAO.delete(teacher_id)==1);

} 

/** 
* 
* Method: modify(Teacher teacher) 
* 
*/ 
@Test
public void testModify() throws Exception { 

    Teacher teacher=new Teacher("1","hxx","2005-01-13",null,"1");
    assert (teacherDAO.modify(teacher)==1);

} 


} 
