package kr.co.orangenode.repository.page;

import kr.co.orangenode.entity.page.Partner;
import kr.co.orangenode.repository.custom.page.PartnerRepositoryCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer>, PartnerRepositoryCustom {

    // Page 협력자 전체 삭제
    public void deletePartnersByPageNo(int pageNo);

    // 협력자 삭제 - JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM Partner p WHERE p.pageNo = :pageNo AND p.uid = :uid")
    void deleteByPageNoAndUid(@Param("pageNo") int pageNo, @Param("uid") String uid);

}
