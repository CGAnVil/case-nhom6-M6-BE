package com.codegym.dto.request;

import com.codegym.model.Category;
import com.codegym.model.PostState;
import com.codegym.model.Status;
import com.codegym.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

    private String title;

    private LocalDate dateLastFix = LocalDate.now();

    private String content;

    private String description;

    private MultipartFile avatarPost;

    private Category category;

    private User user;

    private Status status;

    private PostState state;
}
