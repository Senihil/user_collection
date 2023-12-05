package hu.nye.webapp.users.repository;

import hu.nye.webapp.users.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;

    @Test
    public void UserRepositoryShouldReturnSavedUsers() {
        User userTest = new User();
        userTest.setName("Hugh Mungus");
        userTest.setUsername("hugh");
        userTest.setEmail("hugh001@gmail.com");

        User savedUserTest = userRepositoryTest.save(userTest);

        Assertions.assertThat(savedUserTest).isNotNull();
        Assertions.assertThat(savedUserTest.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepositoryGetAllShouldReturnMoreThanOneUser() {
        User userTest1 = new User();
        userTest1.setName("Hugh Mungus");
        userTest1.setUsername("hugh");
        userTest1.setEmail("hugh001@gmail.com");

        User userTest2 = new User();
        userTest2.setName("Jane Doe");
        userTest2.setUsername("jane");
        userTest2.setEmail("dough007@gmail.com");

        userRepositoryTest.save(userTest1);
        userRepositoryTest.save(userTest2);

        List<User> userListTest = userRepositoryTest.findAll();

        Assertions.assertThat(userListTest).isNotNull();
        Assertions.assertThat(userListTest.size()).isEqualTo(2);
    }

    @Test
    public void UserRepositoryFindByIdShouldReturnUser() {
        User userTest = new User();
        userTest.setName("Hugh Mungus");
        userTest.setUsername("hugh");
        userTest.setEmail("hugh001@gmail.com");

        userRepositoryTest.save(userTest);

        User pokemonList = userRepositoryTest.findById(userTest.getId()).get();

        Assertions.assertThat(pokemonList).isNotNull();
    }

    @Test
    public void UserRepositoryUpdateUserShouldReturnUserNotNull() {
        User userTest = new User();
        userTest.setName("Hugh Mungus");
        userTest.setUsername("hugh");
        userTest.setEmail("hugh001@gmail.com");

        userRepositoryTest.save(userTest);

        User userSaveTest = userRepositoryTest.findById(userTest.getId()).get();
        userSaveTest.setUsername("Mundungus");

        User updatedUserTest = userRepositoryTest.save(userSaveTest);

        Assertions.assertThat(updatedUserTest.getUsername()).isNotNull();
    }

    @Test
    public void UserRepositoryDeleteUserShouldReturnEmpty() {
        User userTest = new User();
        userTest.setName("Hugh Mungus");
        userTest.setUsername("hugh");
        userTest.setEmail("hugh001@gmail.com");

        userRepositoryTest.save(userTest);

        userRepositoryTest.deleteById(userTest.getId());
        Optional<User> userReturnTest = userRepositoryTest.findById(userTest.getId());

        Assertions.assertThat(userReturnTest).isEmpty();
    }

}