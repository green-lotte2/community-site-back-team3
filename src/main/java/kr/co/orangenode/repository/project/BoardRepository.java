package kr.co.orangenode.repository.project;
import kr.co.orangenode.entity.project.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 한 프로젝트에 속한 이슈 조회
    List<Board> findAllByProNo(int proNo);

}
