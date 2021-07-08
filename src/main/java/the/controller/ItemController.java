package the.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
	
	@GetMapping("/item")
	public String list() {
		// 상품 목록 페이지로 이동
		return "/item/list";
	}
	
	@PostMapping("/item")
	public String showItem(int tag, int largeCategory, int smallCategory, Model model) {
		
		System.out.println("tag = " + tag);
		System.out.println("large = " + largeCategory);
		System.out.println("small = " + smallCategory);
		
		return "/item/list";
	}
}
