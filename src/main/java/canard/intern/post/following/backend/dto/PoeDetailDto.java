package canard.intern.post.following.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


/**
 * Objet qui permet d'avoir, en plus de l'objet PoeDto, la liste des etudiants faisant la formation
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class PoeDetailDto extends PoeDto {
    private List<TraineeDto> trainees;


}
