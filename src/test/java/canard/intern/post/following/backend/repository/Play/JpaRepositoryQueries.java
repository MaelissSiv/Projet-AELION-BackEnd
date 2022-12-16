package canard.intern.post.following.backend.repository.Play;


import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Collection;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JpaRepositoryQueries {

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    PoeRepository poeRepository;

    private static void displayCollection(Collection<?> collection) {
        for (var t:collection) {
            System.out.println("\t- " + t);
        }
    }

    @Test
    void traineesByLastnamePartialIgnoreCase() {
        String partialName = "Gac";
        var trainees = traineeRepository.findByLastnameIgnoreCase(partialName);
        displayCollection(trainees);
    }

    @Test
    void poesByTitleIgnoreCase() {
        String title = "java fullstack";
        var poes = poeRepository.findByTitleIgnoreCaseOrderByBeginDate(title);
        displayCollection(poes);

    }

    @Test
    void poesByType() {
        String poeType = "POEC";
        var poes = poeRepository.findByPoeType(poeType);
        displayCollection(poes);
    }

    @Test
    void poesStartingYear(){
        int year = 2022;
        var poes = poeRepository.findByBeginDateBetween(
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 12, 1)
        );
        displayCollection(poes);
    }

    @Test
    void poesStartingYearSorted(){
        int year = 2022;
        var poes = poeRepository.findByBeginDateBetween(
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 12, 1),
                Sort.by("beginDate")
        );
        displayCollection(poes);
    }

    @Test
    void poesStartingYear_JpqlQuery(){
        int year = 2022;
        var poes = poeRepository.findByBeginDateInYear(year);
        displayCollection(poes);
    }

    @Test
    void traineesByPoeTitleIgnoreCase() {
        String title = "java fullstack";
        var trainees = traineeRepository.findByPoeTitleIgnoreCase(title);
        displayCollection(trainees);
    }

    // Sort
    @Test
    void poesSorted(){
        var poesSortedByDateDesc = poeRepository.findAll(
                Sort.by("beginDate").descending()
        );
        displayCollection(poesSortedByDateDesc);
        var poesSortedByTitleDate = poeRepository.findAll(
                Sort.by("title")
                        .and(Sort.by("beginDate"))
        );
        displayCollection(poesSortedByTitleDate);
    }

    @Test
    void countPoeByPoeType(){
        var stats = poeRepository.countPoeByPoeType();
        displayCollection(stats);
    }

    @Test
    void countPoeByPoeType2(){
        var stats = poeRepository.countPoeByPoeType2();
        for (var statRow: stats) {
            System.out.println("\t-" +statRow.getPoeType() + ": " + statRow.getCountPoe());
        }
    }


    @Test
    void countTraineeByPoe() {
        var stats = poeRepository.countTraineesByPoe();
        for (var rowStat: stats) {
            System.out.println("\t- "
                    + rowStat.get("title", String.class)
                    + " : " + rowStat.get("traineeCount", Long.class));
        }
    }
}
