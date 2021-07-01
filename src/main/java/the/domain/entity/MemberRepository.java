package the.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MallMember, Long> {

	Optional<MallMember> findByEmailAndIsSocial(String username, boolean b);

	Optional<MallMember> findByEmail(String email);

}
