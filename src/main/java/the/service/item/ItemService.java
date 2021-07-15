package the.service.item;

import java.util.List;

import org.springframework.ui.Model;

import the.domain.dto.item.ItemDto;
import the.domain.entity.file.FileEntity;

public interface ItemService {

	void insert(ItemDto dto, List<FileEntity> photo, Model model);

	void setCategoryMenu(int large, int small, Model model);

	void findByCategory(String large, String small, Model model);

}
