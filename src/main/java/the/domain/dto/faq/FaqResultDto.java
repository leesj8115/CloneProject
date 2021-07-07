package the.domain.dto.faq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import the.domain.entity.FaqEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaqResultDto {
    private long no;
    private String division;
    private String question;
    private String answer;

    public FaqResultDto(FaqEntity entity) {
        // 엔티티 받아서 dto 타입으로 변환

        no = entity.getNo();
        division = entity.getDivision();
        question = entity.getQuestion();
        answer = entity.getAnswer();
    }
    
    public FaqEntity toEntity() {
    	FaqEntity entity = FaqEntity.builder()
    			.no(no)
    			.division(division)
    			.question(question)
    			.answer(answer)
    			.build();
    	
    	return entity;
    }
}
