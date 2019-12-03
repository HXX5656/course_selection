package test.main.DAO;

import main.DAO.DAOFactory;
import main.DAO.TeachesDAO;
import main.entity.Teaches;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * TeachesDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/03/019</pre>
 */
public class TeachesDAOImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    public static TeachesDAO teachesDAO = DAOFactory.getTeachesDAOInstance();

    /**
     * Method: append(Teaches teaches)
     */
    @Test// Need to satisfy FK constraints before test
    public void testAppend() throws Exception {
        Teaches teaches = new Teaches("9", "9", "9", "1", "2020");
        assert (1 == teachesDAO.append(teaches));
    }


    /**
     * Method: modify(Teaches teaches)
     */
    @Test
    public void testModify() throws Exception {
        assert (1 == 1);
    }

    /**
     * Method: infoList(String teacher_id, String course_id, String section_id, String semester, String year)
     */
    @Test
    public void testInfoList() throws Exception {
        String teacher_id = "9";
        String course_id = "9";
        String section_id = "9";
        String semester = "1";
        String year = "2020";
        System.out.println(teachesDAO.infoList(teacher_id, course_id, section_id, semester, year));
    }

    /**
     * Method: delete(String teacher_id, String course_id, String section_id, String semester, String year)
     */
    @Test
    public void testDelete() throws Exception {
        String teacher_id = "9";
        String course_id = "9";
        String section_id = "9";
        String semester = "1";
        String year = "2020";
        assert (1 == teachesDAO.delete(teacher_id, course_id, section_id, semester, year));
    }


} 
