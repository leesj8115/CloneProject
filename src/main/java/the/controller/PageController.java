package the.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/display/list")
	public String display() {
		// 상품 목록 페이지로 이동
		return "/display/list";
	}
}
