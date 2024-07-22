package gamza.project.doaduo.service.impl;

import gamza.project.doaduo.dto.RequestUserLoginDto;
import gamza.project.doaduo.dto.RequestUserSignUpDto;
import gamza.project.doaduo.entity.UserEntity;
import gamza.project.doaduo.entity.UserRole;
import gamza.project.doaduo.error.ErrorCode;
import gamza.project.doaduo.error.requestError.NotFoundException;
import gamza.project.doaduo.error.requestError.UnAuthorizedException;
import gamza.project.doaduo.jwt.JwtTokenProvider;
import gamza.project.doaduo.repository.UserRepository;
import gamza.project.doaduo.service.inter.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public void signUp(RequestUserSignUpDto dto, HttpServletResponse response) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new UnAuthorizedException("S404", ErrorCode.NOT_ALLOW_ACCESS_EXCEPTION);
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity user = dto.toEntity();
        userRepository.save(user);
    }

    @Override
    public Map<String, String> login(RequestUserLoginDto dto, HttpServletResponse response) {

        if (!userRepository.existsByEmail(dto.getEmail())) {
            throw new UnAuthorizedException("L401-1", ErrorCode.UNAUTHORIZED_EXCEPTION);
        }

        UserEntity user = userRepository.findByEmail(dto.getEmail()).orElseThrow();

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UnAuthorizedException("L401-2", ErrorCode.UNAUTHORIZED_EXCEPTION);
        }

        String at = setBodyAtToken(dto.getEmail(), response);
        String rt = setBodyRtToken(dto.getEmail(), response);

        // Create a map with tokens
        Map<String, String> tokens = new HashMap<>();
        tokens.put("at", "Bearer " + at);
        tokens.put("rt", "Bearer " + rt);

        return tokens;
    }

    @Override
    public void reissueToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        jwtTokenProvider.validateRefreshToken(refreshToken);

        String newAT = jwtTokenProvider.reissueAT(refreshToken, response);
        jwtTokenProvider.setHeaderAccessToken(response, newAT);
    }

//    @Override
//    public void setTokenInHeader(String  email, HttpServletResponse response) {
//        UserEntity user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new NotFoundException("5003", ErrorCode.NOT_ALLOW_ACCESS_EXCEPTION));
//
//        UserRole role = user.getUserRole();
//
//        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), role);
//        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), role);
//
//        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
//        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);
//    }

    @Override
    public String setBodyAtToken(String  email, HttpServletResponse response) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("5003", ErrorCode.NOT_ALLOW_ACCESS_EXCEPTION));

        UserRole role = user.getUserRole();

        return jwtTokenProvider.createAccessToken(user.getId(), role);
    }

    @Override
    public String setBodyRtToken(String  email, HttpServletResponse response) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("5003", ErrorCode.NOT_ALLOW_ACCESS_EXCEPTION));

        UserRole role = user.getUserRole();

        return jwtTokenProvider.createRefreshToken(user.getId(), role);
    }



}