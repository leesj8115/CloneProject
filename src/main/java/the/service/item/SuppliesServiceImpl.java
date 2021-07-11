package the.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import the.domain.entity.item.Item;
import the.domain.entity.item.SuppliesCategory;
import the.domain.entity.item.SuppliesEntity;
import the.domain.entity.item.SuppliesRepository;

@Slf4j
@Service
public class SuppliesServiceImpl implements SuppliesService {
    
    @Autowired
    private SuppliesRepository suppliesRepository;

    @Override
    public void insert(String smallCategory, Item item) {

        SuppliesCategory small = SuppliesCategory.valueOf(smallCategory);
		
		SuppliesEntity entity = SuppliesEntity.builder()
				.suppliesCategory(small)
				.item(item)
				.build();		
		
        suppliesRepository.save(entity);
		
		log.debug("supplies 등록 완료 (item 연결까지 완료)");
    }
}
