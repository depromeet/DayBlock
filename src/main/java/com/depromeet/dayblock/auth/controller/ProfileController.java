package com.depromeet.dayblock.auth.controller;

import com.depromeet.dayblock.auth.EmailInvalidException;
import com.depromeet.dayblock.auth.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
@RequestMapping("/api")
@Api(value = "profile")
@CrossOrigin(origins = {"*"})
@Slf4j
public class ProfileController {

    private static final HttpHeaders httpHeaders = new HttpHeaders();
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    @ApiOperation(value = "User Profile API", notes = "자기 자신 정보 받아옴 (헤더 토큰에 기반해서 정보 추출)")
    public ResponseEntity getProfile(
            HttpServletRequest httpServletRequest) {
        try {
            String accessEmail = httpServletRequest.getSession().getAttribute("accessEmail").toString();
            return new ResponseEntity<>(profileService.getUserProfile(accessEmail), httpHeaders, HttpStatus.OK);
        } catch (EmailInvalidException e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "EMAIL NOT FOUND"), httpHeaders, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Collections.singletonMap("error", "INTERNAL SERVER ERROR"), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
