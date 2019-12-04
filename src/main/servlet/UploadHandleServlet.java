package main.servlet;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet("/UploadHandleServlet")
public class UploadHandleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入servlet");
        DiskFileItemFactory fac=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(fac);
        upload.setHeaderEncoding("UTF-8");

        List fileList=null;
        try {

            fileList=upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator it=fileList.iterator();
        while(it.hasNext()) {
            Object obit=it.next();
            if(obit instanceof DiskFileItem) {
                DiskFileItem item=(DiskFileItem) obit;
                String fileName=item.getName();
                if (fileName != null) {
                    String fName = item.getName().substring(
                            item.getName().lastIndexOf("\\") + 1);
                    String formatName = fName
                            .substring(fName.lastIndexOf(".") + 1);// 获取文件后缀名
                    String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
//					String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
                    File expsfile = new File(savePath);
                    if (!expsfile.exists()) {// 创建文件夹
                        expsfile.mkdirs();
                    }

                    String realPath = savePath+"/"+ UUID.randomUUID().toString()+"."+formatName;
                    System.out.println("realPath:"+realPath);
                    BufferedInputStream bis = new BufferedInputStream(
                            item.getInputStream());// 获得文件输入流
                    BufferedOutputStream outStream = new BufferedOutputStream(
                            new FileOutputStream(new File(realPath)));// 获得文件输出流
                    Streams.copy(bis, outStream, true);// 开始把文件写到你指定的上传文件夹
                    // 上传成功，则插入数据库
                    File file = new File(realPath);
                    if (file.exists()) {
//						request.setAttribute("realPath", realPath);
//						request.getRequestDispatcher("/user/insertUserByExcelPath").forward(request, response);
                        // 返回文件路径
                        String imageName = file.getName();
                        String json = "[{\"filePath\":\""
                                + realPath
                                + "\",\"imageName\":\"" + imageName + "\"}]";
                        response.reset();
                        response.setContentType("import");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(json);
//						response.getWriter().write(realPath);
                        response.getWriter().flush();
                    }

                }

            }
        }

    }
}
