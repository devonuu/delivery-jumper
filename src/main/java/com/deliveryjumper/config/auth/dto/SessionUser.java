package com.deliveryjumper.config.auth.dto;

import com.deliveryjumper.domain.Member;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(Member member) {
      this.name = member.getName();
      this.email = member.getEmail();
      this.picture = member.getPicture();
    }
}
