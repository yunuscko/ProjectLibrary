package com.example.project2.service.impl;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.MembersDto;
import com.example.project2.entity.Books;
import com.example.project2.entity.Loans;
import com.example.project2.entity.Members;
import com.example.project2.repository.BookRepository;
import com.example.project2.repository.LoanRepository;
import com.example.project2.repository.MemberRepository;
import com.example.project2.service.MemberService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private LoanRepository loanRepository;

    @Override
    public void addMembers(MembersDto membersDto) {

        Members members=modelMapper.map(membersDto,Members.class);
        memberRepository.save(members);
    }

    @Override
    public List<BookDto> getAllBooksByMembers(String memberName) {
        List<Loans> loans = loanRepository.findByMembers_NameAndReturnDateIsNull(memberName);

        if (loans != null && !loans.isEmpty()) {
            return loans.stream()
                    .map(loan -> modelMapper.map(loan.getBooks(), BookDto.class))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public void deleteMembers(Long membersId) {
        memberRepository.deleteById(membersId);

    }
}
