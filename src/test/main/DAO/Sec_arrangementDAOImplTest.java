package test.main.DAO;

import main.DAO.DAOFactory;
import main.DAO.Sec_arrangementDAO;
import main.DAO.Sec_arrangementDAOImpl;
import main.entity.Sec_arrangement;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Sec_arrangementDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/042019</pre>
 */
public class Sec_arrangementDAOImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private static Sec_arrangementDAO sec_arrangementDAO = DAOFactory.getSecArrangementDAOInstance();

    /**
     * Method: append(Sec_arrangement sec_arrangement)
     */
    @Test
    public void testAppend() throws Exception {
        Sec_arrangement sec_arrangement = new Sec_arrangement("11", "9", "9", "9", "1", "2020");
        assert (1 == sec_arrangementDAO.append(sec_arrangement));
    }

    /**
     * Method: modify(Sec_arrangement sec_arrangement)
     */
    @Test
    public void testModify() throws Exception {
        Sec_arrangement sec_arrangement = new Sec_arrangement("11", "9", "9", "10", "1", "2019");
        assert (1 == sec_arrangementDAO.modify(sec_arrangement));
    }

    /**
     * Method: infoList(String time_slot_id, String room_id)
     */
    @Test
    public void testInfoList() throws Exception {
        String time_slot_id = "11";
        String room_id = "9";
        System.out.println(sec_arrangementDAO.infoList(time_slot_id, room_id));
    }

    /**
     * Method: delete(String time_slot_id, String room_id)
     */
    @Test
    public void testDelete() throws Exception {
        String time_slot_id = "11";
        String room_id = "9";
        assert (1 == sec_arrangementDAO.delete(time_slot_id, room_id));
    }



    @Test
    public void testFindRoom ()throws Exception{

        assert (sec_arrangementDAO.findRoom("9","9","1","2020").get(0).equals("9"));
    }
} 
