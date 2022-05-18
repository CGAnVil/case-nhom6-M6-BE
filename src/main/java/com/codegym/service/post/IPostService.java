package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {

    Iterable<Post> findPostByIdStatus (Long statusId);

    void blockPost(Long post_id);

    void changeStatus(Long id);

    void changeStatusPublic(Long id);

    Iterable<Post> findPostByIdUser(Long idUser);

    Iterable <Post> findPostByCategory(Long cateId);

}
