package kr.co.orangenode.repository.page;

import kr.co.orangenode.entity.page.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    public List<Page> findAllByUid(String uid);
}
