package canard.intern.post.following.backend.dto;

import canard.intern.post.following.backend.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import canard.intern.post.following.backend.validator.DateLessThan;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter @Setter
public class TraineeDto {
    private Integer id;

    @NotBlank
    private String lastname;

    @NotBlank // non vide, ex ''
    private String firstname;

    private Gender gender;

    @NotNull // pas de données = null
    @DateLessThan
    private LocalDate birthdate;

    @Pattern(regexp = "^\\+(?:[0-9]●?){6,14}[0-9]$")
    private String phoneNumber;

    @NotNull
    @Email // or @Pattern
    private String email;
}
