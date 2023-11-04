package com.adam.buzas.onlab.main.restcontrollers;

import com.adam.buzas.onlab.main.model.Category;
import com.adam.buzas.onlab.main.model.ResponseText;
import com.adam.buzas.onlab.main.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/kategoriak")
    public List<Category> getAllCategories(){
        List<Category> list = new ArrayList<>();
        for(Category category : categoryService.getAll()){
            list.add(category);
        }
        return list;
    }

    @GetMapping("/kategoriak/name")
    public ResponseEntity<?> getBookByTitle(@RequestBody String name){
        Optional<Category> categoryOpt = categoryService.getcategoryByName(name);
        if(!categoryOpt.isEmpty()){
            return ResponseEntity.ok(categoryOpt.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There aren't any category in the system with this name!");
        }
    }

    @GetMapping("/kategoriak/{id}")
    public Optional<Category> getCategoryById(@PathVariable("id") int id){
        return categoryService.getCategory(id);
    }

    @PostMapping("/kategoriak")
    public ResponseEntity<?> createCategory(@RequestBody String category){
        if(category.charAt(0) == '"' && category.charAt(category.length()-1) == '"'){
            category = category.substring(1, category.length()-1);
        }
        if(categoryService.isHaveCategoryWithName(category)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseText("There is a same category in the db!"));
        }
        categoryService.newCategory(category);
        return ResponseEntity.ok(new ResponseText("Success!"));
    }

    @PutMapping("/kategoriak/{id}")
    public Category updateCategory(@PathVariable("id") int id,
                                   @RequestBody Category category) {
        category.setId(id);
        return categoryService.newCategory(category.getName());
    }

    @DeleteMapping("/kategoriak/{id}")
    public void deleteCategory(@PathVariable("id") int id){
        categoryService.deleteKategoria(id);
    }
}
