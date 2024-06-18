package kr.co.orangenode.repository.custom.user;

import kr.co.orangenode.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepositoryCustom {

    // userId 찾기
    Optional<User> findUserIdByUserNameAndUserEmail(String name, String email);

    // userPw 수정
    public long updateUserPwByUserIdAndUserEmail(String uid,String pass, String email);

    // admin user 검색
    Page<User> findUsers(String search, Pageable pageable);

}
