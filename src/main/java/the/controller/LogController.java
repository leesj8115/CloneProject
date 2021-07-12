package the.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import the.domain.dto.MemberInputDto;
import the.service.log.LogService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LogController {
    @Autowired
    LogService service;

	@GetMapping("/log/login")
	public String login() {
		// 로그인 페이지로 이동
		return "/log/login";
	}
	
	@GetMapping("/log/join")
	public String join() {
		// 회원가입 페이지로 이동
		return "/log/join";
	}

    @PostMapping("/log/join")
    public String join(MemberInputDto dto, Model model) {
        System.out.println("로그인 처리");
        
        service.join(dto, model);

        return "/log/login";
    }

    @GetMapping("/log/error")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        // msg -> exception 형태로 꺼내오니까 한글이 깨짐..
        //model.addAttribute("guideMsg", exception);

        model.addAttribute("guideMsg", "아이디 혹은 비밀번호를 확인해주세요.");

        return "/log/login";
    }
    
    @ResponseBody
    @PostMapping("/log/check")
    public boolean check(String id) {
    	// 이메일 중복 체크
    	return service.emailCheck(id);
    }
    
}
