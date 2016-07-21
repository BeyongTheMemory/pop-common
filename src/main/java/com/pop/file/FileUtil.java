package com.pop.file;

/**
 * Created by xugang on 16/7/21.
 */
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {
    /**
     * 记得在springmvc中添加支持文件上传的注解
     * @param file
     * @return
     */
    public static String upload(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String path = "/data/upload/";
//        String fileName = new Date().getTime()+".jpg";
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path+fileName;
    }

    public static void download(String path,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + path);
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            inputStream = new FileInputStream(new File(path));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally{
            //关闭
            try {
                inputStream.close();
                os.close();
            } catch (Exception e) {
            }
        }

    }

}
