package the.domain.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Stock {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long no;

    @Column
    private long itemNo;

    @Column (nullable = false)
    private long size;          // 사이즈
    
    @Column (nullable = false)
    private long color;         // 색상

    @Column(nullable = false)
    private long count;         // 수량

}
