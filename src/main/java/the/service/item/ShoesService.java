package the.service.item;

import org.springframework.ui.Model;

import the.domain.entity.item.Item;

public interface ShoesService {

	void insert(String smallCategory, Item item);

	void find(int smallCategory, Model model);

}
