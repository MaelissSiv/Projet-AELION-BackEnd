package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.dto.IPoeTypeCountPoeDto;
import canard.intern.post.following.backend.dto.PoeTypeCountPoeDto;
import canard.intern.post.following.backend.entity.Poe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;

public interface PoeRepository extends JpaRepository<Poe,Integer> {
    List<Poe> findByTitleIgnoreCaseOrderByBeginDate(String title); //refactorer le nom

    List<Poe> findByBeginDateBetween(LocalDate date1, LocalDate date2);

    List<Poe> findByBeginDateBetween(LocalDate date1, LocalDate date2, Sort sort);

    List<Poe> findByPoeType(String poeType);

    @Query("SELECT p FROM Poe p WHERE EXTRACT (YEAR FROM p.beginDate) = :year ORDER BY p.beginDate")
    List<Poe> findByBeginDateInYear(int year);

    @Query("SELECT new canard.intern.post.following.backend.dto.PoeTypeCountPoeDto" +
            "(p.poeType, COUNT(p)) FROM Poe p GROUP BY p.poeType")
    List<PoeTypeCountPoeDto> countPoeByPoeType ();

    @Query("SELECT p.poeType as poeType, COUNT(p) as countPoe " + "FROM Poe p GROUP BY p.poeType")
    List<IPoeTypeCountPoeDto> countPoeByPoeType2();

    @Query("SELECT p as poe, COUNT (t.id) as traineeCount " +
            "FROM Trainee t RIGHT OUTER JOIN t.poe p " +
            "GROUP BY p " +
            "ORDER BY traineeCount ")
    List<Tuple> countTraineesByPoe();

}
