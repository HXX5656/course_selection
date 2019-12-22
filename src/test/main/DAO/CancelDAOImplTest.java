package test.main.DAO;

import main.DAO.CancelDAO;
import main.DAO.DAOFactory;
import main.entity.Cancel;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * CancelDAOImpl Tester.
 *
 * @author <Authors name>
 * @since <pre>12/04/2019</pre>
 * @version 1.0
 */
public class CancelDAOImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private CancelDAO cancelDAO= DAOFactory.getCancelDAOINstance();
    /**
     *
     * Method: append(Cancel cancel)
     *
     */
    @Test
    public void testAppend() throws Exception {

        Cancel cancel=new Cancel("7","9","9","1","2020");
        assert (1==cancelDAO.append(cancel));
    }

    /**
     *
     * Method: modify(Cancel cancel)
     *
     */
    @Test
    public void testModify() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: infoList(String student_id, String course_id, String section_id, String semester, String year)
     *
     */
    @Test
    public void testInfoList() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: delete(Cancel cancel)
     *
     */
    @Test
    public void testDelete() throws Exception {
        Cancel cancel=new Cancel("","1","1","","");
        assert (2==cancelDAO.delete(cancel));
    }

    @Test
    public void delete_by_section() throws Exception {
        cancelDAO.delete_by_section("9","9","1","2020");
    }
}
