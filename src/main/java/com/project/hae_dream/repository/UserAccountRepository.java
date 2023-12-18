package com.project.hae_dream.repository;

import com.project.hae_dream.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity,Long> {
    // 아이디로 회원 정보 조회 (select * from user_account_table where user_id=? 라는 쿼리를 대신함)
    Optional<UserAccountEntity> findByUserId(String userId); // optional 은 null 방지를 해줌.

    Optional<UserAccountEntity> findByUserName(String userId);
    // 엔티티 객체로 주고받고, 엔티티 객체로 리턴을 받음 그리고 넘겨주는 값은 회원의 id 값.
}
