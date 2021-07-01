package the.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import the.domain.dto.MemberDetails;
import the.domain.entity.MallMember;
import the.domain.entity.MemberRepository;


@Service
public class MemberDetailService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	// DB에서 유저의 정보를 가져오는 서비스
	// Id 어노테이션으로 맵핑된 email이 username 의 형태로 넘어옴
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MallMember> result = memberRepository.findByEmailAndIsSocial(username, false);
		
		System.out.println("로그인 시도");
		
		if (result.isEmpty()) {
			// 데이터가 없다면
			throw new UsernameNotFoundException("아이디 혹은 비밀번호를 확인하여 주십시오");
		}
		
		MallMember entity = result.get();
		
		// UserDetails 타입에 일치하는 형태로 반환하기 위해 dto 생성, 반환
		MemberDetails memberDetails = new MemberDetails(entity);
		
		return memberDetails;
	}
	
	
}
