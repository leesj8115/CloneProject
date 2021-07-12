package the.domain.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import the.domain.entity.BaseDate;
import the.domain.entity.file.FileEntity;


@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Table(name = "item_entity")
@Entity
public class Item extends BaseDate {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long itemId;
	
    @Column
    @Enumerated(EnumType.STRING)
    private LargeCategory largeCategory;    // 대분류 = 신발, 의류, 용품

    // 소분류는 대분류에 따라 나뉘어지니 각 엔티티에서 처리

    @Column (nullable = false)
    private String brand;   // 브랜드

    @Column (nullable = false)
    private String name;    // 제품명

    @Column (nullable = false)
    private long price;     // 정가

    @Column
    private long sellPrice; // 판매가
    
    
    // 양방향으로 OneToMany 연결
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    @Builder.Default
    private List<FileEntity> photoList = new ArrayList<>();  // 사진
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    @Builder.Default
    private List<Stock> stockList = new ArrayList<>();  // 재고

}
