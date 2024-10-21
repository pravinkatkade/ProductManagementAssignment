package com.example.demo.Service;

import com.example.demo.Entity.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.CategoryRepository;


@Service("catService")
public class CategoryService {
	@Autowired
    private CategoryRepository catRepo;
    
    public Category saveCat(Category cat) {
        return catRepo.save(cat);
    }
    
    public List<Category> getAllCategory(){
    	List<Category> list=catRepo.findAll();
    	return list;
    }
    public Optional<Category> getCategoryById(int id) {
        return catRepo.findById(id);
    }
    public Category updateCategoryById(int id, Category updatedCategory) {
        Category cat = catRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        cat.setName(updatedCategory.getName());
        return catRepo.save(cat);
    }

    public String deleteCategoryById(Integer id) {
        Optional<Category> cat = catRepo.findById(id);
        if (cat.isPresent()) {
            catRepo.deleteById(id);
            return "Category deleted successfully!";
        } else {
            return "Category not found!";
        }
    }

}
