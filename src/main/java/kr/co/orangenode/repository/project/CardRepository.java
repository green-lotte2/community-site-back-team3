package kr.co.orangenode.repository.project;
import kr.co.orangenode.entity.project.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    // 한 프로젝트에 속한 이슈 조회
    List<Card> findAllByBoardNo(int boardNo);

    // 이슈 삭제 //
    void deleteAllByBoardNo(int boardNo);
}
