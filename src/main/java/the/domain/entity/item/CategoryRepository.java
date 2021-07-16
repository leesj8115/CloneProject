package the.domain.entity.item;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findBySmall(SmallCategory sc);

	List<Category> findAllByLarge(LargeCategory lc);

	Page<Category> findAllByLarge(LargeCategory lc, Pageable page);
	
}
