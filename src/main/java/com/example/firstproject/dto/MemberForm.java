package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;

public class MemberForm {
    private String email;
    private String pwd;

    public MemberForm(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public Member toEntity() {
        return new Member(null, email, pwd);
    }
}
