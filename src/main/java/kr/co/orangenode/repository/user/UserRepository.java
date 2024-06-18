package kr.co.orangenode.repository.user;

import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.repository.custom.user.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String>, UserRepositoryCustom {

    Optional<User> findByEmail(String email); // 중복 가입 확인
    Optional<User> findByHp(String hp);

    Optional<User> findByUid(String name);
    // 회원 회사별로 출력 //
    List<User> findUserByCompany(String company);

}
