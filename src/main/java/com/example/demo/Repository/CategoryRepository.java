package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Category;
@Repository("catRepo")
public interface CategoryRepository extends JpaRepository<Category,Integer>{

}
