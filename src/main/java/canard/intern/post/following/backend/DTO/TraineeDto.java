package canard.intern.post.following.backend.DTO;

import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.validator.DateLessThan;
import lombok.Builder;
import lombok.Data;

import javax.validation.Constraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Builder
@Data
public class TraineeDto {
    private Integer id;

    @NotNull
    private String lastname;

    private String firstname;
    private Gender gender;

    @NotNull
    @Past //TODO: replace with custom current date -18years
    //@Constraint(validatedBy = DateLessThan.class)
    @DateLessThan
    private LocalDate birthdate;

    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
    private String phoneNumber;

    @NotNull
    @Email//or @Pattern
    private String email;

}
