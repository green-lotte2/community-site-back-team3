package kr.co.orangenode.service;

import kr.co.orangenode.entity.cs.CsCate;
import kr.co.orangenode.repository.CsCateRepository;
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
    public ResponseEntity<?> selectCsCate(){
        List<String> csCateList = csCateRepository.selectCsCateList();
        log.info("csCate 서비스" + csCateList);

        if(csCateList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }else {
            return ResponseEntity.ok().body(csCateList);
        }
    }
}
