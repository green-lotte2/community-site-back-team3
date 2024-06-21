package kr.co.orangenode.repository.cs;

import kr.co.orangenode.entity.cs.CsEntity;
import kr.co.orangenode.repository.custom.cs.CsRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsRepository extends JpaRepository<CsEntity, Integer>, CsRepositoryCustom {
}
