package com.example.burgerqueen_proj.member.repository;

import com.example.burgerqueen_proj.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
