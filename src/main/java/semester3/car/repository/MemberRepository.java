package semester3.car.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import semester3.car.dto.MemberRequest;
import semester3.car.dto.MemberResponse;
import semester3.car.entity.Member;

import javax.swing.*;

public interface MemberRepository extends JpaRepository<Member, String> {

  boolean existsByEmail(String email);


}
