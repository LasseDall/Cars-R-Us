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
import semester3.car.security.entity.Role;
import semester3.car.security.entity.UserWithRoles;
import semester3.car.security.repository.UserWithRolesRepository;

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
  private UserWithRolesRepository userWithRolesRepository;
  final String passwordUsedByAll = "test12";


  public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository, UserWithRolesRepository userWithRolesRepository) {
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
    this.reservationRepository = reservationRepository;
    this.userWithRolesRepository = userWithRolesRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    ArrayList<Car> cars = new ArrayList<>();
    cars.add(new Car("Ford", "Fiesta", 500.0, 400));
    cars.add(new Car("Fiat", "Punto", 400.0, 300));
    cars.add(new Car("Citroen", "Berlingo", 600.0, 500));

    ArrayList<Member> members = new ArrayList<>();
    members.add(new Member("Lasse", passwordUsedByAll, "u,mbjsak", "Lasse", "Dall", "Højgade", "København S", "2300"));
    members.add(new Member("Jørgen", passwordUsedByAll, "uajknhk", "Jørgen", "Jørgensen", "Bredgade", "København K", "2100"));
    members.add(new Member("Mathilde", passwordUsedByAll, "jhwdk", "Mathilde", "Rask", "Dovregade", "København S", "2300"));





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

    setupUserWithRoleUsers();


  }
  private void setupUserWithRoleUsers() {

    System.out.println("******************************************************************************");
    System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
    System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
    System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
    System.out.println("******************************************************************************");
    UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
    UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
    UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
    UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
    user1.addRole(Role.USER);
    user1.addRole(Role.ADMIN);
    user2.addRole(Role.USER);
    user3.addRole(Role.ADMIN);

    //No Role assigned to user4
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
    userWithRolesRepository.save(user4);
  }
}
