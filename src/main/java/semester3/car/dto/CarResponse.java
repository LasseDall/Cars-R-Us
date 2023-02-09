package semester3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import semester3.car.entity.Car;
import semester3.car.entity.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

  private int id;
  private String brand;
  private String model;
  private Double pricePrDay;
  private Integer bestDiscount;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
  LocalDateTime created;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
  LocalDateTime lastEdited;

    //Convert Member Entity to Member DTO
    public CarResponse(Car car, boolean includeAll) {
      this.id = car.getId();
      this.brand = car.getBrand();
      this.model = car.getModel();
      this.pricePrDay = car.getPricePrDay();
      if(includeAll){
        this.bestDiscount = car.getBestDiscount();
        this.created = car.getCreated();
        this.lastEdited = car.getLastEdited();
      }
    }
  }

