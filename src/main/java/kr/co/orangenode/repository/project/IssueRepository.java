package kr.co.orangenode.repository.project;
import kr.co.orangenode.entity.project.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Integer> {
}
