package kr.co.orangenode.service.cs;

import kr.co.orangenode.dto.cs.QuestionDTO;
import kr.co.orangenode.entity.cs.Question;
import kr.co.orangenode.mapper.QuestionMapper;
import kr.co.orangenode.repository.cs.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final ModelMapper modelMapper;

    /** 관리자 들어온 문의내역 가져오기*/
    public ResponseEntity<?> selectQuestion(){
        List<Question> question = questionRepository.selectQuestion();

        if(question.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No question found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(question);
        }
    }
    
    /** 관리자 문의 답변하기*/
    @Transactional
    public ResponseEntity<?> answerQuestion(QuestionDTO questionDTO){
        log.info("관리자 답변하기 서비스" + questionDTO);
        Question question = modelMapper.map(questionDTO, Question.class);


        if(questionDTO.getContent().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("answer fail");
        }else{
            questionRepository.save(question);
            questionMapper.updateQuestionStatus(questionDTO.getParent());
            return ResponseEntity.status(HttpStatus.OK).body(question);
        }
    }
}
