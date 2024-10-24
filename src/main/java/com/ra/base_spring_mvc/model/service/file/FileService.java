package com.ra.base_spring_mvc.model.service.file;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    public String uploadImage(MultipartFile multipartFile){

        String uploadPath = "C:\\Users\\dell\\IdeaProjects\\Btvn_Module3\\project_module3\\src\\main\\webapp\\uploads\\";
        System.out.println(uploadPath);
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdir();
        }
        System.out.println(multipartFile);

        String fileImage = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(uploadPath+File.separator+fileImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileImage;
    }
}
