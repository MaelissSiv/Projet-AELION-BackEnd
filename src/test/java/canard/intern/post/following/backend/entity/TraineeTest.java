package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TraineeTest {
    //ORM Hibernate
    @Autowired
    EntityManager entityManager;

    @Test
    void mapping_OK_allRequiredFields() {
        //given
        String lastname= "Bond";
        String firstname= "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950,1,6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";

        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        //then
        entityManager.persist(trainee); // INSERT

        // force synchro between hibernate et DB
        entityManager.flush();
        //System.out.println(trainee);

        //verify: ID has been generated
        Integer idGenerated = trainee.getId();
        assertNotNull(idGenerated);

        //Verify: data has been inserted in database

        //clear Hibernate cache
        entityManager.clear();
        var traineeRead = entityManager.find(Trainee.class, idGenerated);
        //System.out.println(traineeRead);
        assertNotNull(traineeRead);
        assertAll(
                () -> assertEquals(idGenerated, traineeRead.getId(), "trainee id"),
                () -> assertEquals(lastname, traineeRead.getLastname(),"trainee lastname"),
                () -> assertEquals(firstname, traineeRead.getFirstname(),"trainee firstname"),
                () -> assertEquals(gender, traineeRead.getGender(),"trainee gender"),
                () -> assertEquals(email, traineeRead.getEmail(),"trainee email"),
                () -> assertEquals(phoneNumber, traineeRead.getPhoneNumber(),"trainee phonenumber"),
                () -> assertEquals(birthdate, traineeRead.getBirthdate(),"trainee birthdate")
        );

    }

    @ParameterizedTest
    @EnumSource(Gender.class)
    @NullSource
    void mapping_OK_genderNullable( Gender gender) {
        //given
        String lastname= "Bond";
        String firstname= "James";
        LocalDate birthdate = LocalDate.of(1950,1,6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";

        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        //then
        entityManager.persist(trainee); // INSERT

        // force synchro between hibernate et DB
        entityManager.flush();
        System.out.println(trainee);

        //verify: ID has been generated
        Integer idGenerated = trainee.getId();
        assertNotNull(idGenerated);

        //Verify: data has been inserted in database

        //clear Hibernate cache
        entityManager.clear();
        var traineeRead = entityManager.find(Trainee.class, idGenerated);
        //System.out.println(traineeRead);
        assertNotNull(traineeRead);
        assertAll(
                () -> assertEquals(idGenerated, traineeRead.getId(), "trainee id"),
                () -> assertEquals(lastname, traineeRead.getLastname(),"trainee lastname"),
                () -> assertEquals(firstname, traineeRead.getFirstname(),"trainee firstname"),
                () -> assertEquals(gender, traineeRead.getGender(),"trainee gender"),
                () -> assertEquals(email, traineeRead.getEmail(),"trainee email"),
                () -> assertEquals(phoneNumber, traineeRead.getPhoneNumber(),"trainee phonenumber"),
                () -> assertEquals(birthdate, traineeRead.getBirthdate(),"trainee birthdate")
        );

    }

    @ParameterizedTest
    @ValueSource(strings = {"B","Bond","idpywsatabjmnxtsielflfhusnzsrwqzflnljbwjxtqhpmkqxk"})
    void mapping_OK_RequiredLastname(String lastname) {
        //given
        String firstname= "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950,1,6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";

        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        //then
        entityManager.persist(trainee); // INSERT

        // force synchro between hibernate et DB
        entityManager.flush();
        //System.out.println(trainee);

        //verify: ID has been generated
        Integer idGenerated = trainee.getId();
        assertNotNull(idGenerated);

        //Verify: data has been inserted in database

        //clear Hibernate cache
        entityManager.clear();
        var traineeRead = entityManager.find(Trainee.class, idGenerated);
        //System.out.println(traineeRead);
        assertNotNull(traineeRead);
        assertAll(
                () -> assertEquals(idGenerated, traineeRead.getId(), "trainee id"),
                () -> assertEquals(lastname, traineeRead.getLastname(),"trainee lastname"),
                () -> assertEquals(firstname, traineeRead.getFirstname(),"trainee firstname"),
                () -> assertEquals(gender, traineeRead.getGender(),"trainee gender"),
                () -> assertEquals(email, traineeRead.getEmail(),"trainee email"),
                () -> assertEquals(phoneNumber, traineeRead.getPhoneNumber(),"trainee phonenumber"),
                () -> assertEquals(birthdate, traineeRead.getBirthdate(),"trainee birthdate")
        );

    }

    @ParameterizedTest
    @ValueSource(strings = {"","idpywsatabjmnxtsielflfhusnzsrwqzflnljbwjxtqhpmkqxkkk"})
    @NullSource
    void mapping_KO_RequiredLastname(String lastname) {
        //given
        String firstname= "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950,1,6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";

        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        //then and verify

        assertThrows(PersistenceException.class, () -> {

            entityManager.persist(trainee); // INSERT
            // force synchro between hibernate et DB
            entityManager.flush();
            //System.out.println(trainee);

        });
    }

    @ParameterizedTest
    @CsvSource({
            "Bond, James, M, 1950-01-12, james@bond.fr, +33707070707",
            "Miaou, Isis, F, 2000-01-12, miaou@isis.fr, +33707070708",
            "Roi, Arthur,M, 1968-05-03, roi@arthur.fr,",

    })
    void mapping_OK_allRequiredFields_CSV(
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
        //then
        entityManager.persist(trainee); // INSERT

        // force synchro between hibernate et DB
        entityManager.flush();
        //System.out.println(trainee);

        //verify: ID has been generated
        Integer idGenerated = trainee.getId();
        assertNotNull(idGenerated);

        //Verify: data has been inserted in database

        //clear Hibernate mémoire
        entityManager.clear();
        var traineeRead = entityManager.find(Trainee.class, idGenerated);
        //System.out.println(traineeRead);
        assertNotNull(traineeRead);
        assertAll(
                () -> assertEquals(idGenerated, traineeRead.getId(), "trainee id"),
                () -> assertEquals(lastname, traineeRead.getLastname(),"trainee lastname"),
                () -> assertEquals(firstname, traineeRead.getFirstname(),"trainee firstname"),
                () -> assertEquals(gender, traineeRead.getGender(),"trainee gender"),
                () -> assertEquals(email, traineeRead.getEmail(),"trainee email"),
                () -> assertEquals(phoneNumber, traineeRead.getPhoneNumber(),"trainee phonenumber"),
                () -> assertEquals(birthdate, traineeRead.getBirthdate(),"trainee birthdate")
        );

    }
}