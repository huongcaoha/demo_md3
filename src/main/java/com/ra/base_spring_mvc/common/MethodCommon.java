package com.ra.base_spring_mvc.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MethodCommon {
    public static String uploadImage(MultipartFile file) {
        if(file.getSize() > 0){
            String filePath = "C:\\Users\\dell\\IdeaProjects\\Btvn_Module3\\ecommerce\\src\\main\\webapp\\uploads\\" + file.getOriginalFilename() ;
            File fl = new File(filePath);
            if(!fl.exists()){
                try {
                    fl.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                file.transferTo(fl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return file.getOriginalFilename();
        }
        return null ;
    }
}
