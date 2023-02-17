package semester3.car.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semester3.car.dto.MemberRequest;
import semester3.car.dto.MemberResponse;
import semester3.car.dto.ReservationRequest;
import semester3.car.dto.ReservationResponse;
import semester3.car.service.ReservationService;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

  ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @GetMapping("/{username}")
  List<ReservationResponse> getReservationsByUsername(@PathVariable String username) {return reservationService.findReservationsByUsername(username);}

  //ANONYMOUS
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ReservationResponse addReservation(@RequestBody ReservationRequest body){
    return reservationService.addReservation(body);
  }

  //ADMIN
  @PostMapping(path = "/{username}/{carId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ReservationResponse makeReservation(@RequestBody ReservationRequest reservationRequest, @PathVariable int carId, @PathVariable String username) {
    reservationRequest.setCarId(carId);
    reservationRequest.setMemberUsername(username);
    return reservationService.addReservation(reservationRequest);
  }
}
