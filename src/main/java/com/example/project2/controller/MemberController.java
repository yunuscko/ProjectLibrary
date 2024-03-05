package com.example.project2.controller;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.MembersDto;
import com.example.project2.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Resource
    private MemberService memberService;

    @PostMapping("/addMember")
    public ResponseEntity addMember(@RequestBody MembersDto membersDto){
        try {
            memberService.addMembers(membersDto);
            return new ResponseEntity("members successfully added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllBooksByMember")
    public ResponseEntity<List<BookDto>> getAllBookByMember(@RequestParam String memberName){
        try {
            List<BookDto> bookDtoList=memberService.getAllBooksByMembers(memberName);
            return new ResponseEntity<>(bookDtoList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteByMemberId/{memberId}")
    public ResponseEntity deleteMembers(@PathVariable Long memberId){
        try {
            memberService.deleteMembers(memberId);
            return new ResponseEntity("deleted member",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
