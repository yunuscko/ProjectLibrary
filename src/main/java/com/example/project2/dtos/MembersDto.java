package com.example.project2.dtos;

import com.example.project2.entity.Loans;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class MembersDto {
    private Long id;

    private String name;

    private String email;

    @OneToMany
    private Set<Loans> membersOfLoans;

    private Date registrationDate;
}
