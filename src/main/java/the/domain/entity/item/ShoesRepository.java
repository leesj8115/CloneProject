package the.domain.entity.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepository extends JpaRepository<ShoesEntity, Long> {

	List<ShoesEntity> findAllByShoesCategory(ShoesCategory shoesCategory);


}
