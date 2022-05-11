package com.codegym.controller.user;

import com.codegym.model.Category;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll(){
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> save(@ModelAttribute Category category){
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Category> updateById(@PathVariable Long id, @ModelAttribute Category category){
        Optional <Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Category categories = categoryOptional.get();
        categories.setName(category.getName());
        categoryService.save(categories);
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id){
        Optional <Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.removeById(id);
        return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
    }
}
