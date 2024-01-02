package com.study.LoginLogicPractice.Repository;

import com.study.LoginLogicPractice.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원 정보 조회
    // select * from member_table where member_email=?
    Optional<MemberEntity> findByMemberEmail(String memberEmail);

}
