package canard.intern.post.following.backend.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)

public class TraineeDetailDto extends TraineeDto {
    private PoeDto poe;
}