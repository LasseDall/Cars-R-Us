package semester3.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semester3.car.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
