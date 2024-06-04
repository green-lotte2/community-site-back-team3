package kr.co.orangenode.repository.page;

import kr.co.orangenode.entity.page.Block;
import kr.co.orangenode.repository.custom.BlockRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer>, BlockRepositoryCustom {
}
