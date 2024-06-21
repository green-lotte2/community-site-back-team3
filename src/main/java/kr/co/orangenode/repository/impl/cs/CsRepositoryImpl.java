package kr.co.orangenode.repository.impl.cs;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.cs.CsEntity;
import kr.co.orangenode.entity.cs.QCsEntity;
import kr.co.orangenode.repository.custom.cs.CsRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CsRepositoryImpl implements CsRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QCsEntity qCsEntity = QCsEntity.csEntity;

    @Override
        public List<CsEntity> selectCs(String CateName) {
            
            return jpaQueryFactory.selectFrom(qCsEntity)
                    .where(qCsEntity.cateName.eq(CateName))
                    .fetch();

    }
}
