package com.accolite.sim.controller;


import com.accolite.sim.service.FileProcessingService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class FileController {

    @Autowired
    FileProcessingService fileProcessingService;


    @PostMapping
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        if (file == null) {
            throw new RuntimeException("Please upload a file");
        }
        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename();
        String name = file.getName();
        String contentType = file.getContentType();
//        long size = file.getSize();
//        System.out.println("size = " + size);
//        System.out.println("contentType = " + contentType);
//        System.out.println("name = " + name);
//        System.out.println("originalName = " + originalName);

        //do processing in service layer
        this.fileProcessingService.processFile(inputStream);

        Map<String, String> map = new HashMap<>();
        map.put("File Name", name);
        map.put("Original File Name", originalName);

        return map;
    }

}
