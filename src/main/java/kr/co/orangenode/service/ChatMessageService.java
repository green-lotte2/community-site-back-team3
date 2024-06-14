package kr.co.orangenode.service;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import kr.co.orangenode.dto.chat.ChatMessageDTO;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.repository.chat.ChatMessageRepository;
import kr.co.orangenode.repository.chat.ChatRoomRepository;
import kr.co.orangenode.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Transactional
    public ChatMessageDTO saveMessage(ChatMessageDTO chatMessageDTO) {
        if (chatRoomRepository.existsById(chatMessageDTO.getChatNo())) {
            ChatMessage chatMessage = modelMapper.map(chatMessageDTO, ChatMessage.class);
            chatMessage.setCDate(LocalDateTime.now(ZoneOffset.UTC)); // UTC 시간으로 설정
            ChatMessage savedMessage = chatMessageRepository.save(chatMessage);
            String userProfile = userRepository.findById(chatMessageDTO.getUid())
                    .orElseThrow(() -> new IllegalArgumentException("User not found")).getProfile();

            chatMessageDTO.setCmNo(savedMessage.getCmNo());
            chatMessageDTO.setCDate(savedMessage.getCDate());
            chatMessageDTO.setProfile(userProfile != null ? userProfile : "default"); // 프로필 설정, 기본값으로 "default" 사용

            // 중복 전송 방지
            messagingTemplate.convertAndSend("/topic/chatroom/" + chatMessageDTO.getChatNo(), chatMessageDTO);
            return chatMessageDTO;
        } else {
            throw new IllegalArgumentException("Invalid chat room id: " + chatMessageDTO.getChatNo());
        }
    }





    @Transactional
    public ResponseEntity<String> uploadFile(MultipartFile file, String chatNo, String uid) {
        String oName = file.getOriginalFilename();
        String ext = oName.substring(oName.lastIndexOf("."));
        String sName = UUID.randomUUID().toString() + ext;

        log.info("파일 업로드 시작 - 파일 이름: {}, 채팅방 번호: {}, 사용자 ID: {}", oName, chatNo, uid);

        try {
            Path uploadPath = Paths.get(fileUploadPath).toAbsolutePath().normalize();
            log.info("파일 업로드 경로: {}", uploadPath.toString());
            if (!Files.exists(uploadPath)) {
                log.info("디렉토리가 존재하지 않습니다. 디렉토리를 생성합니다.");
                Files.createDirectories(uploadPath);
            }

            // 파일 저장
            Path filePath = uploadPath.resolve(sName);
            log.info("파일을 저장할 경로: {}", filePath.toString());
            file.transferTo(filePath.toFile());

            // chat room이 존재하는지 확인
            if (!chatRoomRepository.existsById(Integer.parseInt(chatNo))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid chat room id");
            }

            // uid가 user 테이블에 존재하는지 확인
            log.info("Checking if user exists with UID: {}", uid);
            if (!userRepository.existsById(uid)) {
                log.info("User with UID: {} does not exist in the database.", uid);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user id: " + uid);
            }

            // 파일 정보를 데이터베이스에 저장
            ChatMessageDTO chatMessage = new ChatMessageDTO();
            chatMessage.setMessage(oName); // 파일 이름을 메시지로 저장
            chatMessage.setOName(oName);
            chatMessage.setSName(sName); // 저장된 파일 이름 설정
            chatMessage.setCDate(LocalDateTime.now()); // 현재 날짜와 시간을 설정
            chatMessage.setChatNo(Integer.parseInt(chatNo));
            chatMessage.setUid(uid);

            // 유저 이름 설정
            chatMessage.setName(userRepository.findById(uid).orElseThrow(() -> new IllegalArgumentException("User not found")).getName());

            log.info("파일 정보 저장 시작 - {}", chatMessage);

            ChatMessageDTO savedMessageDTO = saveMessage(chatMessage);

            // 저장된 메시지를 채팅방에 전송
            messagingTemplate.convertAndSend("/topic/chatroom/" + chatNo, savedMessageDTO);

            log.info("저장된 메시지 - {}", savedMessageDTO);

            log.info("파일 업로드 성공");

            return ResponseEntity.ok("파일 업로드 성공");
        } catch (IOException e) {
            log.error("파일 업로드 실패", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
        } catch (Exception e) {
            log.error("알 수 없는 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알 수 없는 오류 발생");
        }
    }


    // 파일 다운로드 메서드 수정
    public ResponseEntity<?> fileDownload(String sName) {
        try {
            ChatMessage file = chatMessageRepository.findBysName(sName);
            if (file == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }

            Path path = Paths.get(fileUploadPath).resolve(file.getSName()).normalize();
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }

            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(file.getOName(), StandardCharsets.UTF_8)
                    .build());
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while downloading file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while downloading file");
        }
    }


    @Transactional
    public ResponseEntity<List<ChatMessageDTO>> getMessages(int chatNo) {
        List<Tuple> tuples = chatMessageRepository.saveMessageWithRoom(chatNo);

        if (tuples.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            List<ChatMessageDTO> messages = tuples.stream()
                    .map(tuple -> {
                        String userName = tuple.get(0, String.class);
                        ChatMessage message = tuple.get(1, ChatMessage.class);
                        ChatMessageDTO dto = modelMapper.map(message, ChatMessageDTO.class);
                        dto.setName(userName);

                        String userProfile = userRepository.findById(message.getUid())
                                .orElseThrow(() -> new IllegalArgumentException("User not found")).getProfile();
                        dto.setProfile(userProfile != null ? userProfile : "default"); // 프로필 정보 설정

                        return dto;
                    })
                    .sorted(Comparator.comparing(ChatMessageDTO::getCDate))
                    .collect(Collectors.toList());

            System.out.println("Messages fetched from DB: " + messages);
            return ResponseEntity.ok(messages);
        }
    }

}
