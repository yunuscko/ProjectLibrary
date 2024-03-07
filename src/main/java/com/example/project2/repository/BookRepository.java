package com.example.project2.repository;


import com.example.project2.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Books,Long> {



    @Query("SELECT b FROM Books b WHERE b.authors.authorsName = :authorName")
    Set<Books> findBooksByAuthorsName(@Param("authorName") String authorName);


    Optional<Books>getBookByTitle(String bookTitle);

    Books getBookById(Long bookId);

}
