package canard.intern.post.following.backend.dto;


import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PoeTypeCountPoeDto {
    private String poeType;
    private long countPoe;
}
