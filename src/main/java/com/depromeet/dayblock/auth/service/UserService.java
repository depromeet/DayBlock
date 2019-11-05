package com.depromeet.dayblock.auth.service;

import com.depromeet.dayblock.auth.EmailInvalidException;
import com.depromeet.dayblock.auth.dto.LoginDto;
import com.depromeet.dayblock.auth.dto.UserDto;
import com.depromeet.dayblock.auth.entity.User;
import com.depromeet.dayblock.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService {

    private static final Pattern emailPattern = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public User loginUser(LoginDto loginDto) throws EmailInvalidException, BadCredentialsException {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(EmailInvalidException::new);
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        return user;
    }

    public User registerUser(UserDto userDto) throws EmailInvalidException {
        if (isDuplicateEmail(userDto.getEmail())) {
            log.warn("duplicateEmail");
            throw  new EmailInvalidException();
        }
        if (!emailPattern.matcher(userDto.getEmail()).matches()) {
            log.warn("EmailPattern Error");
            throw new EmailInvalidException();
        }
        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .thumbImageUrl("tmp")
                .imageUrl("tmp")
                .build();

        userRepository.save(user);
        return user;
    }

    public boolean isDuplicateEmail(String userEmail) {
        return userRepository.existsByEmail(userEmail);
    }

}
