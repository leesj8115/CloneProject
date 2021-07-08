package the.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.item.ItemDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.item.Item;
import the.domain.entity.item.ItemRepository;
import the.domain.entity.item.LargeCategory;

@Slf4j
@Service
public class ItemServieImpl implements ItemService {
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public Item insert(ItemDto dto, List<FileEntity> photo) {
		
		// 입력받은 LargeCategory 변환 (String -> enum)		
		String _large = dto.getLargeCategory();	// 입력한 String
		
		System.out.println("largeCategory = " + _large);
		
		LargeCategory large = LargeCategory.valueOf(_large);
	
		// 엔티티 생성 후 저장
		Item entity = Item.builder()
				.largeCategory(large)
				.brand(dto.getBrand())
				.name(dto.getName())
				.price(dto.getPrice())
				.salePrice(dto.getSalePrice())
				.photoList(photo)
				.build(); 
		
		Item result = itemRepository.save(entity);
		
		log.debug("item 등록 완료");
		
		return result;
	}
}
