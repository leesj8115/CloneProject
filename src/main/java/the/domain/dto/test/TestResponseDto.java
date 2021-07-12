package the.domain.dto.test;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import the.domain.entity.test.TestEntity;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TestResponseDto {
	
	private long no;
	
	private String title;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	// 파일은 수동으로 설정
	private String fileName;
	private String fileUrl;
	private long fileSize;
	
	public TestResponseDto(TestEntity entity) {
		this.no = entity.getNo();
		this.title = entity.getTitle();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();
		
		this.fileName = entity.getFileName();
		this.fileUrl = entity.getFileUrl();
		this.fileSize = entity.getFileSize();
	}
	
}
