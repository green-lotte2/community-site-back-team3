package kr.co.orangenode.repository.impl.page;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.page.Page;
import kr.co.orangenode.entity.page.QPage;
import kr.co.orangenode.entity.page.QPartner;
import kr.co.orangenode.repository.custom.page.PageRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PageRepositoryImpl implements PageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QPage qPage = QPage.page;
    private final QPartner qPartner = QPartner.partner;

    // 파트너로 속해 있는 Page 조회
    // SELECT * FROM page WHERE pageNo IN (SELECT pageNo FROM partner WHERE uid = ?);
    @Override
    public List<Page> findPagesByPartnerUid(String uid) {
        return jpaQueryFactory.selectFrom(qPage)
                .where(qPage.pageNo.in(
                        jpaQueryFactory.select(qPartner.pageNo)
                                .from(qPartner)
                                .where(qPartner.uid.eq(uid))
                ))
                .fetch();
    }
}
