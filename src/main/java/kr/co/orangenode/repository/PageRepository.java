package kr.co.orangenode.repository;

import kr.co.orangenode.entity.page.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {
}
