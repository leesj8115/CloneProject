package the.domain.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;



@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
//@Entity
public class ShoesEntity extends Item {
    @Column
    private String gender;                  // 남성용, 여성용 구분

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private ShoesCategory shoesCategory;    // 소분류 (신발 내 구분. 등산화, 슬리퍼 등등)
}
