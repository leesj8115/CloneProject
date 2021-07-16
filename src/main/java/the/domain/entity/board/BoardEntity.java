package the.domain.entity.board;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import the.domain.entity.BaseDate;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "board")
@Entity
public class BoardEntity extends BaseDate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;//글번호
	@Column(nullable = false)
	private String subject;//제목
	@Column(nullable = false)
	private String content;//내용
	@Column
	private int readCount;//조회수
	@Column(nullable = false)
	private String writer;//작성자
	
	// Reply와의 관계를 양방향으로 설정
	@OneToMany(mappedBy = "board")	// 연결된 테이블의 필드명을 넣어준다
	List<Reply> replies;

}
