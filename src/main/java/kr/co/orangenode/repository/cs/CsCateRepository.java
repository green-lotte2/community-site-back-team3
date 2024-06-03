package kr.co.orangenode.repository.cs;

import kr.co.orangenode.entity.cs.CsCate;
import kr.co.orangenode.repository.custom.cs.CsCateRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsCateRepository extends JpaRepository<CsCate, Integer>, CsCateRepositoryCustom {
}
