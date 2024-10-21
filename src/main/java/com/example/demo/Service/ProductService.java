package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
@Service("prodService")
public class ProductService {
	@Autowired
    private ProductRepository prodRepo;
    
    public Product saveProduct(Product prod) {
        return prodRepo.save(prod);
    }
    public List<Product> getAllProduct(){
    	List<Product> list=prodRepo.findAll();
    	return list;
    }
    public Optional<Product> getProductById(int id) {
        return prodRepo.findById(id);
    }
    
    public Product updateProductById(int id, Product updatedProduct) {
        Product cat = prodRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        cat.setName(updatedProduct.getName());
        return prodRepo.save(cat);
    }

    public String deleteProductById(Integer id) {
        Optional<Product> pro = prodRepo.findById(id);
        if (pro.isPresent()) {
        	prodRepo.deleteById(id);
            return "Product deleted successfully!";
        } else {
            return "Product not found!";
        }
    }

}
