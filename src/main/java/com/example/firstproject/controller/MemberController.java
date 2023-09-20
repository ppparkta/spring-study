package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    public MemberRepository memberRepository;
    @GetMapping("/join")
    public String viewJoin(){
        return "/members/new";
    }
    @PostMapping("/join")
    public String createJoin(MemberForm form){
        Member member = form.toEntity();
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";
    }
}
