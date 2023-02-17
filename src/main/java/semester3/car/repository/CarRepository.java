package semester3.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import semester3.car.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
  public List<Car> findAllByBrand(String brand);
}
