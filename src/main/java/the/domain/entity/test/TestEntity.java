package the.domain.entity.test;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Entity
public class TestEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	
	@Column (nullable = false)
	private String title;
	
	@Column (nullable = false)
	private LocalDateTime startDate;
	
	@Column (nullable = false)
	private LocalDateTime endDate;
	
	// 파일 관련 정보도 다 때려 넣기로 함
	@Column (nullable = false)
	private String fileName;
	
	@Column (nullable = false)
	private String fileUrl;
	
	@Column (nullable = false)
	private long fileSize;
}
