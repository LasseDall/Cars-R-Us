package semester3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "car_brand", nullable = false, length = 50)
  private String brand;

  @Column(name = "car_model", nullable = false, length = 60)
  private String model;

  @Column(name = "rental_price_day")
  private Double pricePrDay;

  @Column(name = "max_discount")
  private int bestDiscount;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime lastEdited;

  @OneToMany(mappedBy = "car")
  private List<Reservation> reservations;

  public void addReservation(Reservation reservation) {
    if (reservations == null) {
      reservations = new ArrayList<>();
    }
    reservations.add(reservation);
  }

  public Car(String brand, String model, Double pricePrDay, int bestDiscount) {
    this.brand = brand;
    this.model = model;
    this.pricePrDay = pricePrDay;
    this.bestDiscount = bestDiscount;
  }
}




