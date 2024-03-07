package com.example.project2.repository;

import com.example.project2.entity.Books;
import com.example.project2.entity.Loans;
import com.example.project2.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loans,Long> {

    Loans findByBooksAndReturnDateIsNull(Books books);

    List<Loans> findByMembers_NameAndReturnDateIsNull(String memberName);


}
