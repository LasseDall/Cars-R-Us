package semester3.car.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import semester3.car.entity.Car;
import semester3.car.entity.Member;
import semester3.car.entity.Reservation;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {
    int carId;
    String memberUsername;
    LocalDate rentalDate;

  public ReservationRequest(int carId, String memberUsername, LocalDate rentalDate) {
    this.carId = carId;
    this.memberUsername = memberUsername;
    this.rentalDate = rentalDate;
  }
}
