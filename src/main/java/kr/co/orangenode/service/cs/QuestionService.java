package kr.co.orangenode.service.cs;

import kr.co.orangenode.entity.cs.Question;
import kr.co.orangenode.repository.cs.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;


    public ResponseEntity<?> selectQuestion(){
        List<Question> question = questionRepository.findAll();

        if(question.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No question found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(question);
        }

    }
}
