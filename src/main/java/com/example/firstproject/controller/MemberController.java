package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    public MemberRepository memberRepository;
    @GetMapping("/members/new")
    public String viewJoin(){
        return "/members/new";
    }
    @PostMapping("/members/join")
    public String createJoin(MemberForm form){
        Member member = form.toEntity();
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/"+saved.getId();
    }
    @GetMapping("/members/{id}")
    public String showMember(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "/members/show";
    }
    @GetMapping("/members")
    public String showAllMembers(Model model){
        List<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);
        return "/members/index";
    }
}
