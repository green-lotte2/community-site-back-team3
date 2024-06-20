package kr.co.orangenode.service.cs;

import kr.co.orangenode.dto.cs.CsDTO;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /** cs내용 가져오기 Admin*/
    public ResponseEntity<?> csSelects(){
        List<CsEntity> csEntities = csRepository.findAll();
        return ResponseEntity.ok().body(csEntities);
    }

    /** cs 내용 한개 가져오기 Admin(수정용)*/
    public ResponseEntity<?> csSelect(int csNo) {
        Optional<CsEntity> result = csRepository.findById(csNo);

        CsEntity cs = modelMapper.map(result, CsEntity.class);
        log.info("글 하나 가져오기 서비스 " + cs);

        if(result.isPresent()){
            log.info("됐음");
            return ResponseEntity.ok().body(cs);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
    }
    /** cs내용 삭제하기 Admin*/
    public ResponseEntity<?> deleteCs(int[] csNo){

        if(csNo.length == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");

        }else{
            for(int num : csNo){
                csRepository.deleteById(num);
            }
        }
        return ResponseEntity.ok().body("DELETED");
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

    /** 관리자 cs 글 수정*/
    public ResponseEntity<?> csModify(CsDTO csDTO){
        
        log.info("cs 수정..1" + csDTO.getCsNo());
        Optional<CsEntity> cs = csRepository.findById(csDTO.getCsNo());

        if(cs.isPresent()){
            CsEntity updateCs = modelMapper.map(csDTO, CsEntity.class);
            log.info("cs 수정..2" + updateCs);

            CsEntity result = csRepository.save(updateCs);
            log.info("cs 수정..3" + result);
            return ResponseEntity.status(HttpStatus.OK).body(result);

        }else{
            log.info("cs 수정..4");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
    }


    /*관리자 Cs 글 쓰기*/
    public ResponseEntity<?> csWrite(CsDTO csDTO){

        if(csDTO.getContent().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }else{
            CsEntity csEntity = modelMapper.map(csDTO, CsEntity.class);
            csRepository.save(csEntity);
            return ResponseEntity.status(HttpStatus.OK).body(csEntity);
        }

    }
}
