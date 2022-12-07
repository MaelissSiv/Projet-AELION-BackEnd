package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.DTO.TraineeDto;
import canard.intern.post.following.backend.enums.Gender;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {

    @GetMapping
    public List<TraineeDto> getAll(){
         return List.of(
                 TraineeDto.builder()
                         .id(1)
                         .lastname("Bond")
                         .firstname("James")
                         .gender(Gender.MALE)
                         .birthdate(LocalDate.of(1945,6,16))
                         .build(),
                 TraineeDto.builder()
                         .id(2)
                         .lastname("Aubert")
                         .firstname("Jean-Luc")
                         .build(),
                 TraineeDto.builder()
                         .id(3)
                         .lastname("Naymar")
                         .firstname("Jean")
                         .build(),
                 TraineeDto.builder()
                         .id(4)
                         .lastname("Moneypenny")
                         .firstname("Miss")
                         .gender(Gender.FEMALE)
                         .build()

         );
    }
    /**
     * GET/api/trainees/{id}
     *
     * Example: in order to get trainee of id3, call
     *  GET/api/trainees/3
     * @param id id of trainee to get
     * @return trainee with this id if found
     */

    @GetMapping("/{id}")
    public TraineeDto getById(@PathVariable("id") int id) {
        return TraineeDto.builder()
                .id(id)
                .lastname("Moneypenny")
                .firstname("Miss")
                .gender(Gender.FEMALE)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto create(@Valid @RequestBody TraineeDto traineeDto){
        traineeDto.setId(10);
        return traineeDto;
    }

    @PutMapping ("/{id}")
    public void update (
            @PathVariable("id") int id,
            @Valid @RequestBody TraineeDto traineeDto
    ){
        if (Objects.nonNull(traineeDto.getId()) && (traineeDto.getId() != id)) {
            throw new IllegalArgumentException ();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        // TODO: remove trainee with this id
    }
}
