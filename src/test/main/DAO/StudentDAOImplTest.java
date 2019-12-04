package test.main.DAO;

import main.DAO.DAOFactory;
import main.DAO.StudentDAO;
import main.entity.Student;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * StudentDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/03/2019</pre>
 */
public class StudentDAOImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private static StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();

    /**
     * Method: append(Student student)
     */
    @Test
    public void testAppend() throws Exception {
        Student student = new Student("3", "b", null, null, "1");
        assert (studentDAO.append(student) == 1);
    }


    /**
     * Method: modify(Student student)
     */
    @Test
    public void testModify() throws Exception {
        Student student = new Student("1", "hxx", "2005-01-13", null, "1");
        assert (studentDAO.modify(student) == 1);
    }

    /**
     * Method: infoList(String student_id)
     */
    @Test
    public void testInfoList() throws Exception {
        String student_id = "1";
        System.out.println(studentDAO.infoList(student_id));
    }

    /**
     * Method: delete(String student_id)
     */
    @Test
    public void testDelete() throws Exception {
        String student_id = "1";
        assert (studentDAO.delete(student_id) == 1);
    }


} 
