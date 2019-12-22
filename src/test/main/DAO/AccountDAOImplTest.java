package test.main.DAO; 

import main.DAO.AccountDAO;
import main.DAO.DAOFactory;
import main.entity.Account;
import main.util.EncryptUtil;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* AccountDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/21/2019</pre> 
* @version 1.0 
*/ 
public class AccountDAOImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 
private AccountDAO accountDAO= DAOFactory.getAccountDAOInstance();
/** 
* 
* Method: append(Account account) 
* 
*/ 
@Test
public void testAppend() throws Exception {
    Account account=new Account("T000002", EncryptUtil.md5("T000002","12345678"));
    assert (1 == accountDAO.append(account));

} 

/** 
* 
* Method: delete(String ac_id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 

    String account = "T000002";
    assert (1==accountDAO.delete(account));
} 

/** 
* 
* Method: modify(Account account) 
* 
*/ 
@Test
public void testModify() throws Exception {
    Account account=new Account("root","12345");
    assert (1==accountDAO.modify(account));
} 

/** 
* 
* Method: infoList(String ac_id) 
* 
*/ 
@Test
public void testInfoList() throws Exception { 

    String acc = "root";
    assert (null == accountDAO.infoList(acc));
} 


} 
