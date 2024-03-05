package com.example.project2.service;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.MembersDto;
import com.example.project2.entity.Members;

import java.util.List;

public interface MemberService {

    void addMembers(MembersDto membersDto);

    List<BookDto> getAllBooksByMembers(String memberName);

    void deleteMembers(Long membersId);

}
