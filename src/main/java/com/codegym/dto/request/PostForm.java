package com.codegym.dto.request;

import com.codegym.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

    private Long id;

    private Status statusForm;
}
