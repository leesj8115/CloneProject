package the.domain.dto;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import the.domain.entity.MallMember;

@Getter
public class MemberDetails extends User implements OAuth2User {

	private String name;
	private boolean isSocial;
	
	private Map<String, Object> attributes;

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public MemberDetails(MallMember entity) {
		super(
			entity.getEmail(),
			entity.getPass(),
			entity.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))	// "ROLE_USER" 형태가 넘어감
				.collect(Collectors.toSet())
			);
			
		name = entity.getName();
		isSocial = entity.isSocial();
		attributes = this.getAttributes();
	}
	
	
	public MemberDetails(OAuth2User user) {
		// Oauth에서 가져온 정보들을 싹 다 attributes에 저장!
		super(null, null, null);
		attributes = user.getAttributes();
		name = user.getAttribute("name");
		isSocial = true;	// DB에 저장하는 건 아니지만, 일단 true로 설정해둠
	}
	
}
