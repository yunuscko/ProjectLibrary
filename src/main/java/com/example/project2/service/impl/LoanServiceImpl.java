package com.example.project2.service.impl;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.LoanDto;
import com.example.project2.dtos.MembersDto;
import com.example.project2.entity.Books;
import com.example.project2.entity.Loans;
import com.example.project2.entity.Members;
import com.example.project2.repository.BookRepository;
import com.example.project2.repository.LoanRepository;
import com.example.project2.repository.MemberRepository;
import com.example.project2.service.LoanService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void barrowBook(Long memberId, Long bookId) {
        Members members = memberRepository.getMemberById(memberId);
        Books books = bookRepository.getBookById(bookId);

        if (books.isBorrowed()) {
            System.out.println("Kitap zaten ödünç alınmış");
        } else {
            Loans loan = new Loans();
            loan.setMembers(members);
            loan.setBooks(books);
            loan.setLoadDate(LocalDate.now());

            loanRepository.save(loan);

            // Kitabın durumunu güncelle
            books.setBorrowed(true);
            bookRepository.save(books);

            System.out.println("Kitap ödünç verildi");
        }
    }

    @Override
    public List<LoanDto> getAllLoans() {
        List<Loans> loansList = loanRepository.findAll();

        List<LoanDto> bookDtos = loansList.stream().
                map(loans -> modelMapper.map(loans, LoanDto.class))
                .collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public void returnBookById(Long loanId) {
        Optional<Loans> optionalLoan = loanRepository.findById(loanId);

        if (optionalLoan.isPresent()) {
            Loans loan = optionalLoan.get();

            if (loan.getReturnDate() == null) {
                loan.setReturnDate(LocalDate.now());
                Long daysBetween = ChronoUnit.DAYS.between(loan.getLoadDate(), LocalDate.now());
                if (daysBetween > 15) {
                    Long debt = (daysBetween - 15) * 2;
                    System.out.println("Kitap geciktirme borcunuz " + debt + " TL");
                } else {
                    System.out.println("Kitabı zamanında teslim ettiğiniz için teşekkür ederiz");
                }

                loanRepository.save(loan);
            } else {
                throw new IllegalArgumentException("İade zaten yapılmış");
            }
        } else {
            throw new IllegalArgumentException("Ödünç alınan kitap bulunamadı");
        }
    }
}

