package kr.co.orangenode.service;

import kr.co.orangenode.entity.cs.CsEntity;
import kr.co.orangenode.repository.cs.CsCateRepository;
import kr.co.orangenode.repository.cs.csRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CsService {

    private final CsCateRepository csCateRepository;
    private final csRepository csRepository;


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
}
