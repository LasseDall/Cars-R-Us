package semester3.car.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import semester3.car.dto.CarRequest;
import semester3.car.dto.CarResponse;
import semester3.car.dto.MemberRequest;
import semester3.car.dto.MemberResponse;
import semester3.car.entity.Car;
import semester3.car.entity.Member;
import semester3.car.repository.CarRepository;

import java.util.List;

@Service
public class CarService {

  CarRepository carRepository;

  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<CarResponse> getCars(boolean includeAll) {
    List<Car> cars = carRepository.findAll();

    List<CarResponse> carResponses = cars.stream().map(c -> new CarResponse(c, includeAll)).toList();
    return carResponses;
  }

  public CarResponse findCarById(int id) {
    Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
    return new CarResponse(car, false);
  }

  public CarResponse addCar(CarRequest carRequest) {
    Car newCar = CarRequest.getCarEntity(carRequest);
    newCar = carRepository.save(newCar);

    return new CarResponse(newCar, false);
  }

  public ResponseEntity<Boolean> updateCar(int id, CarRequest carRequest) {
    Car updatedCar = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    updatedCar.setBrand(carRequest.getBrand());
    updatedCar.setModel(carRequest.getModel());
    updatedCar.setPricePrDay(carRequest.getPricePrDay());
    updatedCar.setBestDiscount(carRequest.getBestDiscount());

    carRepository.save(updatedCar);

    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

  public void updateDiscount(int id, int bestDiscount) {
    Car updatedCar = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));

    updatedCar.setBestDiscount(bestDiscount);
    carRepository.save(updatedCar);
  }

  public void deleteCarById(int id) {
    Car deleteCar = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
    carRepository.delete(deleteCar);
  }
}

