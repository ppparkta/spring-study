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
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        Member member = form.toEntity();
        Member equal = memberRepository.findById(member.getId()).orElse(null);
        if (equal != null){
            memberRepository.save(member);
            log.info(equal.toString());
            return "redirect:/members/"+equal.getId();
        }
        return "redirect:/members";
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rdirmsg){
        Member member = memberRepository.findById(id).orElse(null);
        if (member != null){
            memberRepository.delete(member);
            rdirmsg.addFlashAttribute("msg", "삭제됐습니다.");
        }
        return "redirect:/members";
    }
}
