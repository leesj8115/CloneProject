package the.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import the.domain.entity.item.ClothesCategory;
import the.domain.entity.item.ShoesCategory;
import the.domain.entity.item.SuppliesCategory;

@Slf4j
@Controller
public class ItemController {
	
	@GetMapping("/item")
	public String list() {
		// 상품 목록 페이지로 이동
		return "/item/list";
	}
	
	@PostMapping("/item")
	public String showItem(int tag, int largeCategory, int smallCategory, Model model) {
		
		log.debug("tag = " + tag);					// 남성, 여성, 프로스펙스, 아동..
		log.debug("large = " + largeCategory);		// 신발, 의류, 용품..
		log.debug("small = " + smallCategory);		// large의 아래 카테고리..
		
		// tag는 db에서 조회할 때 사용할 녀석..
		// 남자/여자 = gender 조건에 따른 조회
		// 프로스펙스는 브랜드
		// 아동은.. 일단 패스

		String _large = "";	// 페이지로 전달할 largeCategory (int -> String)

		List<String> _small = new ArrayList<>();

		_small.add("ALL");	// 전체는 enum에 포함되어있지 않아서, 따로 추가함

		switch(largeCategory) {
			case 0:
				_large = "HOT SPOT";
				break;
			case 1:
				_large = "신발";

				// 신발 선택에 따른 하위 메뉴 리스트 만들기
				ShoesCategory[] shoesList = ShoesCategory.values();

				// enum의 title을 list에 추가
				for (ShoesCategory s : shoesList) {
					_small.add(s.getTitle());
				}

				break;
			case 2:
				_large = "의류";

				// 의류 선택에 따른 하위 메뉴 리스트 만들기
				ClothesCategory[] clothesList = ClothesCategory.values();

				// enum의 title을 list에 추가
				for (ClothesCategory s : clothesList) {
					_small.add(s.getTitle());
				}

				break;
			case 3:
				_large = "용품";

				// 의류 선택에 따른 하위 메뉴 리스트 만들기
				SuppliesCategory[] suppliesList = SuppliesCategory.values();

				// enum의 title을 list에 추가
				for (SuppliesCategory s : suppliesList) {
					_small.add(s.getTitle());
				}

				break;
		}


		model.addAttribute("largeCategory", _large);				// 선택한 대메뉴
		model.addAttribute("smallList", _small);			// 소메뉴 목록
		model.addAttribute("smallSelect", smallCategory);	// 소메뉴 목록 중 선택한 번호
		

		return "/item/list";
	}
}
