package xyz.xcye.typoraupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.xcye.typoraupload.utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/18 00:03
 */


@RequestMapping("/typora")
@RestController
public class TyporaController {

    @Autowired
    private FileUtils fileUtils;

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile[] files) {
        List<Map> list = new ArrayList<>();
        for (MultipartFile file : files) {
            Map<String,Object> map = new HashMap<>();
            InputStream inputStream = null;
            String saveFileSuccessPath = "";
            try {
                inputStream = file.getInputStream();
                String originalFilename = file.getOriginalFilename();
                saveFileSuccessPath = fileUtils.saveFile(inputStream, originalFilename);
            } catch (IOException e) {
                e.printStackTrace();
                saveFileSuccessPath = "";
            }

            map.put("filePath",saveFileSuccessPath);
            list.add(map);
        }

        return list;
    }

    @PostMapping("/upload/single")
    public String uploadSingle(@RequestParam("file") MultipartFile file) {
        InputStream inputStream = null;
        String saveFileSuccessPath = "";
        try {
            inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            saveFileSuccessPath = fileUtils.saveFile(inputStream, originalFilename);
        } catch (IOException e) {
            e.printStackTrace();
            saveFileSuccessPath = "";
        }

        return saveFileSuccessPath;
    }

}
