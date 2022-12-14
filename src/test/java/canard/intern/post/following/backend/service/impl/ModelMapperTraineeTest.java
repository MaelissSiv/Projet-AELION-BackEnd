package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ModelMapperTraineeTest {

    static ModelMapper modelMapper;


    @BeforeAll
    static void initMapper(){
        modelMapper = new ModelMapper();

    }

    @Test
    void mapTraineeEntityToDto() {
        int id = 12345;
        String lastname= "Bond";
        String firstname= "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950,1,6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";

        var traineeEntity = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

        var traineeDto = modelMapper.map(traineeEntity, TraineeDto.class);
        assertNotNull(traineeDto);
        assertSame(TraineeDto.class, traineeDto.getClass());
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "trainee dto id"),
                () -> assertEquals(lastname, traineeDto.getLastname(), "trainee dto lastname"),
                () -> assertEquals(firstname, traineeDto.getFirstname(), "trainee dto firstname"),
                () -> assertEquals(gender, traineeDto.getGender(), "trainee dto gender"),
                () -> assertEquals(birthdate, traineeDto.getBirthdate(), "trainee dto birthdate"),
                () -> assertEquals(email, traineeDto.getEmail(), "trainee dto email"),
                () -> assertEquals(phoneNumber, traineeDto.getPhoneNumber(), "trainee dto phone Number")

        );

    }

    @Test
    void mapTraineeDtoEntity() {
        Integer id = 1;
        String lastname = "Bond";
        String firstname = "James";
        Gender gender = Gender.M;
        String email = "james.bon@gmail.com";
        String phonenumber = "0659874856";
        LocalDate birthdate = LocalDate.of(1950, 1, 6);
        var traineeDto = TraineeDto.builder()
                .id(id)
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .email(email)
                .phoneNumber(phonenumber)
                .birthdate(birthdate)
                .build();
        var trainee = modelMapper.map(traineeDto, Trainee.class);
        assertNotNull(trainee);
        assertSame(Trainee.class, trainee.getClass());
        assertAll(
                () -> assertEquals(id, trainee.getId(), "trainee id"),
                () -> assertEquals(lastname, trainee.getLastname(), "trainee lastname"),
                () -> assertEquals(firstname, trainee.getFirstname(), "trainee firstname"),
                () -> assertEquals(gender, trainee.getGender(), "trainee gender"),
                () -> assertEquals(email, trainee.getEmail(), "email gender"),
                () -> assertEquals(phonenumber, trainee.getPhoneNumber(), "phonenumber gender"),
                () -> assertEquals(birthdate, trainee.getBirthdate(), "birthdate gender")
        );
    }
}
