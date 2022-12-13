package canard.intern.post.following.backend.jpa.learn;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@DataJpaTest
public class TraineeLearn {
    //ORM Hibernate
    @Autowired
    EntityManager entityManager;

    @Test
    void saveAndRead() {
        var trainee = Trainee.builder()
                .lastname("Bond")
                .firstname("James")
                .gender(Gender.M)
                .birthdate(LocalDate.of(1950, 1, 6))
                .email("james@bond.fr")
                .phoneNumber("+337070707")
                .build();
        entityManager.persist(trainee); // INSERT

        // force synchro between hibernate et DB
        entityManager.flush();
        System.out.println(trainee);
        int idGenerated = trainee.getId();

        //clear Hibernate cache
        entityManager.clear();
        var traineeRead = entityManager.find(Trainee.class, idGenerated);
        System.out.println(traineeRead);

    }
}
