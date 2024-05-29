package kr.co.orangenode.repository.project;

import kr.co.orangenode.entity.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    // 프로젝트 리스트 출력 //
    List<Project> findAllByUid(String uid);
}
