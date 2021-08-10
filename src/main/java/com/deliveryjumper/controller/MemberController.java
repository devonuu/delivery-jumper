package com.deliveryjumper.controller;

import com.deliveryjumper.domain.Address;
import com.deliveryjumper.domain.Member;
import com.deliveryjumper.domain.Role;
import com.deliveryjumper.service.AuthService;
import com.deliveryjumper.service.MemberService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-06
 * Time : 오후 1:51
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberInfo> join(@RequestBody @Valid CreateRequest request){
        Member member = new Member.Builder()
            .email(request.email)
            .role(Role.ROLE_CUSTOMER)
            .name(request.name)
            .password(request.password)
            .picture(request.picture)
            .address(request.address)
            .build();
        memberService.join(member);
        MemberInfo result = new MemberInfo(member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberInfo> findMemberById(@PathVariable(name = "id") Long id){
        Member findMember = memberService.findById(id);
        MemberInfo result = new MemberInfo(findMember);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Data
    static class CreateRequest {
        @NotEmpty
        private String email;

        @NotEmpty
        private String password;
        private Address address;
        private String name;
        private String picture;
    }

    @Data
    static class MemberInfo {
        private String email;
        private Role role;
        private String name;
        private Address address;

        public MemberInfo(Member member){
            this.email = member.getEmail();
            this.role = member.getRole();
            this.name = member.getName();
            this.address = member.getAddress();
        }
    }
}
