package the.service.item;

import java.util.List;

import the.domain.dto.item.ItemDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.item.Item;

public interface ItemService {

	Item insert(ItemDto dto, List<FileEntity> photo);

}
