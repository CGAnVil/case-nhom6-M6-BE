package com.codegym.controller.user;

import com.codegym.model.User;
import com.codegym.dto.request.EditUserForm;
import com.codegym.security.jwt.JwtAuthTokenFilter;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.service.Account.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Value("${upload.pathUser}")
    private String uploadPath;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll(@RequestParam(name = "q") Optional<String> q) {
        Iterable<User> users = userService.findAll();
        if (q.isPresent()) {
            users = userService.findUsersByUserNameContaining(q.get());
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.removeById(id);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> updateUser(PathVariable Long id,@ModelAttribute EditUserForm editUserForm) {
        Optional<User> currentUser = userService.findById(id);
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = currentUser.get();
        String filename;
        MultipartFile avatar = editUserForm.getAvatar();
        if (avatar.getSize() != 0) {
            filename = editUserForm.getAvatar().getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            filename = currentTime + filename;
            user.setAvatar(filename);
            try {
                FileCopyUtils.copy(avatar.getBytes(), new File(uploadPath + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setEmail(editUserForm.getEmail());
            user.setFullName(editUserForm.getFullName());
            user.setAddress(editUserForm.getAddress());
            user.setPhone(editUserForm.getPhone());
            user.setAvatar(filename);
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            user.setEmail(editUserForm.getEmail());
            user.setFullName(editUserForm.getFullName());
            user.setAddress(editUserForm.getAddress());
            user.setPhone(editUserForm.getPhone());
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

    }

}
