package the.domain.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import lombok.Builder;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)	// 리스너로 처리하기 때문에, 메인에 리스너 사용 허가
@MappedSuperclass			// 상속 처리해서 사용하는 녀석
@Getter
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long no;                        // 제품 번호

    @Column
    @Enumerated(EnumType.STRING)
    private LargeCategory largeCategory;    // 대분류 = 신발, 의류, 용품

    // 소분류는 대분류에 따라 나뉘어지니 각 엔티티에서 처리

    @Column
    private String brand;   // 브랜드

    @Column (nullable = false)
    private String name;    // 제품명

    @Column (nullable = false)
    private long price; // 원래 가격

    @Column
    private long salePrice; // 할인 가격

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @Builder.Default
    private List<Stock> stock = new ArrayList<>();  // 재고

    

}
