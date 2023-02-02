package semester3.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import semester3.car.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
