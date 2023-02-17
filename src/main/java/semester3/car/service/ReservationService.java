package semester3.car.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import semester3.car.dto.MemberResponse;
import semester3.car.dto.ReservationRequest;
import semester3.car.dto.ReservationResponse;
import semester3.car.entity.Car;
import semester3.car.entity.Member;
import semester3.car.entity.Reservation;
import semester3.car.repository.CarRepository;
import semester3.car.repository.MemberRepository;
import semester3.car.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

  ReservationRepository reservationRepository;
  CarRepository carRepository;
  MemberRepository memberRepository;

  public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, MemberRepository memberRepository) {
    this.reservationRepository = reservationRepository;
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
  }

  public ReservationResponse addReservation(ReservationRequest reservationRequest){

    Car car = carRepository.findById(reservationRequest.getCarId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
    Member member = memberRepository.findById(reservationRequest.getMemberUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    Reservation newReservation = new Reservation(reservationRequest.getRentalDate(), member, car);
    if (newReservation.getRentalDate().isBefore(LocalDate.now())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You cant rent a car for a day in the past");
    }
    if (newReservation.getCar().getReservations() != null) {
      for (Reservation reservation:car.getReservations()) {
        if (reservation.getRentalDate().isEqual(newReservation.getRentalDate())) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car rented that date");
        }
      }
    }
    newReservation = reservationRepository.save(newReservation);

    return new ReservationResponse(newReservation, false);
  }

  public List<ReservationResponse> findReservationsByUsername(String username) {
    Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    List<ReservationResponse> reservationResponses = reservationRepository.findAllByMember(member).stream().map(r->new ReservationResponse(r, true)).toList();
    return reservationResponses;
  }
}
