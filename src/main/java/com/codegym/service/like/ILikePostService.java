package com.codegym.service.like;

import com.codegym.model.LikePost;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface ILikePostService extends IGeneralService<LikePost> {
    Integer countLikeByIdPost(Long idPost);

    Optional<LikePost> findLikePostByIdPostAndIdUser(Long idPost, Long idUser);

    void deleteLikePost(LikePost likePost);
}
