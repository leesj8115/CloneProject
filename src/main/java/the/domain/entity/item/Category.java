package the.domain.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LargeCategory large;	// 대분류 : 신발, 의류, 용품
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SmallCategory small;	// 소분류 : 모두 다!
	

	
	// TODO  
	// OneToMany 의 default는 LAZY 이나, ItemService -> findByCategory에서 연산간
	// category.getItems() 에서 LazyInitializationException 발생, LAZY 처리가 미흡함...
	// 그래서 일단은 fetch를 EAGER로 설정...
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
	private List<ItemEntity> items = new ArrayList<>();
	
}
