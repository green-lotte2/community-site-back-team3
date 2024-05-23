package kr.co.orangenode.security;

import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityUserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User member = memberRepository.findById(username)
                .orElseThrow(()->new UsernameNotFoundException(username + " NotFound"));

        // 사용자 인증객체 생성(세션에 저장)
        UserDetails userDetails = MyUserDetails.builder()
                .member(member)
                .build();


        // Security ContextHolder 저장
        return userDetails;
    }
}
