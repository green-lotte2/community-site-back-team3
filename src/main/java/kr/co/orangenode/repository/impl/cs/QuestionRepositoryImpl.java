package kr.co.orangenode.repository.impl.cs;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.cs.QCsEntity;
import kr.co.orangenode.entity.cs.QQuestion;
import kr.co.orangenode.entity.cs.Question;
import kr.co.orangenode.repository.custom.cs.QuestionRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QQuestion question = QQuestion.question;

    @Override
    public List<Question> selectQuestion() {

        return jpaQueryFactory.select(question)
                                .from(question)
                                .where(question.parent.isNull())
                                .fetch();
    }

    @Override
    public List<Question> selectMyQuestion(String uid) {
        return jpaQueryFactory.select(question)
                .from(question)
                .where(question.parent.isNull().and(question.uid.eq(uid)))
                .fetch();
    }

    @Override
    public List<Question> selectMyAnswer(String uid, int parent) {
        return jpaQueryFactory.select(question)
                .from(question)
                .where(question.parent.eq(parent).and(question.uid.eq(uid)))
                .fetch();
    }
}
