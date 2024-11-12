package com.ssg.finalex3.member.repository;

import com.ssg.finalex3.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}