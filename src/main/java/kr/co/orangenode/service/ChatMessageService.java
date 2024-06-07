package kr.co.orangenode.service;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import kr.co.orangenode.dto.chat.ChatMessageDTO;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.repository.chat.ChatMessageRepository;
import kr.co.orangenode.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ChatMessage saveMessage(ChatMessageDTO chatMessageDTO) {
        if (chatRoomRepository.existsById(chatMessageDTO.getChatNo())) {
            ChatMessage chatMessage = modelMapper.map(chatMessageDTO, ChatMessage.class);
            return chatMessageRepository.saveMessageWithRoom2(chatMessage);
        } else {
            throw new IllegalArgumentException("Invalid chat room id: " + chatMessageDTO.getChatNo());
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


    @Transactional
    public ResponseEntity<String> uploadFile(MultipartFile file, String chatNo, String uid) {
        String fileName = file.getOriginalFilename();
        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일 저장
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            // 파일 정보를 데이터베이스에 저장
            ChatMessageDTO chatMessage = new ChatMessageDTO();
            chatMessage.setMessage(fileName); // 파일 이름을 메시지로 저장
            chatMessage.setCDate(LocalDateTime.now());
            chatMessage.setChatNo(Integer.parseInt(chatNo));
            chatMessage.setUid(uid);
            saveMessage(chatMessage);

            return ResponseEntity.ok("파일 업로드 성공");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
        }
    }
}
