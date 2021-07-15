package the.service.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.item.ItemDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.item.Category;
import the.domain.entity.item.CategoryRepository;
import the.domain.entity.item.ItemEntity;
import the.domain.entity.item.ItemRepository;
import the.domain.entity.item.LargeCategory;
import the.domain.entity.item.SmallCategory;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	@Override
	public void insert(ItemDto dto, List<FileEntity> photo, Model model) {

		// 카테고리 찾아서 연결하기
		
		SmallCategory sc = SmallCategory.valueOf(dto.getSmallCategory());
		
		Category category = categoryRepository.findBySmall(sc);
		
		log.debug("입력받은 카테고리 = " + category);
		
		
		ItemEntity entity = ItemEntity.builder()
				.brand(dto.getBrand())
				.name(dto.getName())
				.price(dto.getPrice())
				.sellPrice(dto.getSellPrice())
				.category(category)
				.build();
		
		// 사진 데이터 추가
		for (FileEntity file : photo) {
			entity.getPhoto().add(file);
		}
		
		// 카테고리 연결
		
		ItemEntity result = itemRepository.save(entity);
		
		if (result != null) {
			model.addAttribute("msg", "상품 등록에 성공했습니다.");
		}

	}

	@Transactional
	@Override
	public void findByCategory(String large, String small, Model model) {
		// 입력받은 선택 메뉴에 따라 다른 결과를 조회하고 보내줌
		
		// 1빼서 처리하는 이유 => large로 들어오는 값이 메뉴의 index라서... 0 이 HOT SPOT이기 때문...
		LargeCategory[] lcList = LargeCategory.values();
		
		LargeCategory lc = null;
		
		for (LargeCategory _lc : lcList) {
			if (large.equals(_lc.getTitle())) {
				lc = _lc;
			}
		}
		
		List<ItemEntity> result = new ArrayList<>();
		
		
		if (small.equals("전체")) {
			// 대분류에 의해 아이템을 가져올 경우
			
			List<Category> categorys = categoryRepository.findAllByLarge(lc);
			
			for (Category c : categorys) {
				List<ItemEntity> items = c.getItems();	// 아이템 목록 뽑아내서
				result.addAll(items);					// 전부 result에 추가
			}
			
		} else {
			// 전체가 아니라면, 소분류까지 고려해야함
			
			SmallCategory[] scList = SmallCategory.values();
			SmallCategory sc = null;
			
			// title이 기타로 각 대분류 마다 있기 때문에... large 값까지 비교함 ㅠ
			for (SmallCategory _sc : scList) {
				if (_sc.getLarge().equals(large) && _sc.getTitle().equals(small)) {
					sc = _sc;
				}
			}
			
			Category c = categoryRepository.findBySmall(sc);	// 소분류에 의한 DB 접근
		
			List<ItemEntity> items = c.getItems();
			
			result.addAll(items);
		}
		
		// 상품 담아서 전달
		model.addAttribute("items", result);

		// fetch = LAZY인 photo 도 따로 담아줘야 할듯
		log.debug("photoList 각각 가져오기!!!!");

		Map<Long, List<FileEntity>> photoList = new HashMap<>();

		for (ItemEntity e : result) {
			log.debug("나온 아이템 = " + e.getNo() + "번 / " + e.getName());
			log.debug("사진 갯수 = " + e.getPhoto().size());

			photoList.put(e.getNo(), e.getPhoto());
		}

		// 사진 데이터도 담아서 전달
		model.addAttribute("photoList", photoList);

		log.debug("총 계산된 사진 갯수 = " + photoList.size());

	}

	@Override
	public void setCategoryMenu(int large, int small, Model model) {
		
		// /item 에서 대분류에 따른 메뉴를 출력하기 위한 메소드
		
		LargeCategory lc = LargeCategory.values()[large-1];
		
		model.addAttribute("large", lc.getTitle());	// 대분류 이름 전달
		
		List<Category> categorys = categoryRepository.findAllByLarge(lc);
		
		List<SmallCategory> smallCategorys = categorys.stream().map(c -> c.getSmall() ).collect(Collectors.toList());
		List<String> smallList = smallCategorys.stream().map(sc -> sc.getTitle() ).collect(Collectors.toList());
		
		smallList.add(0, "전체");		// 소분류까지 정하지 않고, 대분류로 모두 가져올 메뉴 하나 추가
		
		model.addAttribute("smallList", smallList);	// 소분류 목록 전달
		
	}

}
