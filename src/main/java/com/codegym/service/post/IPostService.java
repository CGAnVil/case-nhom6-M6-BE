package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findPostByIdStatus (Long statusId);

    void blockPost(Long post_id);

    Iterable<Post> findAllPostByUser(Long user_id);
}
