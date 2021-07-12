package the.domain.dto.test;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import the.domain.entity.test.TestEntity;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TestWriteDto {
	private String title;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime startDate;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime endDate;
	
	// 파일은 수동으로 설정
	private String fileName;
	private String fileUrl;
	private long fileSize;
	
	
	public TestEntity toEntity() {
		return TestEntity.builder()
				.title(title)
				.startDate(startDate)
				.endDate(endDate)
				.fileName(fileName)
				.fileUrl(fileUrl)
				.fileSize(fileSize)
				.build();
	}
}
