package main;

import main.service.TimeControlService;
import main.util.SqlUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println(System.getProperty("user.dir"));
        try {
            InputStream in=getClass().getResourceAsStream("initial.properties");
            Properties properties=new Properties();
            properties.load(in);
            TimeControlService timeControlService=TimeControlService.getInstance();
            timeControlService.setCanSelect(properties.getProperty("canSelect").equals("1"));
            timeControlService.setCanDelete(properties.getProperty("canDelete").equals("1"));
            timeControlService.setSemester(Integer.parseInt(properties.getProperty("sys_semester")));
            timeControlService.setYear(Integer.parseInt(properties.getProperty("sys_year")));
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("这个地方被调用了");
        System.out.println(System.getProperty("user.dir"));
        try {
            Properties properties=new Properties();
//            ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
//            String name="initial.properties";
//            if(classLoader.getResource(name)==null) {
//                System.out.println("文件没找到");
//                return;
//            }
//            String path=classLoader.getResource("initial.properties").getPath();
            InputStream in=getClass().getResourceAsStream("initial.properties");
            properties.load(in);
            OutputStream out=new FileOutputStream("initial.properties");
            TimeControlService timeControlService=TimeControlService.getInstance();
            properties.setProperty("canSelect",timeControlService.isCanSelect()?"1":"0");
            properties.setProperty("canDelete",timeControlService.isCanDelete()?"1":"0");
            properties.setProperty("sys_semester",timeControlService.getSemester()+"");
            properties.setProperty("sys_year",timeControlService.getYear()+"");
            System.out.println(properties);
            properties.store(out,"update");
            out.close();
            in.close();
            SqlUtil.createCon().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
