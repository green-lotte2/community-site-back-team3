package kr.co.orangenode.repository.custom.user;

import kr.co.orangenode.entity.user.User;

import java.util.Optional;

public interface UserRepositoryCustom {

    // userId 찾기
    Optional<User> findUserIdByUserNameAndUserEmail(String name, String email);

    // userPw 수정
    public long updateUserPwByUserIdAndUserEmail(String uid,String pass, String email);
}
