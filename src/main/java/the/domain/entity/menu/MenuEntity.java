package the.domain.entity.menu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import the.domain.entity.item.LargeCategory;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
//@Entity
public class MenuEntity {
	
	// 메뉴 데이터를 꼭 DB로 넣어야하나..? 여기에 entity class에 사용하는 enum이 있는데

	@Id
	private long no;
	
	@Column (nullable = false)
	@Enumerated(EnumType.STRING)
	private LargeCategory largeCategory;	// 신발, 의류, 용품

	@Column
	@Builder.Default
	List<String> smallCategory = new ArrayList<>();	// 그 아래의 하위 카테고리
}
