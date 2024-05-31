package kr.co.orangenode.service.user;

import kr.co.orangenode.dto.user.UserDTO;
import kr.co.orangenode.entity.project.Project;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.mapper.UserMapper;
import kr.co.orangenode.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;

    // 회원가입 //
    public String register(UserDTO userDTO) {
        String encoded = passwordEncoder.encode(userDTO.getPass());
        userDTO.setPass(encoded);

        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);

        return savedUser.getUid();
    }
    // 회원 회사별로 조회 //
    public ResponseEntity<?> selectUserByCompany(String company) {
        try {
            List<User> userList = userRepository.findUserByCompany(company);
            List<UserDTO> userDTOS = new ArrayList<>();
            for (User user : userList) {
                userDTOS.add(modelMapper.map(user, UserDTO.class));
            }
            if(!userDTOS.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    /*  계정 설정으로 이동 */
    public ResponseEntity<?> userInfo(String uid) {
        Optional<User> findUid = userRepository.findById(uid);
        if(findUid.isPresent()){
            User user = findUid.get();
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }
    /* 내 설정 수정 */
    public ResponseEntity<?> updateUserInfo(UserDTO userDTO) {

        userMapper.updateUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    /* 이미지 업로드 */
    @Value("${file.upload.path}")
    private String fileUploadPath;
    public String uploadImage(MultipartFile file) {
        String path = new File(fileUploadPath).getAbsolutePath();
        log.info("img:" + file);
        if(!file.isEmpty()){
            String oName = file.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String sName = UUID.randomUUID().toString()+ ext;

            try {
                String imgPath = mkMyImg(path, sName, file);

                return imgPath;
            }catch( Exception e){
                log.error("이미지 업로드 오류:" + e.getMessage());
                return null;
            }
        }
        return null;
    }
    /* 이미지 생성 메서드 */
    private String mkMyImg(String path, String sName, MultipartFile file) throws IOException {
        Thumbnails.of(file.getInputStream())
                .size(100,100)
                .toFile(new File(path, "myImg" + sName));
        return "myImg" + sName;
    }
}
