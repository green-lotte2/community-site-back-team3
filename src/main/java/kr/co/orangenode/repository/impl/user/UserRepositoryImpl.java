package kr.co.orangenode.repository.impl.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.user.QUser;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.repository.custom.user.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    // admin user 검색
    public Page<User> findUsers(String search, Pageable pageable) {
        QUser user = QUser.user;

        List<User> users = jpaQueryFactory.selectFrom(user)
                .where(user.uid.containsIgnoreCase(search)
                        .or(user.name.containsIgnoreCase(search))
                        .or(user.email.containsIgnoreCase(search))
                        .or(user.hp.containsIgnoreCase(search))
                        .or(user.company.containsIgnoreCase(search))
                        .or(user.nick.containsIgnoreCase(search))
                        .or(user.grade.containsIgnoreCase(search))
                        .or(user.role.containsIgnoreCase(search))
                        .or(user.department.containsIgnoreCase(search))
                        .or(user.position.containsIgnoreCase(search))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(user)
                .where(user.uid.containsIgnoreCase(search)

                        .or(user.name.containsIgnoreCase(search))
                        .or(user.email.containsIgnoreCase(search))
                        .or(user.hp.containsIgnoreCase(search))
                        .or(user.company.containsIgnoreCase(search))
                        .or(user.nick.containsIgnoreCase(search))
                        .or(user.grade.containsIgnoreCase(search))
                        .or(user.role.containsIgnoreCase(search))
                        .or(user.department.containsIgnoreCase(search))
                        .or(user.position.containsIgnoreCase(search)))
                .fetchCount();

        return new PageImpl<>(users, pageable, total);
    }
}
