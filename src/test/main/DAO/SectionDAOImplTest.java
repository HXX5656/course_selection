package test.main.DAO;

import main.DAO.DAOFactory;
import main.DAO.SectionDAO;
import main.entity.Section;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * SectionDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/032019</pre>
 */
public class SectionDAOImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    public static SectionDAO sectionDAO = DAOFactory.getSectionDAOInstance();

    /**
     * Method: append(Section section)
     */
    @Test
    public void testAppend() throws Exception {
        Section section = new Section("1", "2", "2", "2019", "9", "10", "100", null);
        assert (1 == sectionDAO.append(section));
    }


    /**
     * Method: modify(Section section)
     */
    @Test
    public void testModify() throws Exception {
        Section section = new Section("1","1","2","2019","4","7","50",null);
        assert (1 == sectionDAO.modify(section));
    }

    /**
     * Method: infoList(String course_id, String section_id, String semester, String year)
     */
    @Test
    public void testInfoList() throws Exception {
        String course_id = "1";
        String section_id = "1";
        String semester = "2";
        String year = "2019";
        System.out.println(sectionDAO.infoList(course_id,section_id,semester,year));
    }

    /**
     * Method: delete(String course_id, String section_id, String semester, String year)
     */
    @Test
    public void testDelete() throws Exception {
        String course_id = "1";
        String section_id = "2";
        String semester = "2";
        String year = "2019";
        assert (1 == sectionDAO.delete(course_id, section_id, semester, year));
    }


} 
