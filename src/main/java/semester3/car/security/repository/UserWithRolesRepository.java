package semester3.car.security.repository;

import semester3.car.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import semester3.car.security.entity.UserWithRoles;

public interface
UserWithRolesRepository extends JpaRepository<UserWithRoles,String> {
    Boolean existsByEmail(String email);
}
