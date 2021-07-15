package the.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import the.domain.entity.item.LargeCategory;
import the.service.item.ItemService;


@Slf4j
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/item")
	public String list(Model model) {
		// 상품 목록 페이지로 이동
		
		
		// 그냥 주소로 접근할 때에는... 강제적으로 신발/전체 를 출력하게 처리
		itemService.setCategoryMenu(1, 0, model);
		model.addAttribute("smallSelect", "" + 0);
		
		return "/item/list";
	}
	
	@PostMapping("/item")
	public String listUsingCategory(int tag, int large, int small, Model model) {
		// tag = 남자, 여자, 아동 ...
		// large = 신발, 의류, 용품
		// small = 전체, 워킹화, 러닝화 ....

		// 상품 데이터 뿌려주기는 페이지 내에서 ajax로 처리할 것임
		// 여기서는 상품 목록을 뿌려주기 위한 값만 내보내면 됨
		
		itemService.setCategoryMenu(large, small, model);
		
		model.addAttribute("smallSelect", "" + small);
		
		return "/item/list";
	}
	
	@PostMapping("/item/load")
	public String getItemList(String large, String small, Model model) {
		
		log.debug("상품 목록 데이터 가져오기 (large = " + large + ", " + "small = " + small + ")");
		itemService.findByCategory(large, small, model);
		
		return "/item/itemdata";
	}
	
}
