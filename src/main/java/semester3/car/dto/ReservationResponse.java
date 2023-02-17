package semester3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import semester3.car.entity.Car;
import semester3.car.entity.Member;
import semester3.car.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
  int id;
  int carId;
  String memberUsername;

  @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
  LocalDate rentalDate;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
  LocalDateTime reservationDate;


  //Convert Member Entity to Member DTO
  public ReservationResponse(Reservation r, boolean includeAll) {
    this.id = r.getId();
    this.carId = r.getCar().getId();
    this.memberUsername = r.getMember().getUsername();
    this.rentalDate = r.getRentalDate();
    if(includeAll){
      this.reservationDate = r.getReservationDate();
    }
  }
}
