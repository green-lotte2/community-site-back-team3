package kr.co.orangenode.repository;

import kr.co.orangenode.entity.article.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
}
