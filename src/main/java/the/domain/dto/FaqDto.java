package the.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import the.domain.entity.FaqEntity;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaqDto {
    private String division;
    private String question;
    private String answer;

    public FaqEntity toEntity() {
        FaqEntity entity = FaqEntity.builder().division(division).question(question).answer(answer).build();

        return entity;
    }
}
