package com.ra.base_spring_mvc.model.entity.dto;



import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BannerDto {


    private String bannerName;
    @NotNull(message = "file not null !")
    private MultipartFile image ;
    private String location ;

    private String content ;

}
