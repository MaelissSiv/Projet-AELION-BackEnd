package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.repository.TraineeRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("testu")
public class TraineeRepositoryTest {

    @Autowired
    TraineeRepository traineeRepository;
    //@Rollback(value = false)
    @Autowired
    //EntityManager entityManager;
    TestEntityManager entityManager; //usefull wrapper of entity manager only for test
    @ParameterizedTest
    @CsvSource({
            "Bond, James, M, 1950-01-12, james@bond.fr, +33707070707",
            "Miaou, Isis, F, 2000-01-12, miaou@isis.fr, +33707070708",
            "Roi, Arthur,M, 1968-05-03, roi@arthur.fr,",

    })
    void save_OK_allRequiredFields_CSV(
            //given
            String lastname,
            String firstname,
            Gender gender,
            LocalDate birthdate,
            String email,
            String phoneNumber
    ) {

        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

        //traineeRepository.save(trainee);
        traineeRepository.saveAndFlush(trainee);

        Assertions.assertNotNull(trainee.getId());

        //(optional) verify 2: read data from db to check if data has been inserted

    }

    @Test
    void findAll() {
        //given: save data in database
        var traineesDatabase = List.of(
                Trainee.builder()
                        .lastname("Bond")
                        .firstname("James")
                        .gender(Gender.M)
                        .birthdate(LocalDate.of(1950,1,2))
                        .email("james@bond.fr")
                        .phoneNumber("+33707070707")
                        .build(),
                Trainee.builder()
                        .lastname("Gache")
                        .firstname("Gabin")
                        .gender(Gender.M)
                        .birthdate(LocalDate.of(2020,7,14))
                        .email("gabin@gache.fr")
                        .phoneNumber("+33707070708")
                        .build(),
                Trainee.builder()
                        .lastname("Ellestou")
                        .firstname("Jeanne")
                        .gender(Gender.F)
                        .birthdate(LocalDate.of(1975,10,12))
                        .email("ellestou@jeanne.fr")
                        .phoneNumber("+33707070709")
                        .build()
        );

        traineesDatabase.forEach(t -> entityManager.persist(t));
        entityManager.flush();

        //when: read all trainees from database
        var traineesRead= traineeRepository.findAll();
        System.out.println(traineesRead);

        //verify: all data has been read( size and content)
        Assertions.assertEquals(traineesDatabase.size(), traineesRead.size());

        //TODO: check content
    }

    @Test
    void findById_present() {
        //given: ???
        //int id = 12345;
        var traineeDataBase = Trainee.builder()
                .lastname("Bond")
                .firstname("James")
                .gender(Gender.M)
                .birthdate(LocalDate.of(1950,1,2))
                .email("james@bond.fr")
                .phoneNumber("+33707070707")
                .build();
        int id = entityManager.persistAndGetId(traineeDataBase, Integer.class);
        entityManager.flush();
        entityManager.clear(); //vide mémoire hibernate

        //then
        var opTrainee= traineeRepository.findById(id);

        // verify
        Assertions.assertTrue(opTrainee.isPresent(), "trainee is present");

    }
    @Test
    void findById_absent() {
        //given: empty database
        int id = 12345;

        //then
        var opTrainee= traineeRepository.findById(id);

        // verify
        Assertions.assertTrue(opTrainee.isEmpty());


    }

}
