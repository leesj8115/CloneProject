package the.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
	USER("ROLE_USER", "회원"),
	ADMIN("ROLE_ADMIN", "관리자");
	
	final String role;
	final String title;
}
