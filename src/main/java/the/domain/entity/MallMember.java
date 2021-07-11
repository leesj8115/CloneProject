package the.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class MallMember extends BaseDate {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;

	@Column(unique = true,nullable = false)
	private String email;	// 아이디 (이메일 형식)
	@Column(nullable = false)
	private String pass;	// 비밀번호
	@Column(nullable = false)
	private String name;	// 이름
	@Column
	private String phone;	// 연락처
	@Column
	private String birth;	// 생년월일

	@Column
    private String ip;		// 아이피

	@Column
	private boolean isSocial;	// 소셜 로그인 확인

	@Enumerated(EnumType.STRING)
	@Column
	private Gender gender;	// 성별
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private Set<MemberRole> roles = new HashSet<>();	// 권한
	
	
	public void addRole(MemberRole role) {
		roles.add(role);
	}
}
