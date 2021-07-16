package the.domain.entity.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import the.domain.entity.BaseDate;

// 댓글

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Reply extends BaseDate {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;	// 번호
	
	@Column (nullable = false)
	private String reply;	// 댓글 내용
	
	@Column (nullable = false)
	private String writer;	// 작성자
	
	@ManyToOne
	@JoinColumn(name = "board_no")
	private BoardEntity board;
	
	
	
}
