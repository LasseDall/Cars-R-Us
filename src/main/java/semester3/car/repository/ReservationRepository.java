package semester3.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semester3.car.entity.Member;
import semester3.car.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

  List<Reservation> findAllByMember(Member member);
}
