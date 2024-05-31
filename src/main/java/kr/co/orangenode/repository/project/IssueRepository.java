package kr.co.orangenode.repository.project;
import kr.co.orangenode.entity.project.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Integer> {

    // 한 프로젝트에 속한 이슈 조회
    List<Issue> findAllByProNo(int proNo);

    // 이슈 삭제 //
    void deleteAllByProNo(int proNo);
}
