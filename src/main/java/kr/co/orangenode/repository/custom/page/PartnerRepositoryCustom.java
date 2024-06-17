package kr.co.orangenode.repository.custom.page;


import com.querydsl.core.Tuple;

import java.util.List;

public interface PartnerRepositoryCustom {

    // Page의 협력자 (+이름) 전체 조회
    List<Tuple> selectPartnersByPageNo(int pageNo);
}
