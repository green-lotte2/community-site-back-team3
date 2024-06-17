package kr.co.orangenode.repository.impl.page;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.page.QPartner;
import kr.co.orangenode.entity.user.QUser;
import kr.co.orangenode.repository.custom.page.PartnerRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PartnerRepositoryImpl implements PartnerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QPartner qPartner = QPartner.partner;
    private final QUser qUser = QUser.user;

    // Page의 협력자 (+이름) 전체 조회
    @Override
    public List<Tuple> selectPartnersByPageNo(int pageNo){

        return jpaQueryFactory
                .select(qPartner, qUser.name)
                .from(qPartner)
                .join(qUser).on(qPartner.uid.eq(qUser.uid))
                .where(qPartner.pageNo.eq(pageNo))
                .fetch();
    }
}
