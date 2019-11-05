package com.depromeet.dayblock.auth.service;

import com.depromeet.dayblock.auth.EmailInvalidException;
import com.depromeet.dayblock.auth.UnauthorizedException;
import com.depromeet.dayblock.auth.dto.TokenDto;
import com.depromeet.dayblock.auth.entity.User;
import com.depromeet.dayblock.auth.jwt.TokenProvider;
import com.depromeet.dayblock.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenService {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    public TokenService(TokenProvider tokenProvider, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    public TokenDto publishToken(User user) {
        String accessToken = tokenProvider.publishToken(user.getId(), user.getEmail(), true);
        String refreshToken = tokenProvider.publishToken(user.getId(), user.getEmail(), false);
        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public TokenDto updateToken(String refreshToken) throws UnauthorizedException {
        String accessEmail = tokenProvider.getUserEmail(refreshToken, false);
        User user = userRepository.findByEmail(accessEmail)
                .orElseThrow(UnauthorizedException::new);
        return this.publishToken(user);
    }
}
