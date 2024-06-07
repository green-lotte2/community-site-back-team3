package kr.co.orangenode.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.page.Block;
import kr.co.orangenode.entity.page.QBlock;
import kr.co.orangenode.repository.custom.BlockRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BlockRepositoryImpl implements BlockRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QBlock qBlock = QBlock.block;

    // blocks order 순서로 조회
    @Override
    public List<Block> findAllByPageNoOrderByBlockOrder(int pageNo){
        return jpaQueryFactory
                .selectFrom(qBlock)
                .where(qBlock.pageNo.eq(pageNo))
                .orderBy(qBlock.blockOrder.asc())
                .fetch();
    }
}
