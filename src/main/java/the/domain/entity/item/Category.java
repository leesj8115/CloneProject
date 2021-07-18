package the.domain.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Category {
	
	@Id
	private long id;	// 아이디 직접 계산하여 부여
	// id는 0000 4자리로 이루어 지며
	// 0--- : 앞의 1자리는 대분류 (1 부터 신발, 의류, 용품)
	// -000 : 뒤의 3자리는 소분류 (001 부터 각 대분류의 하위 분류)
	// 로 조합하여 생성, 부여한다.
	
	@Enumerated(EnumType.STRING)
	@Column
	private LargeCategory large;	// 대분류 : 신발, 의류, 용품
	
	@Enumerated(EnumType.STRING)
	@Column
	private SmallCategory small;	// 소분류 : 모두 다!
	
	@Builder.Default
	@OneToMany(mappedBy = "category")	// fetch default = LAZY
	private List<ItemEntity> items = new ArrayList<>();
	
}
