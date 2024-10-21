package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Category;
import com.example.demo.Service.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from React app
public class CategoryController {

    @Autowired
    CategoryService catService;

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Category cat) {
        return catService.saveCat(cat) != null
            ? ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully!") // 201 Created
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the Category!"); // 500 Internal Server Error
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = catService.getAllCategory();
        return ResponseEntity.ok(categories); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable int id) {
        Optional<Category> category = catService.getCategoryById(id);
        return category.isPresent()
            ? ResponseEntity.ok(category) // 200 OK
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty()); // 404 Not Found
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable int id, @RequestBody Category category) {
        Category updatedCategory = catService.updateCategoryById(id, category);
        return updatedCategory != null
            ? ResponseEntity.ok(updatedCategory) // 200 OK
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable int id) {
        catService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category deleted successfully!"); // 204 No Content
    }
}
