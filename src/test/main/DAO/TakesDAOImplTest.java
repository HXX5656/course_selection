package test.main.DAO;

import main.DAO.DAOFactory;
import main.DAO.TakesDAO;
import main.entity.Takes;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * TakesDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/03/2019</pre>
 */
public class TakesDAOImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private static TakesDAO takesDAO = DAOFactory.getTakesDAOInstance();

    /**
     * Method: append(Takes takes)
     */
    @Test
    public void testAppend() throws Exception {
        Takes takes = new Takes("9", "9", "9", "1", "2020", null);
        assert (1 == takesDAO.append(takes));
    }

    /**
     * Method: modify(Takes takes)
     */
    @Test
    public void testModify() throws Exception {
        Takes takes = new Takes("9", "9", "9", "1", "2020", "6");
        assert (1 == takesDAO.modify(takes));
    }

    /**
     * Method: infoList(String student_id, String course_id, String section_id, String semster, String year)
     */
    @Test
    public void testInfoList() throws Exception {
        String student_id = "9";
        String course_id = "9";
        String section_id = "9";
        String semester = "1";
        String year = "2020";
        System.out.println(takesDAO.infoList(student_id,course_id,section_id,semester,year));
    }

    /**
     * Method: delete(String student_id, String course_id, String section_id, String semster, String year)
     */
    @Test
    public void testDelete() throws Exception {
        String student_id = "9";
        String course_id = "9";
        String section_id = "9";
        String semester = "1";
        String year = "2020";
        Takes takes=new Takes(student_id,course_id,section_id,semester,year,"");
        assert (1 == takesDAO.delete(takes));
    }




} 
