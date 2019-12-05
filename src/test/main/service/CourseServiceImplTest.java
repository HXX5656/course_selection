package test.main.service; 

import com.google.gson.JsonObject;
import main.service.CourseService;
import main.service.ServiceFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* CourseServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/05/2019</pre> 
* @version 1.0 
*/ 
public class CourseServiceImplTest { 

@Before
public void before() throws Exception {
    jsonObject.addProperty("student_id","1");
    jsonObject.addProperty("course_id","1");
    jsonObject.addProperty("section_id","1");
    jsonObject.addProperty("semester","2");
    jsonObject.addProperty("year","2019");
    jsonObject.addProperty("reason","再没有英语课我要退学啦");
    jsonObject.addProperty("date","2019-12-04");
    courseService=ServiceFactory.getCourseServiceInstance(jsonObject);
} 

@After
public void after() throws Exception { 
} 
private CourseService courseService;
private JsonObject jsonObject=new JsonObject();
/** 
* 
* Method: select_or_cancel() 
* 
*/ 
@Test
public void testSelect_or_cancel() throws Exception { 

    courseService.select_or_cancel();

} 

/** 
* 
* Method: apply() 
* 
*/ 
@Test
public void testApply() throws Exception { 

    courseService.apply();
} 

/** 
* 
* Method: confirm_apply() 
* 
*/ 
@Test
public void testConfirm_apply() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: refuse_apply() 
* 
*/ 
@Test
public void testRefuse_apply() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: getApp_id() 
* 
*/ 
@Test
public void testGetApp_id() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CourseServiceImpl.getClass().getMethod("getApp_id"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
