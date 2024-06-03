package kr.co.orangenode.repository.cs;

import kr.co.orangenode.entity.cs.CsEntity;
import kr.co.orangenode.repository.custom.cs.csRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface csRepository extends JpaRepository<CsEntity, Integer>, csRepositoryCustom {
}
