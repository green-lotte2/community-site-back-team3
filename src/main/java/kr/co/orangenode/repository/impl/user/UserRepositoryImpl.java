package kr.co.orangenode.repository.impl.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.user.QUser;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.repository.custom.user.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private QUser qUser = QUser.user;

    // userID 찾기
    @Override
    public Optional<User> findUserIdByUserNameAndUserEmail(String name, String email) {
        User user = jpaQueryFactory
                .selectFrom(qUser)
                .where(qUser.name.eq(name)
                        .and(qUser.email.eq(email)))
                .fetchOne();
        return Optional.ofNullable(user);
    }

    // UserPw update
    @Override
    @Transactional
    public long updateUserPwByUserIdAndUserEmail(String uid, String pass, String email) {
        try {
            long result = jpaQueryFactory
                    .update(qUser)
                    .set(qUser.pass, pass)
                    .where(qUser.uid.eq(uid)
                            .and(qUser.email.eq(email)))
                    .execute();
            return result;
        } catch (Exception e) {
            log.error("Error msg :" + e.getMessage());
            return -1;
        }
    }
}
