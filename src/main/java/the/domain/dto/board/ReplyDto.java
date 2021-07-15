package the.domain.dto.board;

import java.time.LocalDateTime;

import lombok.Data;
import the.domain.entity.board.Reply;

@Data
public class ReplyDto {
	private long no;
	private String reply;
	private String writer;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public ReplyDto(Reply entity) {
		// 엔티티 받아서 dto로 변환
	}
	
}
