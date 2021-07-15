package the.domain.dto.board;

import lombok.Data;
import the.domain.entity.board.Reply;

@Data
public class ReplyInsertDto {
	
	private String reply;
	private String writer;
	
	public Reply toEntity() {
		
		return Reply.builder().reply(reply).writer(writer).build();
		
	}
	
}
