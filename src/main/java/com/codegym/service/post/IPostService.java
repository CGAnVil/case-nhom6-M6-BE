package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {

    Iterable<Post> findPostByIdStatus (Long statusId);

    Iterable<Post> findPostByIdUser(Long idUser);


}
