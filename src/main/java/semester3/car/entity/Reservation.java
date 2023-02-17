package semester3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @CreationTimestamp
  private LocalDateTime reservationDate;

  private LocalDate rentalDate;

  @ManyToOne
  Car car;

  @ManyToOne
  Member member;

  public Reservation(LocalDate rentalDate, Member member, Car car) {
    this.rentalDate = rentalDate;
    this.member = member;
    this.car = car;
  }
}
