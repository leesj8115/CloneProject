package the.domain.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import the.domain.entity.file.FileEntity;


@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Entity
public class Item {
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
    private long price; // 원래 가격

    @Column
    private long salePrice; // 할인 가격
    
    @OneToMany
    @JoinColumn(name = "item_id")
    private List<FileEntity> photoList;  // 사진
    
    @OneToMany
    @JoinColumn(name = "item_id")
    private List<Stock> stockList;  // 재고

}
