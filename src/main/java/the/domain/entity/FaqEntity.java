package the.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class FaqEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long no;

    @Column(nullable = false)
    private String division;            // 분류
    @Column(nullable = false)
    private String question;            // 질문
    @Column(nullable = false)
    private String answer;              // 답변
}
