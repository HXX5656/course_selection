package main;

import main.service.TimeControlService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Listener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            InputStream in=getClass().getResourceAsStream("initial");
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
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            Properties properties=new Properties();
            ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
            String name="initial.properties";
            if(classLoader.getResource(name)==null) {
                return;
            }
            String path=classLoader.getResource("initial.properties").getPath();
            InputStream in=classLoader.getResource("initial.properties").openStream();
            properties.load(in);
            OutputStream out=new FileOutputStream(path);
            TimeControlService timeControlService=TimeControlService.getInstance();
            properties.setProperty("canSelect",timeControlService.isCanSelect()?"1":"0");
            properties.setProperty("canDelete",timeControlService.isCanDelete()?"1":"0");
            properties.setProperty("sys_semester",timeControlService.getSemester()+"");
            properties.setProperty("sys_year",timeControlService.getYear()+"");
            in.close();
            properties.store(out,"update");
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
