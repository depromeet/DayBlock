package com.depromeet.dayblock.auth.controller;

import com.depromeet.dayblock.auth.EmailInvalidException;
import com.depromeet.dayblock.auth.UnauthorizedException;
import com.depromeet.dayblock.auth.dto.LoginDto;
import com.depromeet.dayblock.auth.dto.TokenDto;
import com.depromeet.dayblock.auth.dto.UserDto;
import com.depromeet.dayblock.auth.entity.User;
import com.depromeet.dayblock.auth.service.TokenService;
import com.depromeet.dayblock.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@Api(value = "Auth")
@CrossOrigin(origins = {"*"})
@Slf4j
public class AuthController {

    private static final HttpHeaders httpHeaders = new HttpHeaders();
    private final TokenService tokenService;
    private final UserService userService;

    public AuthController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login API", notes = "로그인을 하면 토큰을 반환하는 API. (Authorization Header 필요 없습니다. Swagger 전역 설정 원인)")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        log.info("login payload :" + loginDto.getEmail());
        try {
            User user = userService.loginUser(loginDto);
            return new ResponseEntity<>(tokenService.publishToken(user), httpHeaders, HttpStatus.OK);
        } catch (EmailInvalidException | BadCredentialsException e) {
            return new ResponseEntity<>(TokenDto.builder().build(), httpHeaders, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register API", notes = "회원가입을 하면 토큰을 반환하는 API. (Authorization Header 필요 없습니다. Swagger 전역 설정 원인)")
    public ResponseEntity<TokenDto> register(@RequestBody UserDto userDto) {
        log.info("register payload :" + userDto.getEmail());
        try {
            User user = userService.registerUser(userDto);
            return new ResponseEntity<>(tokenService.publishToken(user), httpHeaders, HttpStatus.OK);
        } catch (EmailInvalidException e) {
            return new ResponseEntity<>(TokenDto.builder().build(), httpHeaders, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/duplicate/{newEmail:.+}")
    @ApiOperation(value = "Duplicate API", notes = "회원가입시 회원 아이디(email) 중복 체크하는 API. (Authorization Header 필요 없습니다. Swagger 전역 설정 원인)")
    public ResponseEntity duplicate(
            @PathVariable("newEmail") String newEmail) {
        if (userService.isDuplicateEmail(newEmail)) {
            return new ResponseEntity<>(Collections.singletonMap("duplicate", true), httpHeaders, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("duplicate", false), httpHeaders, HttpStatus.OK);
        }

    }

    @GetMapping("refresh/{token:.+}")
    @ApiOperation(value = "Update Access Token API", notes = "refresh_token 을 보내면 access_token 을 반환해주는 API. (Authorization Header 필요 없습니다. Swagger 전역 설정 원인)")
    public ResponseEntity<TokenDto> refreshToken(
            @PathVariable("token") String refreshToken) {
        log.info("update Token");
        try {
            return new ResponseEntity<>(tokenService.updateToken(refreshToken), httpHeaders, HttpStatus.OK);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(TokenDto.builder().build(), httpHeaders, HttpStatus.UNAUTHORIZED);
        }
    }
}
