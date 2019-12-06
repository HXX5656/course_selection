package test.main.DAO;

import main.DAO.ApplicationDAO;
import main.DAO.DAOFactory;
import main.entity.Application;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * ApplicationDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/04/2019</pre>
 */
public class ApplicationDAOImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private ApplicationDAO applicationDAO = DAOFactory.getApplicationDAOInstance();

    /**
     * Method: append(Application application)
     */
    @Test
    public void testAppend() throws Exception {
        Application application = new Application("", "要毕业了", "", "2019-03-15", "1", "1", "1", "2", "2019");
        assert (1 == applicationDAO.append(application));
    }

    /**
     * Method: delete(String app_id)
     */
    @Test
    public void testDelete() throws Exception {

        String app_id = "1";
        assert (1 == applicationDAO.delete(app_id));
    }

    /**
     * Method: modify(Application application)
     */
    @Test
    public void testModify() throws Exception {

        Application application = new Application("2", "要毕业了", "1", "2019-04-15", "1", "1", "1", "2", "2019");
        assert (1 == applicationDAO.modify(application));
    }

    /**
     * Method: infoList(String app_id)
     */
    @Test
    public void testInfoList() throws Exception {

        String app_id = "2";
        System.out.println(applicationDAO.infoList(app_id));
    }

    @Test
    public void testStudentInfoList() throws Exception {
        System.out.println(applicationDAO.studentInfoList("9", "9", "1", "2020").get(0).get("student_id"));
    }

    /**
     * Method: setReturn(ResultSet res)
     */
    @Test
    public void testSetReturn() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ApplicationDAOImpl.getClass().getMethod("setReturn", ResultSet.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
