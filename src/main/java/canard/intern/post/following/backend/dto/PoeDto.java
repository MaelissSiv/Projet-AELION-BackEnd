package canard.intern.post.following.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Objet qui represente une formation POE
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PoeDto {

    private Integer id;

    private String title;

    private String poeType;

    private LocalDate beginDate;

    private LocalDate endDate;
}