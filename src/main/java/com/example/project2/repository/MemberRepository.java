package com.example.project2.repository;

import com.example.project2.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members,Long> {
    Members getMemberById(Long memberId);
}
