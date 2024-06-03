package kr.co.orangenode.repository.impl.cs;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.cs.CsEntity;
import kr.co.orangenode.entity.cs.QCsEntity;
import kr.co.orangenode.repository.custom.cs.csRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.Console;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class csRepositoryImpl implements csRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QCsEntity qCsEntity = QCsEntity.csEntity;

    @Override
        public List<CsEntity> selectCs(String CateName) {
            
            return jpaQueryFactory.selectFrom(qCsEntity)
                    .where(qCsEntity.cateName.eq(CateName))
                    .fetch();

    }
}
