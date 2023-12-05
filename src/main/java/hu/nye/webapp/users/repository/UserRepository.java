package hu.nye.webapp.users.repository;

import hu.nye.webapp.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
