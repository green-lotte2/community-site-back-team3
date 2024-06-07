package kr.co.orangenode.service;

import kr.co.orangenode.dto.page.BlockDTO;
import kr.co.orangenode.dto.page.PageDTO;
import kr.co.orangenode.entity.page.Block;
import kr.co.orangenode.entity.page.Page;
import kr.co.orangenode.repository.page.PageFileRepository;
import kr.co.orangenode.repository.page.BlockRepository;
import kr.co.orangenode.repository.page.PageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;
    private final BlockRepository blockRepository;
    private final PageFileRepository pageFileRepository;

    private final ModelMapper modelMapper;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    // 페이지 목록 조회
    public ResponseEntity<?> selectPages(String uid){
        List<PageDTO> pageDTOS = pageRepository.findAllByUid(uid)
                .stream()
                .map(entity -> {
                    PageDTO pageDTO = modelMapper.map(entity, PageDTO.class);
                    return pageDTO;
                })
                .toList();
        return ResponseEntity.ok().body(pageDTOS);
    }
    // 페이지 조회
    public ResponseEntity<?> selectPage(int pageNo){
        return ResponseEntity.ok().body(pageRepository.findByPageNo(pageNo));
    }
    // 파일 업로드
    public ResponseEntity<String> upload(MultipartFile pagefile) {
        log.info("파일 업로드 ... 1");
        // 이미지 파일 등록 : 해당 디렉토리 없을 경우 자동 생성
        String path = new File(fileUploadPath).getAbsolutePath();

        // oName, sName 구하기
        String oName = pagefile.getOriginalFilename();
        log.info("파일 업로드 ... 2 " + oName);
        String ext = oName.substring(oName.lastIndexOf("."));
        log.info("파일 업로드 ... 3 " + ext);
        String sName = UUID.randomUUID().toString() + ext;
        log.info("파일 업로드 ... 4 path : " + path);

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

        return ResponseEntity.ok().body(sName);
    }
    // 페이지 생성
    public ResponseEntity<?> insertPage(PageDTO pageDTO){
        Page page = pageRepository.save(modelMapper.map(pageDTO, Page.class));
        // page 리턴
        return ResponseEntity.ok().body(page);
    }
    // 페이지 내용 불러오기
    public ResponseEntity<?> selectBlocks(int pageNo){
        List<BlockDTO> blocks = blockRepository.findAllByPageNoOrderByBlockOrder(pageNo)
                .stream()
                .map(entity -> {
                    return modelMapper.map(entity, BlockDTO.class);
                })
                .toList();

        return ResponseEntity.ok().body(blocks);
    }
    // 블록 저장
    public void insertBlocks(List<Map<String, Object>> blocks, int pageNo){

        log.info("insertBlocks  pageNo: " + pageNo);
        for (Map<String, Object> block : blocks) {
            BlockDTO blockDTO = new BlockDTO();
            blockDTO.setPageNo(pageNo);
            blockDTO.setType(block.get("type").toString());
            blockDTO.setData(String.valueOf(block.get("data")));
            blockDTO.setBlockOrder((Integer) block.get("order"));

            blockRepository.save(modelMapper.map(blockDTO, Block.class));
        }
    }
}

