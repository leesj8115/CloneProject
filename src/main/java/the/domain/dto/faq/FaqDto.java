package the.domain.dto.faq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaqDto {
    private String division;
    private String question;
    private String answer;
}
