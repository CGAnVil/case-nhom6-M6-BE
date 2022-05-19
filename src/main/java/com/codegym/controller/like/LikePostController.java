package com.codegym.controller.like;

import com.codegym.model.LikePost;
import com.codegym.service.Account.user.IUserService;
import com.codegym.service.like.ILikePostService;
import com.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/likes")
public class LikePostController {
    @Autowired
    private ILikePostService likePostService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @GetMapping("/{idPost}")
    public ResponseEntity<Integer> countLikeByIdPost(@PathVariable Long idPost) {
        return new ResponseEntity<>(likePostService.countLikeByIdPost(idPost), HttpStatus.OK);
    }

    @GetMapping("/{idPost}/{idUser}")
    public ResponseEntity<Integer> likeUnlikePost(@PathVariable Long idPost, @PathVariable Long idUser) {
        Optional<LikePost> likePostOptional = likePostService.findLikePostByIdPostAndIdUser(idPost, idUser);
        if (likePostOptional.isPresent()) {
            likePostService.deleteLikePost(likePostOptional.get());
        } else {
            likePostService.save(new LikePost(userService.findById(idUser).get(), postService.findById(idPost).get()));
        }
        Integer totalLike = likePostService.countLikeByIdPost(idPost);
        return new ResponseEntity<>(totalLike, HttpStatus.OK);
    }
}
