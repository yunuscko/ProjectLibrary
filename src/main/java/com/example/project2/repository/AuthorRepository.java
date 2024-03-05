package com.example.project2.repository;

import com.example.project2.entity.Authors;
import com.example.project2.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Authors,Long> {

    Optional<Authors> getAuthorsByAuthorsName(String authorName);

}
