package test.main.service; 

import main.service.ImportService;
import main.service.ServiceFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ImportServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/04/2019</pre> 
* @version 1.0 
*/ 
public class ImportServiceImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 
private ImportService importService= ServiceFactory.getImportServiceInstance(null);
/** 
* 
* Method: importStudent() 
* 
*/ 
@Test
public void testImportStudent() throws Exception { 

    importService.import_select();
} 


} 
