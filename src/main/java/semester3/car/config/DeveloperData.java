package semester3.car.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import semester3.car.entity.Car;
import semester3.car.entity.Member;
import semester3.car.entity.Reservation;
import semester3.car.repository.CarRepository;
import semester3.car.repository.MemberRepository;
import semester3.car.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeveloperData implements ApplicationRunner {

  private CarRepository carRepository;
  private MemberRepository memberRepository;
  private ReservationRepository reservationRepository;

  public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
    this.reservationRepository = reservationRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    ArrayList<Car> cars = new ArrayList<>();
    cars.add(new Car("Ford", "Fiesta", 500.0, 400));
    cars.add(new Car("Fiat", "Punto", 400.0, 300));
    cars.add(new Car("Citroen", "Berlingo", 600.0, 500));



    ArrayList<Member> members = new ArrayList<>();
    members.add(new Member("Lasse", "1234", "ll@live.dk", "Lasse", "Dall", "Højgade", "København S", "2300"));
    members.add(new Member("Jørgen", "jørgen2", "jørgen@live.dk", "Jørgen", "Jørgensen", "Bredgade", "København K", "2100"));
    members.add(new Member("Mathilde", "mth3", "mm@live.dk", "Mathilde", "Rask", "Dovregade", "København S", "2300"));

    List<String> colors = new ArrayList<>();
    List<String> colors1 = new ArrayList<>();
    List<String> colors2 = new ArrayList<>();
    colors.add("grøn");
    colors.add("lilla");
    members.get(0).setFavoriteCarColors(colors);
    colors1.add("gul");
    members.get(1).setFavoriteCarColors(colors1);
    colors2.add("rød");
    colors2.add("blå");
    members.get(2).setFavoriteCarColors(colors2);


    Map<String, String> phoneNumbers = new HashMap<>();
    Map<String, String> phoneNumbers1 = new HashMap<>();
    Map<String, String> phoneNumbers2 = new HashMap<>();
    phoneNumbers.put("Home", "1234");
    phoneNumbers.put("Work", "5678");
    phoneNumbers1.put("Home", "1111");
    phoneNumbers1.put("Work", "2222");
    phoneNumbers2.put("Home", "8888");
    phoneNumbers2.put("Work", "7890");
    members.get(0).setPhones(phoneNumbers);
    members.get(1).setPhones(phoneNumbers1);
    members.get(2).setPhones(phoneNumbers2);

    memberRepository.saveAll(members);
    carRepository.saveAll(cars);

    Reservation reservation = new Reservation(LocalDate.of(2023, 11, 30), members.get(0), cars.get(0));
    Reservation reservation2 = new Reservation(LocalDate.of(2023, 10, 11), members.get(1), cars.get(0));
    reservationRepository.save(reservation);
    reservationRepository.save(reservation2);




  }
}
