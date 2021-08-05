package com.deliveryjumper.repository;

import com.deliveryjumper.domain.Member;
import java.util.Optional;
import java.util.stream.DoubleStream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String loginId);

}
