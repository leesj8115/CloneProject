package the.domain.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import the.domain.entity.file.FileEntity;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class ItemEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	
	@Column(nullable = false)
	private String brand;	// 브랜드명
	
	@Column(nullable = false)
	private String name;	// 제품명
	
	@Column(nullable = false)
	private long price;		// 정가
	
	@Column(nullable = false)
	private long sellPrice;	// 판매가
	
	
	// 파일은 Item 기준 일대다 단방향으로 설정
	// OneToMany default = LAZY
	@OneToMany
	@JoinColumn(name="item_no")
	@Builder.Default
	List<FileEntity> photo = new ArrayList<>();		// 제품 사진
	
	// 재고는 Stock 기준 다대일 양방향으로 설정
	@OneToMany(mappedBy = "item")	// Stock의 item 필드와 연결됨
	@Builder.Default
	private List<StockEntity> stocks = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_no")
	private Category category;	// 카테고리!
	
}
