package the.domain.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ItemDto {	
	private String smallCategory;
	
	private String brand;
	
	private String name;
	
	private long price;
	
	private long sellPrice;
	
}
