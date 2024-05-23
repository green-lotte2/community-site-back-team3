package kr.co.orangenode.repository;

import kr.co.orangenode.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {

    Optional<Member> findByEmail(String email); // 중복 가입 확인

    Member findAllByEmail(String email);


}
