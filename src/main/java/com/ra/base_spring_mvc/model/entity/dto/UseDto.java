package com.ra.base_spring_mvc.model.entity.dto;

import com.ra.base_spring_mvc.model.entity.Role;
import com.ra.base_spring_mvc.validator.user.PhoneUnique;
import com.ra.base_spring_mvc.validator.user.UsernameUnique;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UseDto {

    @Column(name = "username",unique = true)
    @Size(min = 6,max = 100)
    @UsernameUnique
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must not contain special characters.")
    private String username;

    @Email
    private String email ;

    @NotBlank(message = "fullname can not blank")
    private String fullName;

    private String password ;

    @Column(name = "phone",unique = true)
    @Pattern(regexp = "^0[35789][0-9]{8}$" , message = "phone not fomat !")
    @PhoneUnique
    private String phone ;

    @NotBlank(message = "address cannot blank")
    private String address ;
}
