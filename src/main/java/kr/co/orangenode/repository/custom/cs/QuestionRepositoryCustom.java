package kr.co.orangenode.repository.custom.cs;

import kr.co.orangenode.entity.cs.Question;

import java.util.List;

public interface QuestionRepositoryCustom {
    public List<Question> selectQuestion();
}
