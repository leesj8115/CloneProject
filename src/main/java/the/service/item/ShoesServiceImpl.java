package the.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	
	
}
