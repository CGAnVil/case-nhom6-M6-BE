package com.codegym.service.comment;

import com.codegym.model.CommentPost;
import com.codegym.service.IGeneralService;

public interface ICommentPostService extends IGeneralService<CommentPost> {
    Iterable<CommentPost> getAllCommentByIdPost(Long idPost);

    Iterable<CommentPost> getAllReplyComment(Long idPost, Long idComment);
}
