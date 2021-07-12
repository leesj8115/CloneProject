package the.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import the.domain.entity.item.Item;
import the.domain.entity.item.ShoesCategory;
import the.domain.entity.item.ShoesEntity;
import the.domain.entity.item.ShoesRepository;

@Slf4j
@Service
public class ShoesServiceImpl implements ShoesService {
	
	@Autowired
	private ShoesRepository shoesRepository;

	@Override
	public void insert(String smallCategory, Item item) {
		
		ShoesCategory small = ShoesCategory.valueOf(smallCategory);
		
		ShoesEntity entity = ShoesEntity.builder()
				.shoesCategory(small)
				.item(item)
				.build();		
		
		shoesRepository.save(entity);
		
		log.debug("shoes 등록 완료 (item 연결까지 완료)");
		
	}

	@Override
	public void find(int smallCategory, Model model) {
		
		log.debug("service에서 small에 맞는 목록 가져오기");
		
		
		if (smallCategory == 0) {
			// 0일 경우 전체 가져오기 (맨 처음 메뉴는 ALL 이기 때문에)
			
			List<ShoesEntity> allEntity = shoesRepository.findAll();
			
			model.addAttribute("result", allEntity);
		} else {
			// 0이 아닌 경우, 각 선택에 맞는 분류의 값만 가져오기
			ShoesCategory[] sc = ShoesCategory.values();
			
			List<ShoesEntity> result = shoesRepository.findAllByShoesCategory(sc[smallCategory-1]);

			model.addAttribute("result", result);
		}		
		
	}
	
	
}
