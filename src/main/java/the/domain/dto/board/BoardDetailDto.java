package the.domain.dto.board;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import the.domain.entity.board.BoardEntity;

@RequiredArgsConstructor
@Data
public class BoardDetailDto {
	
	private long no;//글번호
	private String subject;//제목
	private String content;//내용
	private String writer;//작성자
	private int readCount;//조회수
	private LocalDateTime createdDate;//작성일
	
	public BoardDetailDto(BoardEntity entity) {
		this.no = entity.getNo();
		this.subject = entity.getSubject();
		this.content = entity.getContent();
		this.writer = entity.getWriter();
		this.readCount = entity.getReadCount();
		this.createdDate = entity.getCreatedDate();
	}
	
	

}
