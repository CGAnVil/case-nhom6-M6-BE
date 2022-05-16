package com.codegym.controller.Post;



import com.codegym.dto.request.PostForm;
import com.codegym.model.Post;
import com.codegym.model.Status;
import com.codegym.service.post.IPostService;
import com.codegym.service.status.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {


    @Value("${upload.pathPost}")
    private String uploadPath;

    @Autowired
    private IPostService postService;

    @Autowired
    private IStatusService statusService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllPost() {
        Iterable<Post> posts = postService.findAll();
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findByIdPost(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }


    @GetMapping("status")
    public ResponseEntity<Iterable<Status>> findAllStatus(){
        Iterable <Status> statuses = statusService.findAll();
        if (!statuses.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statuses,HttpStatus.OK);
    }




   @PostMapping
   public ResponseEntity<Post> createNewPost(@ModelAttribute PostForm postForm){
       MultipartFile avatarPost = postForm.getAvatarPost();
       if(avatarPost.getSize() !=0){
           String filename = postForm.getAvatarPost().getOriginalFilename();
           long currentTime = System.currentTimeMillis();
           filename = currentTime + filename;

           try {
               FileCopyUtils.copy(postForm.getAvatarPost().getBytes(), new File(uploadPath + filename));
           } catch (IOException e) {
               e.printStackTrace();
           }

           Post post = new Post(postForm.getDateLastFix(), postForm.getTitle(), postForm.getContent(), postForm.getDescription(), filename, postForm.getCategory(),postForm.getUser(), postForm.getStatus());
           return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
       }else{
           String filename = "";
           Post post = new Post(postForm.getDateLastFix(), postForm.getTitle(), postForm.getContent(), postForm.getDescription(), filename, postForm.getCategory(),postForm.getUser(), postForm.getStatus());
           return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
       }
   }


    @GetMapping("users/{id}")
    public ResponseEntity<Iterable<Post>> showAllByUser(@PathVariable("id") Long idUser) {
        Iterable<Post> posts = postService.findPostByIdUser(idUser);
        if (!posts.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }



    @PostMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @ModelAttribute Post postEdit) {
        LocalDate today = LocalDate.now();
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post post = postOptional.get();
        post.setTitle(postEdit.getTitle());
        post.setDateCreate(today);
        post.setDescription(postEdit.getDescription());
        post.setAvatarPost(postEdit.getAvatarPost());
        post.setUser(postEdit.getUser());
        post.setStatus(postEdit.getStatus());
        post.setCategory(postEdit.getCategory());
        postService.save(postEdit);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (!optionalPost.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.removeById(id);
        return new ResponseEntity<>(optionalPost.get(), HttpStatus.OK);
    }

    @GetMapping("findStatus/{id}")
    public ResponseEntity<Iterable<Post>> findPostStatus(@PathVariable Long id) {
        Iterable<Post> posts = postService.findPostByIdStatus(id);
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }



}
