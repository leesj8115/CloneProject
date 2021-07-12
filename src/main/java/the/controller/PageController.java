package the.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.MemberDetails;
import the.service.file.FileService;

@Slf4j
@Controller
public class PageController {

	@Autowired
	FileService	service;	// visual 에서 사진 가져올 때 사용함
	
	@GetMapping("/list")
	public String display() {
		// 상품 목록 페이지로 이동
		return "/item/list";
	}
	
	@GetMapping("/visual")
	public String visual(Model model) {
		// 메인 화면의 비주얼 영역을, DB를 통해 파일 경로를 확인하고 보여줌
		
		
		service.getVisual(model);
		
		return "/visual";
	}

	@GetMapping("/test")
	public String test(Model model, Authentication authentication) {
		// 로그인 한 사용자 이름 꺼내기 테스트

		log.debug("테스트!");

		MemberDetails member = (MemberDetails) authentication.getPrincipal();
		
		log.debug(member.getName());
		
		model.addAttribute("data", member.getName());

		return "/test";
	}


}
