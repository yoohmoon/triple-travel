package com.triple.travelLog.service;

import com.triple.travelLog.entity.Member;
import com.triple.travelLog.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member signup(String email, String password, String nickname){
        Optional<Member> existing = memberRepository.findByEmail(email);

        if(existing.isPresent()){
            throw new RuntimeException("이미 가입된 이메일입니다");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member(email, encodedPassword, nickname);
        return memberRepository.save(member);
    }

    public Member login(String email, String password){
        Optional<Member> optional = memberRepository.findByEmail(email);

        if(optional.isPresent()){
            Member member = optional.get();
            if(passwordEncoder.matches(password, member.getPassword())){
                return member;
            }
        }

        throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다");
    }
}
