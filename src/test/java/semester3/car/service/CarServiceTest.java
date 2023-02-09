package semester3.car.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import semester3.car.dto.CarRequest;
import semester3.car.dto.CarResponse;
import semester3.car.entity.Car;
import semester3.car.repository.CarRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

  @Mock
  CarRepository carRepository;

  CarService carService;

  @BeforeEach
  void setUp() {
    carService = new CarService(carRepository);
  }

  @Test
  void getCars() {
    Car c1 = new Car("Toyota", "Yaris", 500.0, 200);
    Car c2 = new Car("Fiat", "Punto", 300.0, 100);
    c1.setCreated(LocalDateTime.now());
    c2.setCreated(LocalDateTime.now());
    Mockito.when(carRepository.findAll()).thenReturn(List.of(c1,c2));
    List<CarResponse> cars = carService.getCars(true);
    assertEquals(2,cars.size());
    assertNotNull(cars.get(0).getCreated());
  }

  @Test
  void addCar() {
    Car c1 = new Car("Toyota", "Yaris", 500.0, 200);
    Mockito.when(carRepository.save(any(Car.class))).thenReturn(c1);

    CarRequest request = new CarRequest(c1);
    CarResponse response = carService.addCar(request);
    assertEquals("Yaris",response.getModel());
  }

  @Test
  void findCarById() {
    Car c1 = new Car("Toyota", "Yaris", 500.0, 200);
    Car c2 = new Car("Fiat", "Punto", 300.0, 100);
    c1.setId(1);
    c2.setId(2);
    Mockito.when(carRepository.findById(2)).thenReturn(Optional.of(c2));
    CarResponse carResponse = carService.findCarById(2);

    assertEquals("Punto", carResponse.getModel());
  }

  @Test
  void updateCar() {
    Car c1 = new Car("Toyota", "Yaris", 500.0, 200);
    c1.setId(1);
    Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(c1));
    Car car = new Car("Toyota", "Auris", 500.0, 200);
    CarRequest carRequest = new CarRequest(car);
    ResponseEntity<Boolean> response = carService.updateCar(1, carRequest);
    ResponseEntity<Boolean> testResponse = new ResponseEntity<Boolean>(true, HttpStatus.OK);
    assertEquals(testResponse, response);
  }
}