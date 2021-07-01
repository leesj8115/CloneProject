package the.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)	// 리스너로 처리하기 때문에, 메인에 리스너 사용 허가
@MappedSuperclass			// 상속 처리해서 사용하는 녀석
@Getter
public class BaseDate {

	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime updatedDate;
}
