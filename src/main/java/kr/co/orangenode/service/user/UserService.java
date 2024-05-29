package kr.co.orangenode.service.user;

import kr.co.orangenode.dto.user.UserDTO;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

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
}
