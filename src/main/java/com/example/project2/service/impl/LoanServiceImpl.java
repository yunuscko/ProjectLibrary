package com.example.project2.service.impl;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.LoanDto;
import com.example.project2.dtos.MembersDto;
import com.example.project2.entity.Books;
import com.example.project2.entity.Loans;
import com.example.project2.entity.Members;
import com.example.project2.repository.BookRepository;
import com.example.project2.repository.LoanRepository;
import com.example.project2.service.LoanService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
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

    @Resource
    private LoanRepository loanRepository;

    @Resource
    private ModelMapper modelMapper;

    @Override
    public void barrowBook(MembersDto membersDto, BookDto bookDto) {

        Members members=modelMapper.map(membersDto,Members.class);
        Books books=modelMapper.map(bookDto,Books.class);

        Loans existingLoan = loanRepository.findByBooksAndReturnDateIsNull(books);

        if(existingLoan==null){
            Loans loans=new Loans();
            loans.setMembers(members);
            loans.setBooks(books);
            loans.setLoadDate(LocalDate.now());
            loanRepository.save(loans);

        }else{
            throw new IllegalArgumentException("bu kitap şu anda başka bi üye tarafından ödünç alınmış durumda");
        }
    }
    @Override
        public List<LoanDto> getAllLoans() {
        List<Loans> loansList=loanRepository.findAll();

        List<LoanDto> bookDtos=loansList.stream().
                map(loans -> modelMapper.map(loans,LoanDto.class))
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
                Long daysBetween = ChronoUnit.DAYS.between(loan.getLoadDate(), LocalDate.now()); // loan.getBorrowDate() kullanımına dikkat
                if (daysBetween > 15) {
                    Long debt = (daysBetween - 15) * 2;
                    System.out.println("Kitap geciktirme borcunuz " + debt + " TL");
                } else {
                    System.out.println("Kitabı zamanında teslim ettiğiniz için teşekkür ederiz");
                }
                loanRepository.save(loan); // Bu satırı doğru yerde yazdık
            } else {
                throw new IllegalArgumentException("İade zaten yapılmış");
            }
        } else {
            throw new IllegalArgumentException("Ödünç alınan kitap bulunamadı");
        }
    }
}

