package semester3.car.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semester3.car.dto.CarRequest;
import semester3.car.dto.CarResponse;
import semester3.car.dto.MemberRequest;
import semester3.car.dto.MemberResponse;
import semester3.car.service.CarService;
import semester3.car.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

  CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  //ADMIN ONLY
  @GetMapping
  List<CarResponse> getCars(){ return carService.getCars(true); }

  //ADMIN
  @GetMapping(path = "/{id}")
  CarResponse getCarById(@PathVariable int id) throws Exception {return carService.findCarById(id);}

  //ANONYMOUS
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  CarResponse addCar(@RequestBody CarRequest body){
    return carService.addCar(body);
  }

  //MEMBER
  @PutMapping(path = "/{id}")
  ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
    return carService.updateCar(id, body );
  }

  //ADMIN
  @PatchMapping("/discount/{id}/{bestDiscount}")
  void setBestDiscountForCar(@PathVariable int id, @PathVariable int bestDiscount) {
    carService.updateDiscount(id, bestDiscount);
  }

  //ADMIN
  @DeleteMapping("/{id}")
  void deleteCarByid(@PathVariable int id) {
    carService.deleteCarById(id);
  }
}

