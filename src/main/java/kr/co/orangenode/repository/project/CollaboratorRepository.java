package kr.co.orangenode.repository.project;

import kr.co.orangenode.entity.project.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator,Integer> {

    // 콜라보레이터 삭제 //
    void deleteAllByProNo(int proNo);
}
