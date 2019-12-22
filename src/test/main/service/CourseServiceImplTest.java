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
 * @version 1.0
 * @since <pre>12/05/2019</pre>
 */
public class CourseServiceImplTest {

    @Before
    public void before() throws Exception {
        jsonObject.addProperty("student_id", "7");
        jsonObject.addProperty("course_id", "9");
        jsonObject.addProperty("section_id", "9");
        jsonObject.addProperty("semester", "1");
        jsonObject.addProperty("year", "2020");
        jsonObject.addProperty("reason", "再没有英语课我要退学啦");
        jsonObject.addProperty("date", "2019-12-04");
        courseService = ServiceFactory.getCourseServiceInstance(jsonObject);
    }

    @After
    public void after() throws Exception {
    }

    private CourseService courseService;
    private JsonObject jsonObject = new JsonObject();

    /**
     * Method: select_or_cancel()
     */
    @Test
    public void testSelect_or_cancel() throws Exception {

        courseService.select_or_cancel();

    }

    /**
     * Method: apply()
     */
    @Test
    public void testApply() throws Exception {

        assert (courseService.apply() == 1);
    }

    /**
     * Method: confirm_apply()
     */
    @Test
    public void testConfirm_apply() throws Exception {
        System.out.println(courseService.confirm_apply());

    }

    /**
     * Method: refuse_apply()
     */
    @Test
    public void testRefuse_apply() throws Exception {
        courseService.refuse_apply();
    }


    /**
     * Method: getApp_id()
     */
    @Test
    public void testGetApp_id() throws Exception {

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
