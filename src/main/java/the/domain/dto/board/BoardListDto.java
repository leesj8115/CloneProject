package the.domain.dto.board;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import the.domain.entity.board.BoardEntity;

@RequiredArgsConstructor
@Data
public class BoardListDto {
	
	private long no;//글번호
	private String subject;//제목
	private String writer;//작성자
	private int readCount;//조회수
	private LocalDateTime createdDate;//작성일
	private int replyCount;	// 댓글 갯수
	
	public BoardListDto(BoardEntity entity) {
		this.no = entity.getNo();
		this.subject = entity.getSubject();
		this.writer = entity.getWriter();
		this.readCount = entity.getReadCount();
		this.createdDate = entity.getCreatedDate();
		this.replyCount = entity.getReplies().size();
	}
	
	

}
