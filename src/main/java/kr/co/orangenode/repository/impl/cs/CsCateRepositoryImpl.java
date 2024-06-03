package kr.co.orangenode.repository.impl.cs;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.cs.QCsCate;
import kr.co.orangenode.repository.custom.cs.CsCateRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CsCateRepositoryImpl implements CsCateRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QCsCate qCsCate = QCsCate.csCate;

    @Override
    public List<String> selectCsCateList() {
        return jpaQueryFactory.select(qCsCate.cateName).from(qCsCate).fetch();
    }
}
