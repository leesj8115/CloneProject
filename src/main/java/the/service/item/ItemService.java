package the.service.item;

import java.util.List;

import org.springframework.ui.Model;

import the.domain.dto.item.ItemDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.item.ItemEntity;

public interface ItemService {

	void insert(ItemDto dto, List<FileEntity> photo, Model model);

	void setCategoryMenu(int large, int small, Model model);

	void findByCategory(String large, String small, Model model);

}
