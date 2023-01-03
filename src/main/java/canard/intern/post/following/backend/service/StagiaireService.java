package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.repository.StagiaireRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StagiaireService {

    private static StagiaireService instance;

    private StagiaireRepository repository = StagiaireRepository.getInstance();
    private StagiaireService() {
        System.out.println("I'm the service constructor");
    }

    public static StagiaireService getInstance() {
        if (StagiaireService.instance == null){
            StagiaireService.instance = new StagiaireService();
        }
        return StagiaireService.instance;
    }
    public List<Trainee> findAll() {
        return repository.findAll();
    }

    public Trainee add() {
        Trainee stagiaire = new Trainee();
        stagiaire.setLastname("Talaron");
        stagiaire.setFirstname("Delphine");
        stagiaire.setId(2);
        stagiaire.setBirthdate(LocalDate.of(1999, 12, 10));
        stagiaire.setEmail("delphine@talaron.com");

        return repository.add(stagiaire);
    }
}
