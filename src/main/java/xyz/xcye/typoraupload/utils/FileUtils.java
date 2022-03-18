package xyz.xcye.typoraupload.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/18 08:07
 */


@Component
public class FileUtils {
    @Value("${typora.file.url.prefix}")
    private String fileDomain;

    @Value("${typora.save.path}")
    private String typoraSavePath;

    public String saveFile(InputStream inputStream,String fileName) {
        String saveFilePath = typoraSavePath + "/" + fileName;

        File file = new File(saveFilePath);
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());

        if (file.exists()) {
            //已经存在
            file = new File(saveFilePath + "/" + System.currentTimeMillis() + fileSuffix);
        }

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file,true);

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return fileDomain + "/" + fileName;
    }
}
