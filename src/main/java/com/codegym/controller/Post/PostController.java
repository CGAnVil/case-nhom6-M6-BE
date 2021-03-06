package com.codegym.controller.Post;


import com.codegym.dto.request.PostForm;
import com.codegym.model.Post;
import com.codegym.model.PostState;
import com.codegym.model.Status;
import com.codegym.service.post.IPostService;
import com.codegym.service.status.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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


    @Value("${upload-path}")
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

    @GetMapping("/{id}/users")
    public ResponseEntity<Iterable<Post>> findAllPostByUser(@PathVariable Long id) {
        Iterable<Post> posts = postService.findPostByIdUser(id);
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
    public ResponseEntity<Iterable<Status>> findAllStatus() {
        Iterable<Status> statuses = statusService.findAll();
        if (!statuses.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = {"application/json"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, headers = ("content-type=multipart/*"))
    public ResponseEntity<?> importContractRateFile(@ModelAttribute PostForm postForm) throws IOException {
        MultipartFile avatarPost = postForm.getAvatarPost();
        if (avatarPost.getSize() != 0) {
            String filename = postForm.getAvatarPost().getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            filename = currentTime + filename;

            try {
                FileCopyUtils.copy(postForm.getAvatarPost().getBytes(), new File(uploadPath + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Post post = new Post(postForm.getDateLastFix(), postForm.getTitle(), postForm.getContent(), postForm.getDescription(), filename, postForm.getCategory(), postForm.getUser(), postForm.getStatus());
            post.setState(new PostState(1L, "Active"));
            return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
        } else {
            String filename = "";
            Post post = new Post(postForm.getDateLastFix(), postForm.getTitle(), postForm.getContent(), postForm.getDescription(), filename, postForm.getCategory(), postForm.getUser(), postForm.getStatus());
            post.setState(new PostState(1L, "Active"));
            return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
        }
    }


//   @PostMapping
//   @RequestMapping(value = "/", method = RequestMethod.POST, produces = {"application/json"},
//           consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, headers = ("content-type=multipart/*"))
//   public ResponseEntity<Post> createNewPost(@RequestParam String title, @RequestPart MultipartFile avatarPost){
//        return new ResponseEntity<>(null);
//       MultipartFile avatarPost = postForm.getAvatarPost();
//       if(avatarPost.getSize() !=0){
//           String filename = postForm.getAvatarPost().getOriginalFilename();
//           long currentTime = System.currentTimeMillis();
//           filename = currentTime + filename;
//
//           try {
//               FileCopyUtils.copy(postForm.getAvatarPost().getBytes(), new File(uploadPath + filename));
//           } catch (IOException e) {
//               e.printStackTrace();
//           }
//
//           Post post = new Post(postForm.getDateLastFix(), postForm.getTitle(), postForm.getContent(), postForm.getDescription(), filename, postForm.getCategory(),postForm.getUser(), postForm.getStatus());
//           post.setState(new PostState(1L, "Active"));
//           return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
//       }else{
//           String filename = "";
//           Post post = new Post(postForm.getDateLastFix(), postForm.getTitle(), postForm.getContent(), postForm.getDescription(), filename, postForm.getCategory(),postForm.getUser(), postForm.getStatus());
//           post.setState(new PostState(1L, "Active"));
//           return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
//       }
//   }


    @GetMapping("users/{id}")
    public ResponseEntity<Iterable<Post>> showAllByUser(@PathVariable("id") Long idUser) {
        Iterable<Post> posts = postService.findPostByIdUser(idUser);
        if (!posts.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);

    }

    // c???p nh??t b??i post
    @PostMapping("/{id}")
    public ResponseEntity<Post> editPost(@PathVariable Long id, @ModelAttribute PostForm postForm) {
        Optional<Post> currentPost = postService.findById(id);
        if (!currentPost.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post post = currentPost.get();
        String filename;
        MultipartFile avatarPost = postForm.getAvatarPost();
        if (avatarPost.getSize() != 0) {
            filename = postForm.getAvatarPost().getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            filename = currentTime + filename;
            post.setAvatarPost(filename);
            try {
                FileCopyUtils.copy(avatarPost.getBytes(), new File(uploadPath + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setDateLastFix(postForm.getDateLastFix());
        post.setDescription(postForm.getDescription());
        post.setCategory(postForm.getCategory());
        post.setStatus(postForm.getStatus());
        postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (!optionalPost.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.blockPost(id);
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
