package the.domain.entity.board;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

	List<Reply> findAllByBoardNo(long bno);

	List<Reply> findAllByBoardNo(long bno, Sort sort);

}
