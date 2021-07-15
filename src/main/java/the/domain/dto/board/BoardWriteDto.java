package the.domain.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import the.domain.entity.board.BoardEntity;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BoardWriteDto {
	
	private String subject;//제목
	private String content;//내용
	private String writer;//작성자
	
	public BoardEntity toEntity() {
		return BoardEntity.builder()
				.subject(subject)
				.content(content)
				.writer(writer)
				.build();
	}

}
