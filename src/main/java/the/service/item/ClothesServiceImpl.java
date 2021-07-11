package the.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import the.domain.entity.item.ClothesCategory;
import the.domain.entity.item.ClothesEntity;
import the.domain.entity.item.ClothesRepository;
import the.domain.entity.item.Item;

@Slf4j
@Service
public class ClothesServiceImpl implements ClothesService {

    @Autowired
    ClothesRepository clothesRepository;

    @Override
    public void insert(String smallCategory, Item item) {

        ClothesCategory small = ClothesCategory.valueOf(smallCategory);
		
		ClothesEntity entity = ClothesEntity.builder()
				.clothesCategory(small)
				.item(item)
				.build();		
		
        clothesRepository.save(entity);
		
		log.debug("clothes 등록 완료 (item 연결까지 완료)");
    }
    
}
