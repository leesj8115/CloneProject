package the.domain.entity.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	

}
