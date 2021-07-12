package the.domain.entity.cs.faq;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FaqEntity, Long> {

    // 정렬도 OrderBy(컬럼명)(정렬기준) 을 통해서 처리할 수 있음!!
    // 직접 쿼리를 작성하는 방법도 있긴 합니다. 지금은 쿼리메서드 사용중
    List<FaqEntity> findAllByDivisionOrderByNoDesc(String division);

    Page<FaqEntity> findAllByDivision(String division, Pageable pageable);

    long countNoByDivision(String division);

}
