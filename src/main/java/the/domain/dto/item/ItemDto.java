package the.domain.dto.item;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import the.domain.entity.file.FileEntity;
import the.domain.entity.item.Category;


@AllArgsConstructor
@Data
public class ItemDto {
	
	private long no;
	
	private String smallCategory;
	
	private String brand;
	
	private String name;
	
	private long price;
	
	private long sellPrice;
	
}
