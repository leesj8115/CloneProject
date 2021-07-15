package the.domain.entity.item;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class StockEntity {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	
	@Column(nullable = false)
	private String size;	// 크기
	
	@Column(nullable = false)
	private String color;	// 색상
	
	@Column(nullable = false)
	private long count;		// 수량
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")	// 외래키
	private ItemEntity item;
	
}
