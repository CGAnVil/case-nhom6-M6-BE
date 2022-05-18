package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.model.UserStatus;
import com.codegym.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService{
    @Autowired
    private IPostRepository postRepository;


    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
          postRepository.deleteById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Iterable<Post> findPostByIdStatus(Long statusId) {
        return  postRepository.findAllByStatus_Id(statusId);
    }

    @Override
    public void blockPost(Long post_id) {
        postRepository.blockPost(post_id);
    }

    @Override
    public void unblockPost(Long post_id) {
        postRepository.unblockPost(post_id);
    }


    @Override
    public void changeStatus(Long id) {
        postRepository.changeStatus(id);
    }

    @Override
    public void changeStatusPublic(Long id) {
        postRepository.changeStatusPublic(id);
    }


    @Override
    public Iterable<Post> findPostByIdUser(Long idUser) {
        return postRepository.findAllByUser_Id(idUser);
    }


}
