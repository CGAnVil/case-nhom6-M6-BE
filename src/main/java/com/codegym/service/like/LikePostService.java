package com.codegym.service.like;

import com.codegym.model.LikePost;
import com.codegym.repository.ILikePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikePostService implements ILikePostService{
    @Autowired
    private ILikePostRepository likePostRepository;

    @Override
    public Iterable<LikePost> findAll() {
        return likePostRepository.findAll();
    }

    @Override
    public Optional<LikePost> findById(Long id) {
        return likePostRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        likePostRepository.deleteById(id);
    }

    @Override
    public LikePost save(LikePost likePost) {
        return likePostRepository.save(likePost);
    }

    @Override
    public Integer countLikeByIdPost(Long idPost) {
        return likePostRepository.countLikeByIdPost(idPost);
    }

    @Override
    public Optional<LikePost> findLikePostByIdPostAndIdUser(Long idPost, Long idUser) {
        return likePostRepository.findLikePostByIdPostAndIdUser(idPost, idUser);
    }

    @Override
    public void deleteLikePost(LikePost likePost) {
        likePostRepository.delete(likePost);
    }
}
