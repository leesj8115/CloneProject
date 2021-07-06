package the.domain.entity.file;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

	List<FileEntity> findAllByDivision(String string);
    
}
