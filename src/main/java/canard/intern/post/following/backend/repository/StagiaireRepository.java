package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Trainee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StagiaireRepository {

    // rendre single tone notre repository
    //3 acteurs: Attribut, constructeur privé et
    private static StagiaireRepository instance;
    private List<Trainee> stagiaires = new ArrayList<>(); //représentation concrète de list est une arraylist

    private StagiaireRepository () {
        _feed();
    }

    public static StagiaireRepository getInstance() {
        if (StagiaireRepository.instance == null) {
            StagiaireRepository.instance = new StagiaireRepository();
        }
        return StagiaireRepository.instance;

    }


    public List<Trainee> findAll() {

        return stagiaires;
    }

    public Trainee add(Trainee trainee) {
        stagiaires.add(trainee);
        return trainee;
    }
    private void _feed(){
        Trainee stagiaire = new Trainee();
        stagiaire.setLastname("Latte");
        stagiaire.setFirstname("Trudy");
        stagiaire.setId(1);
        stagiaire.setBirthdate(LocalDate.of(1989, 1, 15));
        stagiaire.setEmail("trudy@latte.com");


        stagiaires.add(stagiaire);

    }
}
