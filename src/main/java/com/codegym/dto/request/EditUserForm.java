package com.codegym.dto.request;

import com.codegym.model.Role;
import com.codegym.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserForm {


    private String email;

    private String fullName;

    private String address;

    private String phone;

    private MultipartFile avatar;


}
