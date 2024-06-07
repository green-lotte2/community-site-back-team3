package kr.co.orangenode.repository.custom;

import kr.co.orangenode.entity.page.Block;

import java.util.List;

public interface BlockRepositoryCustom {

    // 페이지 blocks 조회
    public List<Block> findAllByPageNoOrderByBlockOrder(int pageNo);

}
