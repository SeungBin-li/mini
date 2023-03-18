package com.sparta.mini.member.controller;

import com.sparta.mini.member.dto.LoginRequestDto;
import com.sparta.mini.member.dto.MessageResponseDto;
import com.sparta.mini.member.dto.SignupRequestDto;
import com.sparta.mini.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public ModelAndView signupPage(){
        return new ModelAndView("signup");
    }
    @GetMapping("/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        memberService.signup(signupRequestDto);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "회원가입이 성공적으로 진행되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        memberService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "로그인이 성공적으로 진행되었습니다."));
    }
}
