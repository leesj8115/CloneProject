package the.service;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.MemberDetails;
import the.domain.entity.MallMember;
import the.domain.entity.MemberRepository;
import the.domain.entity.MemberRole;

// 구글을 담았던 녀석
@Slf4j
@Service
public class SocialOauth2UserService extends DefaultOAuth2UserService {
	// 구현체를 상속 받았음. OAuth2UserService<OAuth2UserRequest, OAuth2User>를 구현하는 건 어려워용
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberRepository repository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User user = super.loadUser(userRequest);
		
		log.debug("소셜 로그인");

		// 어떤 소셜 아이디인지 확인이 가능하다
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		log.debug("registrationId : " + registrationId);

		Map<String, Object> temp = user.getAttributes();
		Set<String> key = temp.keySet();

		for (String s : key) {
			log.debug(s + " : " + temp.get(s));
		}

		if (registrationId.equals("naver")) {
			// 네이버 로그인시 네이버에 맞는 저장 처리 실행
			return saveSocialNaverMember(user);
		}
		else if (registrationId.equals("kakao")) {
			// 카카오 로그인시 네이버에 맞는 저장 처리 실행
			return saveSocialKakaoMember(user);
		}
		else if (registrationId.equals("google")) {
			return saveSocialMember(user);
		}

		return null;
	}

	private MemberDetails saveSocialNaverMember(OAuth2User user) {
		// 네이버에서 제공하는 정보 (내가 선택한 옵션 기준)
		// response 아래 id=(내부에서사용하는값), gender=M or F or U, email, mobile=010-1234-5678, mobile_e164=+821012345678, name, birthday=00-00, birthyear=0000

		log.debug("네이버 정보 저장 처리 실행");
		
		Map<String, String> userdata = user.getAttribute("response");

		String email = userdata.get("email");
		String name = userdata.get("name");
		String phone = userdata.get("mobile");
		String birth = "" + userdata.get("birthyear") + "-" + userdata.get("birthday");

		log.debug("email = " + email);
		log.debug("name = " + name);
		log.debug("phone = " + phone);
		log.debug("birth = " + birth);

		// 비밀번호는 풀지 못하는 임의의 값으로 입력
		MallMember entity = MallMember.builder()
							.email(email)
							.pass(passwordEncoder.encode(""+System.nanoTime()/10000))
							.name(name)
							.birth(birth)
							.phone(phone)
							.isSocial(true)
							.build();
		
		entity.addRole(MemberRole.USER);

		MallMember result = repository.save(entity);
		
		return new MemberDetails(result);
	}

	private MemberDetails saveSocialKakaoMember(OAuth2User user) {
		// 카카오에서 제공하는 정보 (선택 가능한 옵션 기준)
		// kakao_account 아래 email, birthday, gender=male or female
		// 정보가 너무 없어서.. 부족한 정보는 임의로 만들어 추가


		log.debug("카카오 정보 저장 처리 실행");	

		Map<String, String> userdata = user.getAttribute("kakao_account");

		String email = userdata.get("email");
		String[] _name = email.split("[@]");
		String name = _name[0];	// 아이디 앞부분 잘라내서 이름으로 사용함
		
		// 비밀번호는 풀지 못하는 임의의 값으로 입력
		MallMember entity = MallMember.builder()
							.email(email)
							.pass(passwordEncoder.encode(""+System.nanoTime()/10000))
							.name(name)
							.isSocial(true)
							.build();
		
		entity.addRole(MemberRole.USER);

		MallMember result = repository.save(entity);
		
		return new MemberDetails(result);
	}

	private MemberDetails saveSocialMember(OAuth2User user) {
		String email = user.getAttribute("email");
		String name = user.getAttribute("name");

		// 구글 로그인은 name(성 + 이름), given_name(이름), family_name(성), email 밖에 없음 흑흑
		// 전화번호는 없어서 안됨

		// 비밀번호는 풀지 못하는 임의의 값으로 입력
		MallMember entity = MallMember.builder()
							.email(email)
							.pass(passwordEncoder.encode(""+System.nanoTime()/10000))
							.name(name)
							.isSocial(true)
							.build();
		
		entity.addRole(MemberRole.USER);

		MallMember result = repository.save(entity);
		
		return new MemberDetails(result);
	}
	
}
