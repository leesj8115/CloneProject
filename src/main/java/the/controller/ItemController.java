package the.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
	
	@GetMapping("/item")
	public String list() {
		// 상품 목록 페이지로 이동
		return "/item/list";
	}
	
	
}
