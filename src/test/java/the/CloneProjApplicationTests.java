package the;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import the.domain.entity.MallMember;
import the.domain.entity.MemberRepository;
import the.domain.entity.MemberRole;
import the.domain.entity.cs.faq.FaqEntity;
import the.domain.entity.cs.faq.FaqRepository;
import the.domain.entity.item.Category;
import the.domain.entity.item.CategoryRepository;
import the.domain.entity.item.LargeCategory;
import the.domain.entity.item.SmallCategory;

@SpringBootTest
class CloneProjApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberRepository repository;
	
	@Autowired
	FaqRepository faqRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Test
	void faq데이터생성() {
		String[] div = {"회원","배송","주문/결제","교환/반품/취소","쿠폰/포인트","상품"};

		IntStream.rangeClosed(1, 120).forEach(i -> {
			FaqEntity entity = FaqEntity.builder()
								.division(div[i % 6])
								.question("테스트질문"+i)
								.answer("테스트답변"+i)
								.build();			
			
			faqRepository.save(entity);
		});
	}

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
			
			repository.save(entity);
		});
	}
	
	@Test
	void 관리자계정생성() {
		MallMember entity = MallMember.builder()
								.email("admin")
								.pass(passwordEncoder.encode("admin"))
								.phone("010-9999-9999")
								.name("관리자")
								.isSocial(false)
								.build();
			
		entity.addRole(MemberRole.USER);
		entity.addRole(MemberRole.ADMIN);	// 관리자 추가
		
		repository.save(entity);
	}
	
	@Test
	void 카테고리테이블생성() {
		
		for(LargeCategory l : LargeCategory.values()) {
			for (SmallCategory s : SmallCategory.values()) {
				
				if (l.getTitle() == s.getLarge()) {
					
					Category entity = Category.builder()
							.large(l)
							.small(s)
							.build();
					
					categoryRepository.save(entity);
					
				}
				
				
			}
		}
		
	}

}
