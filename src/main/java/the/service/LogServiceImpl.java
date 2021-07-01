package the.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import the.domain.dto.MemberInputDto;
import the.domain.entity.MallMember;
import the.domain.entity.MemberRepository;
import the.domain.entity.MemberRole;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    MemberRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest request;

    @Override
    public void join(MemberInputDto dto, Model model) {

        // ip의 request를 어디서 가져온거지

        MallMember entity = 
            MallMember.builder()
                .email(dto.getEmailId() + "@" + dto.getEmailDomain())
                .pass(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .phone(dto.getPhone())
                .birth(dto.getBirth())
                .ip(request.getRemoteAddr())
            .build();


        entity.addRole(MemberRole.USER);

        repository.save(entity);    // db에 등록

        model.addAttribute("guideMsg", dto.getName() + "님의 회원 가입을 축하합니다!");

    }

	@Override
	public boolean emailCheck(String id) {
		// 입력한 이메일이 이미 있는 회원인지 중복 체크
		Optional<MallMember> result = repository.findByEmail(id);
		
		return result.isEmpty();
	}

    

}
