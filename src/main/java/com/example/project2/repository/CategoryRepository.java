package com.example.project2.repository;

import com.example.project2.entity.Books;
import com.example.project2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long>{
    List<Books> getBooksByCategoryName(String categoryName);
}
