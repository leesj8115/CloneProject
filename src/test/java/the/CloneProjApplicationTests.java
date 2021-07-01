package the;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import the.domain.entity.MallMember;
import the.domain.entity.MemberRepository;
import the.domain.entity.MemberRole;

@SpringBootTest
class CloneProjApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberRepository repository;
	
	@Test
	void 테스트계정생성() {
		IntStream.rangeClosed(1, 3).forEach(i -> {
			MallMember entity = MallMember.builder()
								.email("test" + i + "@test.com")
								.pass(passwordEncoder.encode("1234"))
								.phone("010-1234-5678")
								.name("회원" + i)
								.isSocial(false)
								.build();
			
			entity.addRole(MemberRole.USER);
			
			if (i == 3) {
				entity.addRole(MemberRole.ADMIN);
			}
			
			repository.save(entity);
		});
	}
	
	

}
