package the.domain.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;



@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Table(name = "shoes")
@Entity
public class ShoesEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long shoesNo;                        // 제품 번호
	
    @Column
    private String gender;                  // 남성용, 여성용 구분

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private ShoesCategory shoesCategory;    // 소분류 (신발 내 구분. 등산화, 슬리퍼 등등)
   
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")	// FK인 Item_id를 통해 Item 조회
    private Item item;
    
}
