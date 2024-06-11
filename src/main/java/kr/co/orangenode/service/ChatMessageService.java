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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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

    @Transactional
    public ChatMessageDTO saveMessage(ChatMessageDTO chatMessageDTO) {
        if (chatRoomRepository.existsById(chatMessageDTO.getChatNo())) {
            ChatMessage chatMessage = modelMapper.map(chatMessageDTO, ChatMessage.class);
            ChatMessage savedMessage = chatMessageRepository.saveMessageWithRoom2(chatMessage);
            chatMessageDTO.setCmNo(savedMessage.getCmNo());
            chatMessageDTO.setCDate(savedMessage.getCDate());
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
        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        log.info("파일 업로드 시작 - 파일 이름: {}, 채팅방 번호: {}, 사용자 ID: {}", oName, chatNo, uid);

        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일 저장
            Path filePath = uploadPath.resolve(sName);
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
            chatMessage.setCDate(LocalDateTime.now());
            chatMessage.setChatNo(Integer.parseInt(chatNo));
            chatMessage.setUid(uid);
            chatMessage.setSName(sName); // 저장된 파일 이름 설정

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

    public ResponseEntity<?> getMessages(int chatNo) {
        List<Tuple> tuples = chatMessageRepository.saveMessageWithRoom(chatNo);

        if (tuples.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        } else {
            List<ChatMessageDTO> result = tuples.stream()
                    .map(tuple -> {
                        String userName = tuple.get(0, String.class);
                        ChatMessage message = tuple.get(1, ChatMessage.class);
                        ChatMessageDTO dto = modelMapper.map(message, ChatMessageDTO.class);
                        dto.setName(userName);
                        return dto;
                    })
                    .sorted(Comparator.comparing(ChatMessageDTO::getCDate))
                    .collect(Collectors.toList());

            log.info("챗메세지 서비스" + result);

            return ResponseEntity.ok().body(result);
        }
    }
}
