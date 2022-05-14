package com.codegym.dto.request;

import com.codegym.model.Category;
import com.codegym.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

    private String title;

    private String content;

    private String description;

    private MultipartFile avatarPost;

    private Category category;

    private Status status;
}
