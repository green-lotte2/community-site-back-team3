package kr.co.orangenode.repository.page;

import kr.co.orangenode.entity.page.Page;
import kr.co.orangenode.repository.custom.page.PageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer>, PageRepositoryCustom {

    // 페이지 목록 조회
    public List<Page> findAllByUid(String uid);
    // 페이지 조회
    public Page findByPageNo(int pageNo);
}
