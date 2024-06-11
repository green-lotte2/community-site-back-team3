package kr.co.orangenode.service;

import kr.co.orangenode.dto.cs.QuestionDTO;
import kr.co.orangenode.entity.cs.CsEntity;
import kr.co.orangenode.entity.cs.Question;
import kr.co.orangenode.repository.cs.CsCateRepository;
import kr.co.orangenode.repository.cs.CsRepository;
import kr.co.orangenode.repository.cs.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CsService {

    private final CsCateRepository csCateRepository;
    private final CsRepository csRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    /** cs카테고리 가져오기*/
    public ResponseEntity<?> selectCsCate(){
        List<String> csCateList = csCateRepository.selectCsCateList();
        log.info("csCate 서비스" + csCateList);

        if(csCateList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }else {
            return ResponseEntity.ok().body(csCateList);
        }
    }

    /** cs내용 가져오기*/
    public ResponseEntity<?> selectCs(String csCateName){
        List<CsEntity> result = csRepository.selectCs(csCateName);
        log.info("cs서비스 " + result);
        if(result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }else{
            return ResponseEntity.ok().body(result);
        }

    }

    // 문의하기 글 작성하기
    public ResponseEntity<?> insertQuestion(QuestionDTO questionDTO){

        if(questionDTO.getContent()!=null){
            log.info("글넣기 서비스" + questionDTO);
            Question question = modelMapper.map(questionDTO, Question.class);
            questionRepository.save(question);

            return ResponseEntity.status(HttpStatus.OK).body(question);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }

    }

    // 글쓰기 파일 업로드
    public ResponseEntity<?> uploadFile(MultipartFile[] imgFiles){

        String path = new File(fileUploadPath).getAbsolutePath();

        List<String> fileoNames = new ArrayList<>();
        List<String> filesNames = new ArrayList<>();
        List<String> fileUrls = new ArrayList<>();

        log.info("글쓰기 파일 업로드...1 " + path);

        for(MultipartFile file : imgFiles) {
            String oName = file.getName();
            log.info("글쓰기 파일 업로드...2" + oName);

            String sName = file.getOriginalFilename();

            String ext = sName.substring(sName.lastIndexOf("."));
            log.info("글쓰기 파일 업로드...3" + ext);

            filesNames.add(sName);

            File uploadFile = new File(path, sName);

            try {
                file.transferTo(uploadFile);

                log.info("글쓰기 파일 업로드...4" + uploadFile);
                String fileUrl = "/uploads/" + sName;

                log.info("글쓰기 파일 업로드...5" + fileUrl);

                fileUrls.add(fileUrl);
            }catch (Exception e){
                log.error("파일 업로드 실패" + sName, e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }

        return ResponseEntity.ok().body(filesNames);
    }
}
