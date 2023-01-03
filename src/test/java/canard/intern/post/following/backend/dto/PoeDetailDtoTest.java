package canard.intern.post.following.backend.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PoeDetailDtoTest {

    @Test
    void builder(){
        var poeDetailDto = PoeDetailDto.builder()
                .id(1)
                .title("Java Fullstack")
                .trainees(List.of(
                        TraineeDto.builder()
                                .id(33)
                                .lastname("Cool")
                                .build(),
                        TraineeDto.builder()
                                .id(34)
                                .lastname("Sanka")
                                .build()
                ))
                .build();
        System.out.println(poeDetailDto);

        // TODO: assert
    }

}