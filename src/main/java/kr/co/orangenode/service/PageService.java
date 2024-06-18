package kr.co.orangenode.service;

import kr.co.orangenode.dto.page.BlockDTO;
import kr.co.orangenode.dto.page.PageDTO;
import kr.co.orangenode.dto.page.PartnerDTO;
import kr.co.orangenode.entity.page.Page;
import kr.co.orangenode.entity.page.Partner;
import kr.co.orangenode.mapper.PageMapper;
import kr.co.orangenode.repository.page.PageRepository;
import kr.co.orangenode.repository.page.PartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageService {

    private final PartnerRepository partnerRepository;
    private final PageRepository pageRepository;
    private final PageMapper pageMapper;

    private final ModelMapper modelMapper;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    // 페이지 목록 조회
    public ResponseEntity<?> selectPages(String uid) {

        // 직접 소유한 페이지 목록 조회
        List<PageDTO> myPages = pageRepository.findAllByUid(uid)
                .stream()
                .map(entity -> modelMapper.map(entity, PageDTO.class))
                .toList();

        // 협력자로 포함된 페이지 목록 조회
        List<PageDTO> partnerPages = pageRepository.findPagesByPartnerUid(uid)
                .stream()
                .map(entity -> modelMapper.map(entity, PageDTO.class))
                .toList();

        // 중복 제거를 위해 LinkedHashSet 사용 (입력 순서 유지)
        Set<PageDTO> allPagesSet = new LinkedHashSet<>(myPages);
        allPagesSet.addAll(partnerPages);

        // Set을 List로 변환
        List<PageDTO> allPages = new ArrayList<>(allPagesSet);
        return ResponseEntity.ok().body(allPages);
    }

    // 페이지 조회
    public ResponseEntity<?> selectPage(int pageNo) {
        return ResponseEntity.ok().body(pageRepository.findByPageNo(pageNo));
    }

    // 파일 업로드
    public ResponseEntity<String> upload(MultipartFile pagefile) {
        log.info("파일 업로드 ... 1");
        // 이미지 파일 등록 : 해당 디렉토리 없을 경우 자동 생성
        String path = new File(fileUploadPath).getAbsolutePath();

        // oName, sName 구하기
        String oName = pagefile.getOriginalFilename();
        String ext = oName.substring(oName.lastIndexOf("."));
        String sName = UUID.randomUUID().toString() + ext;

        // upload
        File destinationFile = new File(path, sName);

        try {
            pagefile.transferTo(destinationFile);
            log.info("파일 업로드 ... 5");
            log.info("file : {}", destinationFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("파일 업로드 실패", e);
            throw new RuntimeException("파일 업로드 실패", e);
        }
        log.info("sName : " + sName);
        return ResponseEntity.ok().body(sName);
    }

    // 페이지 생성
    public ResponseEntity<?> insertPage(PageDTO pageDTO) {
        Page page = pageRepository.save(modelMapper.map(pageDTO, Page.class));
        // page 리턴
        return ResponseEntity.ok().body(page);
    }

    // 페이지 저장
    public void updateTitle(PageDTO pageDTO) {
        log.info("update PAge Serv");

        // pageRepository.save(modelMapper.map(pageDTO, Page.class));
        pageMapper.updateTitle(pageDTO);

    }

    // 페이지 삭제
    @Transactional
    public ResponseEntity<Integer> delete(int pageNo) {
        log.info("페이지 삭제 서비스 : " + pageNo);
        partnerRepository.deletePartnersByPageNo(pageNo);
        pageRepository.deleteById(pageNo);
        return ResponseEntity.ok().body(1);
    }

    // 파트너 등록
    public ResponseEntity<String> insert(int pageNo, List<String> userUids) {
        try {
            for (String uid : userUids) {
                PartnerDTO partnerDTO = new PartnerDTO();
                partnerDTO.setUid(uid);
                partnerDTO.setPageNo(pageNo);
                partnerRepository.save(modelMapper.map(partnerDTO, Partner.class));
            }
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding partners: " + e.getMessage());
        }
    }
    // 파트너 조회
    public ResponseEntity<?> selectPartner(int pageNo) {
        List<PartnerDTO> partnerDTOS = partnerRepository.selectPartnersByPageNo(pageNo)
                .stream()
                .map(tuple -> {
                    PartnerDTO partnerDTO = modelMapper.map(tuple.get(0, Partner.class), PartnerDTO.class);
                    partnerDTO.setName(tuple.get(1, String.class));
                    return partnerDTO;
                })
                .toList();

        return ResponseEntity.ok().body(partnerDTOS);
    }
    // 파트너 삭제
    public ResponseEntity<?> delete(PartnerDTO partnerDTO) {
        log.info("partnerDTO.getPageNo() : " +partnerDTO.getPageNo());
        log.info("partnerDTO.getUid() : " +partnerDTO.getUid());
        try {
            partnerRepository.deleteByPageNoAndUid(partnerDTO.getPageNo(), partnerDTO.getUid());
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting partner: " + e.getMessage());
        }
    }
}

