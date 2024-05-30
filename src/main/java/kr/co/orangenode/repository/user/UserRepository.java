package kr.co.orangenode.repository.user;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email); // 중복 가입 확인
    Optional<User> findByHp(String hp);

    // 회원 회사별로 출력 //
    List<User> findUserByCompany(String company);

    // 관리자 페이지 유저 가입일 순 정렬
    //public List<User> findAllByOrderByRdateDesc();



}
