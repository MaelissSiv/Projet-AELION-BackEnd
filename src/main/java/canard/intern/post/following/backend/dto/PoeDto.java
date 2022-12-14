package canard.intern.post.following.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PoeDto {


    private Integer id;


    private String title;


    private String poeType;


    private LocalDate beginDate;


    private LocalDate endDate;
}