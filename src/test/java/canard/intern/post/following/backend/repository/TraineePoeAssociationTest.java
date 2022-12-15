package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Trainee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

//this is not a unit test
//goal: how to deal with association trainee => poe
//@ActiveProfiles("testu")

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) //keep db from application
class TraineePoeAssociationTest {

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    PoeRepository poeRepository;

    @Autowired
    EntityManager entityManager; //ORM Hibernate

    @Test
    void TraineeWithPoe(){
        var trainee = traineeRepository.findById(1).get();
        //select
        //      trainee0_.id as id1_1_0_, trainee0_.birthdate as birthdat2_1_0_, trainee0_.email as email3_1_0_, trainee0_.firstname as firstnam4_1_0_, trainee0_.gender as gender5_1_0_, trainee0_.lastname as lastname6_1_0_, trainee0_.phone_number as phone_nu7_1_0_, trainee0_.poe_id as poe_id8_1_0_, poe1_.id as id1_0_1_, poe1_.begin_date as begin_da2_0_1_, poe1_.end_date as end_date3_0_1_, poe1_.poe_type as poe_type4_0_1_, poe1_.title as title5_0_1_
        // from
        //      trainees trainee0_
        //      left outer join poes poe1_ on trainee0_.poe_id=poe1_.id
        //where trainee0_.id=?
        //      Trainee(id=1, lastname=Neymar, firstname=Jean, gender=M, birthdate=1970-05-24, phoneNumber=+330102030405, email=jean.neymar@dodo.org, poe=Poe(id=1, title=Java Fullstack, poeType=POEI, beginDate=2022-10-24, endDate=2023-01-27))
        //      Poe(id=1, title=Java Fullstack, poeType=POEI, beginDate=2022-10-24, endDate=2023-01-27)
        var poe = trainee.getPoe();
        System.out.println(trainee);
        System.out.println(poe);

    }


    @Test
    void poeWithTrainees() {
        int poeId = 1;
        var poe = poeRepository.findById(poeId).get();
        System.out.println(poe);

        // how to fetch trainees from this poe?
        // SQL: select * from trainees where poe_id =2;
        //
        String sql = "select t from Trainee t where t.poe.id = ?1";
        var trainees = entityManager.createQuery(sql, Trainee.class)
                .setParameter(1, poeId)
                .getResultList();
        for(var trainee: trainees){
            System.out.println("\t- " +trainee);
        }
    }

    @Test
    void poeWithTraineesJpaRepository (){
        int poeId = 1;
        var poe = poeRepository.findById(poeId).get();
        System.out.println(poe);

        // how to fetch trainees from this poe?
        var trainees = traineeRepository.findByPoeId(poeId);
        for(var trainee: trainees){
            System.out.println("\t- " +trainee);
        }
    }
}