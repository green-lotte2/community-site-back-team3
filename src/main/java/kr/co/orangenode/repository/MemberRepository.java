package kr.co.orangenode.repository;

import kr.co.orangenode.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email); // 중복 가입 확인

    User findAllByEmail(String email);


}
