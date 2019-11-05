package com.depromeet.dayblock.auth.service;

import com.depromeet.dayblock.auth.EmailInvalidException;
import com.depromeet.dayblock.auth.dto.ProfileDto;
import com.depromeet.dayblock.auth.entity.User;
import com.depromeet.dayblock.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileDto getUserProfile(String userEmail) throws EmailInvalidException {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(EmailInvalidException::new);

        return ProfileDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .imageUrl(user.getImageUrl())
                .thumbImageUrl(user.getThumbImageUrl())
                .build();
    }
}
