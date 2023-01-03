package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.service.StagiaireService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stagiaire") //http://localhost:8080/api/stagiaire
public class StagiaireController {
    private StagiaireService service = StagiaireService.getInstance();
    @GetMapping // il faut une requete get sur le http.../api/stagiaire
    public List<Trainee> findAll() {

//        StagiaireService service = StagiaireService.getInstance();
//
//        StagiaireService otherService = StagiaireService.getInstance();

        return service.findAll();
    }
@GetMapping("/add")
    public Trainee add() {
        return service.add();
    }
}


