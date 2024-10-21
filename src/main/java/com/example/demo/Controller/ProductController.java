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

import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductService prodServ;

    @PostMapping // Ensure this matches the URL used in Axios
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return prodServ.saveProduct(product) != null
            ? ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully!") // 201 Created
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the Product!"); // 500 Internal Server Error
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = prodServ.getAllProduct();
        return ResponseEntity.ok(products); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable int id) {
        Optional<Product> product = prodServ.getProductById(id);
        return product.isPresent()
            ? ResponseEntity.ok(product) // 200 OK
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty()); // 404 Not Found
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable int id, @RequestBody Product pro) {
        Product updatedProduct = prodServ.updateProductById(id, pro);
        return updatedProduct != null
            ? ResponseEntity.ok(updatedProduct) // 200 OK
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id) {
        prodServ.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully!"); // 204 No Content
    }
}
