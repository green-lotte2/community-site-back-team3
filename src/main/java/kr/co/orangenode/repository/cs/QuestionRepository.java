package kr.co.orangenode.repository.cs;

import kr.co.orangenode.entity.cs.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
