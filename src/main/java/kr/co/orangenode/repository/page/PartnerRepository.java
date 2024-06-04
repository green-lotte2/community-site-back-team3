package kr.co.orangenode.repository.page;

import kr.co.orangenode.entity.page.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {
}
