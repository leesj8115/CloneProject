package the.domain.entity.item;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import the.domain.entity.Gender;



@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Table(name = "supplies")
@Entity
public class SuppliesEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long suppliesNo;                   // 제품 번호
	
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;                  // 남성용, 여성용 구분

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private SuppliesCategory suppliesCategory;    // 소분류 (용품 내 구분. 피트니스, 모자 등)

    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")	// FK인 Item_id를 통해 Item 조회
    private Item item;
    
}
