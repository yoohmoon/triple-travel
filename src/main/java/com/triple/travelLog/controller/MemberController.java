package com.triple.travelLog.controller;

import com.triple.travelLog.config.JwtProvider;
import com.triple.travelLog.dto.*;
import com.triple.travelLog.entity.Member;
import com.triple.travelLog.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    private final JwtProvider jwtProvider;

    public MemberController(MemberService memberService, JwtProvider jwtProvider) {
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signup")
    public ApiResponse<MemberResponse> signup(@Valid @RequestBody SignupRequest request){
        Member member = memberService.signup(request.getEmail(), request.getPassword(), request.getNickname());
        return ApiResponse.success(new MemberResponse(member.getId(), member.getEmail(), member.getNickname()));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        Member member = memberService.login(request.getEmail(), request.getPassword());
        String token = jwtProvider.createToken(member.getId(), member.getEmail());
        return ApiResponse.success(new LoginResponse(token));
    }
}
