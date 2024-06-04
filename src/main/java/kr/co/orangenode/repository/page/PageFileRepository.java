package kr.co.orangenode.repository.page;

import kr.co.orangenode.entity.page.PageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageFileRepository extends JpaRepository<PageFile, Integer> {
}
