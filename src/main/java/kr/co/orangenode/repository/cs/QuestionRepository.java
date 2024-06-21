package kr.co.orangenode.repository.cs;

import kr.co.orangenode.entity.cs.Question;
import kr.co.orangenode.repository.custom.cs.QuestionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer>, QuestionRepositoryCustom {
}
