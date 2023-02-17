package semester3.car.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import semester3.car.dto.ReservationRequest;
import semester3.car.dto.ReservationResponse;
import semester3.car.entity.Car;
import semester3.car.entity.Member;
import semester3.car.entity.Reservation;
import semester3.car.repository.CarRepository;
import semester3.car.repository.MemberRepository;
import semester3.car.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

  @Mock
  ReservationRepository reservationRepository;

  @Mock
  CarRepository carRepository;

  @Mock
  MemberRepository memberRepository;

  ReservationService reservationService;


  @BeforeEach
  void setUp() {
    reservationService = new ReservationService(reservationRepository, carRepository, memberRepository);
  }

  @Test
  void addReservation() {
    LocalDate localDate = LocalDate.now();
    LocalDate localDate1 = LocalDate.of(2023, 12, 12);
    Member member = new Member("Mathilde", "kk", "kk", "kk", "jj", "ii", "uu", "yy");
    Car car = new Car("Ford", "Mustang", 500.0, 100);
    car.setId(1);
    Reservation reservation1 = new Reservation(localDate, member, car);
    Reservation reservation2 = new Reservation(localDate1, member, car);
    ReservationRequest reservationRequest = new ReservationRequest(1, "Mathilde", localDate1);
    car.addReservation(reservation1);
    car.addReservation(reservation2);
    Mockito.when(carRepository.findById(reservationRequest.getCarId())).thenReturn(Optional.of(car));
    Mockito.when(memberRepository.findById(reservationRequest.getMemberUsername())).thenReturn(Optional.of(member));
    assertThrows(ResponseStatusException.class, ()->{ reservationService.addReservation(reservationRequest);});
  }

  @Test
  void findReservationsByUsername() {
    LocalDate localDate1 = LocalDate.of(2023, 12, 12);
    Member member = new Member("Mathilde", "kk", "kk", "kk", "jj", "ii", "uu", "yy");
    Car car = new Car("Ford", "Mustang", 500.0, 100);
    Reservation reservation = new Reservation(LocalDate.now(), member, car);
    Reservation reservation1 = new Reservation(localDate1, member, car);
    Mockito.when(memberRepository.findById("Mathilde")).thenReturn(Optional.of(member));
    Mockito.when(reservationRepository.findAllByMember(member)).thenReturn(List.of(reservation, reservation1));
    List<ReservationResponse> reservationResponses = reservationService.findReservationsByUsername("Mathilde");
    assertEquals(2,reservationResponses.size());
    assertNotNull(reservationResponses.get(0));
  }
}