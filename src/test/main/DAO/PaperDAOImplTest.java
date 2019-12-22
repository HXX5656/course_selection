package test.main.DAO;

import main.DAO.DAOFactory;
import main.DAO.PaperDAO;
import main.entity.Paper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * PaperDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/04/2019</pre>
 */
public class PaperDAOImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private static PaperDAO paperDAO = DAOFactory.getPaperDAOInstance();

    /**
     * Method: append(Paper paper)
     */
    @Test
    public void testAppend() throws Exception {
        Paper paper = new Paper("9", "open", "11", "9");
        assert (1 == paperDAO.append(paper));
    }


    /**
     * Method: modify(Paper paper)
     */
    @Test
    public void testModify() throws Exception {
        Paper paper = new Paper("9","close","11","9");
        assert (1 == paperDAO.modify(paper));
    }

    /**
     * Method: infoList(String exam_id)
     */
    @Test
    public void testInfoList() throws Exception {
        String exam_id = "9";
        System.out.println(paperDAO.infoList("9"));
    }


    /**
     * Method: delete(Paper paper)
     */
    @Test
    public void testDelete() throws Exception {
        Paper paper = new Paper("9", "close", "11", "9");
        assert (1 == paperDAO.delete(paper));
    }

    @Test
    public void testDeleteById() throws Exception {
        assert (1 == paperDAO.delete_by_examID("9"));
    }
}
