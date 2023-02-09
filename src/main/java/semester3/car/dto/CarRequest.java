package semester3.car.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import semester3.car.entity.Car;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {

  private String brand;
  private String model;
  private Double pricePrDay;
  private int bestDiscount;

  public static Car getCarEntity(CarRequest carRequest) {
    return new Car(carRequest.brand, carRequest.model, carRequest.pricePrDay, carRequest.bestDiscount);
  }

  public CarRequest(Car car) {
    this.brand = car.getBrand();
    this.model = car.getModel();
    this.pricePrDay = car.getPricePrDay();
    this.bestDiscount = getBestDiscount();
  }
}
